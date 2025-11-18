package com.schoolevents.schoolevents_api.DTO;

import com.schoolevents.schoolevents_api.models.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
public class SignDTO {
    private Long id;
    private LocalDate date;

    private Set<User> users;
    private Set<Event> events;
}
