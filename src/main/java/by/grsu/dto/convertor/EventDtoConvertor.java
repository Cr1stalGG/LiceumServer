package by.grsu.dto.convertor;

import by.grsu.dto.event.EventCreationDto;
import by.grsu.dto.event.EventDto;
import by.grsu.entity.Event;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EventDtoConvertor {
    public static Event convertDtoToEntity(EventCreationDto source){
        return Event.builder()
                .title(source.getTitle())
                .description(source.getDescription())
                .pointsCount(source.getPointsCount())
                .build();
    }

    public static EventDto convertEntityToDto(Event source){
        return EventDto.builder()
                .uuid(source.getId())
                .tittle(source.getTitle())
                .description(source.getDescription())
                .pointsCount(source.getPointsCount())
                .build();
    }
}
