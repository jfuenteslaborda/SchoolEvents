package com.schoolevents.schoolevents_api.DTO;

import com.schoolevents.schoolevents_api.models.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
public class ImageDTO {
    private Long id;
    private String src;
    private String description;

    private Set<Event> events;
}
