package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


import java.time.LocalDate;

@Data
public class EventDTO {
    @NotBlank(message = "El id no puede estar vacio")
    private Long id;
    @NotBlank(message = "Tiene que tener algún titulo")
    private String title;
    @NotBlank(message = "Tiene que tener alguna descripción")
    private String description;
    @Min(value = 1, message = "El precio debe de ser mínimo 1")
    private Integer price;
    @Min(value = 5, message = "La capacidad debe de ser minimo 5")
    private Integer capacity;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Pattern(
            regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$",
            message = "La fecha debe tener el formato dd-MM-yyyy"
    )
    @NotBlank(message = "Debe de tener alguna fecha")
    private LocalDate date;
    @NotBlank(message = "Debe haber necesidad de si es de pago")
    private Boolean need_payment;
}
