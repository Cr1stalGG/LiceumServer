package by.grsu.repository;

import by.grsu.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findById(long id);

    @Query(value = "select * from transaction t where t.from_card = :cardNumber or t.to_card = :cardNumber", nativeQuery = true)
    List<Transaction> findAllByCardNumber(@Param("cardNumber") String cardNumber);
}
