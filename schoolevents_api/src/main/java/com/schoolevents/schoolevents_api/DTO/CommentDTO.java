package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentDTO {

    private Long id;

    // String → usa NotBlank
    @NotBlank(message = "La descripcion esta vacia")
    private String description;

    // LocalDate → NO se usa Pattern, solo NotNull
    @NotNull(message = "Tiene que haber alguna fecha")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    // Objetos → NotNull + Valid (para validar sus campos internos)
    @NotNull(message = "Tiene que estar asociado a algun usuario")
    @Valid
    private UserDTO user;

    @NotNull(message = "Tiene que estar asociado a algun evento")
    @Valid
    private EventDTO event;
}
