package by.grsu.repository;

import by.grsu.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAll();
    Event findById(long id);
}
