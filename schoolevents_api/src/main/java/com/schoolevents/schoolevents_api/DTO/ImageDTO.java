package com.schoolevents.schoolevents_api.DTO;

import com.schoolevents.schoolevents_api.models.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
public class ImageDTO {
    private Long id;
    @NotBlank(message = "La fecha debe de tener src")
    private String src;
    @NotBlank(message = "La descripcion no debe de ser nula")
    private String description;
    @NotNull(message = "Debe de estar relacionada con algun evento")
    private EventDTO event;
}
