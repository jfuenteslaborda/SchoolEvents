package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Debe de tener id")
    private Integer event_id;
    @NotBlank(message = "Debe de tener titulo")
    private String title;
    @NotBlank(message = "Debe de tener descripcion")
    private String description;
    @Min(value = 1, message = "Debe de tener minimo 1 de precio")
    private BigDecimal price;
    @Min(value = 5, message = "La capacidad debe de ser minima")
    private Integer capacity;
    @NotBlank(message = "La fecha no debe de ser nula")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime date;
    private Long assist_num;
}
