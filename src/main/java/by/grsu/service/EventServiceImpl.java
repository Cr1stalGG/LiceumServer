package by.grsu.service;

import by.grsu.dto.convertor.EventDtoConvertor;
import by.grsu.dto.event.EventCreationDto;
import by.grsu.dto.event.EventDto;
import by.grsu.entity.Event;
import by.grsu.repository.EventRepository;
import by.grsu.service.api.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public List<EventDto> findAll() {
        return eventRepository.findAll().stream()
                .map(EventDtoConvertor::convertEntityToDto)
                .toList();
    }

    @Override
    public EventDto findById(long id) {
        return EventDtoConvertor.convertEntityToDto(eventRepository.findById(id));
    }

    @Override
    public void save(EventCreationDto dto) {
        eventRepository.save(EventDtoConvertor.convertDtoToEntity(dto));
    }

    @Override
    public void deleteById(long id) {
        eventRepository.deleteById(id);
    }
}
