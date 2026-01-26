package com.schoolevents.schoolevents_api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface UserStadisticsDTO {
    Long getUser_id();
    String getFull_name();
    Long getSigns_total();
}

