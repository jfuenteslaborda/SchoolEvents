package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentDTO {

    private Long id;

    @NotBlank(message = "La descripcion esta vacia")
    private String description;

    @NotNull(message = "Tiene que haber alguna fecha")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    
    @NotNull(message = "Tiene que estar asociado a algun usuario")
    private UserDTO user;

    @NotNull(message = "Tiene que estar asociado a algun evento")
    private EventDTO event;
}
