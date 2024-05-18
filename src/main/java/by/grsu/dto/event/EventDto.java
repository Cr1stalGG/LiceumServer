package by.grsu.dto.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventDto {
    private long uuid;
    private String tittle;
    private String description;
    private long pointsCount;
}
