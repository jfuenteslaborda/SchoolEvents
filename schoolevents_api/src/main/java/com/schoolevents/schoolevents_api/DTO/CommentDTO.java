package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CommentDTO {
    @NotBlank(message = "El id no puede estar en blanco")
    private Long id;
    @NotNull
    @NotBlank(message = "La descripcion esta vacia")
    private String description;
    @NotBlank(message = "Tiene que haber alguna fecha")
    @Pattern(
            regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$",
            message = "La fecha debe tener el formato dd-MM-yyyy"
    )
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    @NotBlank(message = "Tiene que estar asociado a algun usuario")
    private UserDTO user;
    @NotBlank(message = "Tiene que estar asociado a algun evento")
    private EventDTO event;
}
