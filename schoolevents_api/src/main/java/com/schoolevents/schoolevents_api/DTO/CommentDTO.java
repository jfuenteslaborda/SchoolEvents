package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CommentDTO {
    @NotBlank(message = "El id no puede estar en blanco")
    private Long id;
    @NotBlank(message = "La descripcion esta vacia")
    private String description;
    @NotBlank(message = "Tiene que haber alguna fecha")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    @NotBlank(message = "Tiene que estar asociado a algun usuario")
    private UserDTO user;
    @NotBlank(message = "Tiene que estar asociado a algun evento")
    private EventDTO event;
}
