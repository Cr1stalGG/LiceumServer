package by.grsu.repository;

import by.grsu.entity.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long> {
    boolean existsByName(String name);
    Bonus findById(long id);
    List<Bonus> findAll();
    Bonus findByName(String name);
    List<Bonus> findByNameLike(@Param("name") String name);
    void deleteById(long id);
    void deleteByName(String name);
}