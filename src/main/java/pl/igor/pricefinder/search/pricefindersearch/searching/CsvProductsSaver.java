package pl.igor.pricefinder.search.pricefindersearch.searching;

import lombok.RequiredArgsConstructor;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class CsvProductsSaver implements ProductsSaver {

    private final String filePath;

    @Override
    public void saveBatch(List<Product> products) {
        File csvOutputFile = new File(filePath);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.println("Item id,Name,Brand,Category,Item Url,Final price,Final price currency,Base price,Base price currency,Picture url,Category id");
            products.stream()
                    .map(this::convertToCsv)
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //todo:
        }
    }

    private String convertToCsv(Product product) {
        String[] row = {
                product.getItemId(),
                product.getName(),
                product.getBrand(),
                product.getCategory(),
                product.getItemUrl(),
                product.getFinalPrice().getGross().toString(),
                product.getFinalPrice().getCurrency(),
                product.getBasePrice().getGross().toString(),
                product.getBasePrice().getCurrency(),
                product.getPictureUrl(),
                product.getCategoryId()
        };

        return Stream.of(row)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    @Override
    public void save(Product product) {

    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
