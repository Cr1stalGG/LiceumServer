package by.grsu.dto.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventCreationDto {
    private String title;
    private String description;
    private long pointsCount;
}
