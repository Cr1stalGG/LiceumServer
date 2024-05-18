package by.grsu.service.api;

import by.grsu.dto.event.EventCreationDto;
import by.grsu.dto.event.EventDto;

import java.util.List;

public interface EventService {
    List<EventDto> findAll();
    EventDto findById(long id);
    void save(EventCreationDto dto);
    void deleteById(long id);
}
