package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private Integer price;
    private Integer capacity;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    private Boolean need_payment;
}
