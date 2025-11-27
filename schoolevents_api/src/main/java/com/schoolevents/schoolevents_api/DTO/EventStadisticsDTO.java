package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventStadisticsDTO {
    private Integer event_id;
    private String title;
    private String description;
    private BigDecimal price;
    private Integer capacity;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime date;
    private Long assist_num;
}
