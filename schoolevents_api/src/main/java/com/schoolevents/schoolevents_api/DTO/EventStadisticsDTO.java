package com.schoolevents.schoolevents_api.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public interface EventStadisticsDTO {
    Long getEvent_id();
    String getTitle();
    String getDescription();
    Float getPrice();
    Integer getCapacity();
    LocalDate getDate();
    Long getAssist_num();
}

