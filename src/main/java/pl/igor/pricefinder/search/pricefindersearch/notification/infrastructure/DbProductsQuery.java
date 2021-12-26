package pl.igor.pricefinder.search.pricefindersearch.notification.infrastructure;

import org.springframework.transaction.annotation.Transactional;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.ProductFilter;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.ProductsQuery;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.ProductDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class DbProductsQuery implements ProductsQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductDto> getProductsByFilter(ProductFilter productFilter) {
        List<Object[]> results = entityManager.createNativeQuery(String.format("select name, final_price from product " +
                        "where name ~* '%s' " +
                        "and final_price < %s " +
                        "and search_id = %s",
                productFilter.getPipeSeparatedProductNames(),
                productFilter.getMaxPrice(),
                productFilter.getSearchId()))
                .getResultList();

        return results.stream()
                .map(record -> new ProductDto((String) record[0], new BigDecimal((String) record[1])))
                .collect(Collectors.toList());
    }
}
