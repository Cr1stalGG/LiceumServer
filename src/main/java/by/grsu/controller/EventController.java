package by.grsu.controller;

import by.grsu.dto.event.EventCreationDto;
import by.grsu.dto.event.EventDto;
import by.grsu.service.api.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private EventService eventService;

    @GetMapping
    public List<EventDto> findAll(){
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public EventDto findById(@PathVariable long id){
        return eventService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void save(@RequestBody EventCreationDto dto){
        eventService.save(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(@PathVariable long id){
        eventService.deleteById(id);
    }
}
