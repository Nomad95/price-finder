package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.productextractor;

import com.google.common.collect.Sets;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ProductExtractor {
    public List<Product> findProductsInHtml(String pageHtml) {
        //first find price
        Document document = Jsoup.parse(pageHtml);
        Set<String> attributes = new HashSet<>();

        //sposob 1
        for (Element element : document.getAllElements()) {
            attributes.addAll(element.attributes().asList().stream()
                    .map(Attribute::getKey)
                    .filter(key -> key.toLowerCase().contains("price"))
                    .collect(Collectors.toSet()));
        }

        for (String attribute : attributes) {
            Elements elementsByAttribute = document.getElementsByAttribute(attribute);
            for (Element element : elementsByAttribute) {
                String attr = element.attr(attribute);
                System.out.println(attr);

                //find normalPrice
                findPreviousPrice(element);
            }

        }


        //sposob 2
        Pattern pattern = Pattern.compile("\\d{1,3}(?:[.,]\\d{3})*(?:[.,]\\d{2})");
        Matcher matcher = pattern.matcher(document.text());

        Set<Elements> elementsWithPrice = Sets.newHashSet();

        while (matcher.find()) {
            String actualPriceString = matcher.group();

            Elements elementWithPrice = document.getElementsContainingOwnText(actualPriceString);
            elementsWithPrice.add(elementWithPrice);

            for (Element element : elementWithPrice) {
                if (Objects.nonNull(element) && element.hasParent()) {
                    findProductNameFromElement(elementWithPrice.first().parent(), 2);
                }
            }

        }


        //Elements select = document.select("*");

        return List.of();
    }

    private void findPreviousPrice(Element element) {
        Elements del = element.getElementsByTag("del");
        if (!del.isEmpty()) {
            System.out.println("prev: " + del.html());
        }
    }

    private void findProductNameFromElement(Element element, int depth) {
        if (depth == 0) {
            return;
        }

        if (htmlContainsProductNameKeyword(element)) {

            Set<Elements> elementsWithProductName = element.getAllElements().stream()
                    .map(e -> e.attributes().asList())
                    .flatMap(List::stream)
                    .filter(a -> a.getKey().contains("name")
                                    || a.getKey().contains("nazwa")
                                    // || a.getKey().contains("produkt")
                                    // || a.getKey().contains("product")

                                    || a.getValue().contains("name")
                                    || a.getValue().contains("nazwa")
                            // || a.getValue().contains("produkt")
                            //|| a.getValue().contains("product")
                    )
                    .filter(a ->
                            (!a.getKey().contains("price") && !a.getKey().contains("cena"))
                        &&
                            (!a.getValue().contains("price") && !a.getValue().contains("cena")))
                    .map(attr -> element.getElementsByAttribute(attr.getKey()))
                    .collect(Collectors.toSet());

            elementsWithProductName.forEach(e -> {
                for (Element element1 : e) {
                    if (element1.html().length() < 100) {
                        System.out.println("BY ATTRIBUTE: " + element1.html());
                    }
                }
            });

//            if (!elementsWithProductName.isEmpty()) {
//                return;
//            }

            //drill and find
            for (Element child : element.children()) {
                //attribute


                //class
                //innerHtml
                if (htmlContainsProductNameKeyword(child)) {
                    //System.out.println(child.html());
                    //return;
                }

                //nested
            }

        }

        if (element.hasParent()) {
            findProductNameFromElement(element.parent(), depth - 1);
        }
    }

    private boolean htmlContainsProductNameKeyword(Element element) {
        return Objects.nonNull(element) &&
                Objects.nonNull(element.html()) &&
                element.html().toLowerCase().contains("name") ||
                element.html().toLowerCase().contains("product") ||
                element.html().toLowerCase().contains("nazwa") ||
                element.html().toLowerCase().contains("produkt");
    }
}
