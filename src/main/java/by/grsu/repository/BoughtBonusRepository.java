package by.grsu.repository;


import by.grsu.entity.BoughtBonus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoughtBonusRepository extends JpaRepository<BoughtBonus, Long> {
    BoughtBonus findByNameAndToken(String name, String token);
    boolean existsByToken(String token);
    BoughtBonus findByToken(String token);
    void deleteByName(String name);
    void deleteByToken(String token);
}
