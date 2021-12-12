package pl.igor.pricefinder.search.pricefindersearch.searching.infrastructure;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

}
