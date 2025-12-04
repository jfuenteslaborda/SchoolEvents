package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class SignDTO {
    @NotBlank(message = "El id no debe de ser nulo")
    private Long id;
    @NotBlank(message = "La fecha no debe de ser nula")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    @NotBlank(message = "Debe de estar relacionado a un usuario")
    private UserDTO user;
    @NotBlank(message = "Debe de estar relacionado a algun evento")
    private EventDTO event;
}
