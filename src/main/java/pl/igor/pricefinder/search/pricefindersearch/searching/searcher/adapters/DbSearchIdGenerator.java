package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.adapters;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SearchIdGenerator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

@Transactional(isolation = Isolation.SERIALIZABLE)
public class DbSearchIdGenerator implements SearchIdGenerator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public synchronized Long generateId() {
        return ((BigInteger) entityManager.createNativeQuery("select nextval('search_sequence')").getSingleResult()).longValue();
    }
}