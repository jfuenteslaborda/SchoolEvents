package com.schoolevents.schoolevents_api.DTO;

import com.schoolevents.schoolevents_api.models.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
public class ImageDTO {
    @NotBlank(message = "El id no debe de ser nulo")
    private Long id;
    @NotBlank(message = "La fecha debe de tener src")
    private String src;
    @NotBlank(message = "La descripcion no debe de ser nula")
    private String description;
    @NotBlank(message = "Debe de estar relacionada con algun evento")
    private EventDTO event;
}
