package pl.igor.pricefinder.search.pricefindersearch.searching.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Transactional
@RequiredArgsConstructor
public class JpaPostgresProductRepository implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Override
    public void saveAll(Collection<ProductEntity> products) {
        jpaProductRepository.saveAll(products);
    }
}
