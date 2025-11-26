package com.schoolevents.schoolevents_api.DTO;

import com.schoolevents.schoolevents_api.models.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private Integer price;
    private Integer capacity;
    private String date;
    private Boolean need_payment;
}
