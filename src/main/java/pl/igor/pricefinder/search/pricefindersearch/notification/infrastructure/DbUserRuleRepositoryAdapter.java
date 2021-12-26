package pl.igor.pricefinder.search.pricefindersearch.notification.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.UserRule;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.UserRuleRepository;

import java.util.List;
import java.util.UUID;

@Transactional
@RequiredArgsConstructor
public class DbUserRuleRepositoryAdapter implements UserRuleRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public void saveUserRule(UserRule userRule) {
        jpaUserRepository.save(userRule);
    }

    @Override
    public List<UserRule> getRulesByUser(UUID userId) {
        return jpaUserRepository.findByUserId(userId);
    }

    @Override
    public List<UserRule> getAllRules() {
        return jpaUserRepository.findAll();
    }
}

@Repository
interface JpaUserRepository extends JpaRepository<UserRule, Long> {

    List<UserRule> findByUserId(UUID userId);
}
