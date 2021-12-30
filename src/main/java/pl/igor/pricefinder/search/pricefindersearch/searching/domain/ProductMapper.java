package pl.igor.pricefinder.search.pricefindersearch.searching.domain;

import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Product;

import java.util.Collection;
import java.util.stream.Collectors;

public class ProductMapper {
    Collection<ProductEntity> mapAll(Collection<Product> products) {
        return products.stream().map(productDto -> ProductEntity.builder()
                .searchId(productDto.getSearchId())
                .searchDate(productDto.getSearchDate())
                .itemId(productDto.getItemId())
                .source(productDto.getSource())
                .name(productDto.getName())
                .brand(productDto.getBrand())
                .category(productDto.getCategory())
                .categoryId(productDto.getCategoryId())
                .finalPrice(new Price(productDto.getFinalPrice().getGross(), productDto.getFinalPrice().getCurrency()))
                .basePrice(new Price(productDto.getBasePrice().getGross(), productDto.getBasePrice().getCurrency()))
                .itemUrl(productDto.getItemUrl())
                .pictureUrl(productDto.getPictureUrl())
                .build()
        ).collect(Collectors.toUnmodifiableList());
    }
}
