package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.sql.Update;

import java.time.LocalDate;

@Data
public class EventDTO {

    @NotNull(groups = Update.class, message = "El id de evento no puede ser nulo")
    private Long id;

    @NotBlank(message = "Tiene que tener algún titulo")
    private String title;

    @NotBlank(message = "Tiene que tener alguna descripción")
    private String description;

    @NotNull(message = "El precio no puede ser nulo")
    private Integer price;

    @NotNull(message = "La capacidad no puede ser nula")
    @Min(value = 5, message = "La capacidad debe de ser mínimo 5")
    private Integer capacity;

    @NotNull(message = "Debe de tener alguna fecha")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotNull(message = "Debe haber necesidad de si es de pago")
    private Boolean need_payment;

    @NotNull(message = "Debe de tener alguna imagen principal")
    private String src;
}
