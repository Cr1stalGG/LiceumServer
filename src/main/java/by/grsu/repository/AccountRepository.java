package by.grsu.repository;

import by.grsu.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByUsername(String username);
    Account findByUsername(String username);
    @Query(value = "select * from account a where a.role = 1", nativeQuery = true)
    List<Account> findAll();
}
