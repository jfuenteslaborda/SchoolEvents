package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.time.LocalDate;

@Data
public class SignDTO {
    private Long id;
    @NotNull(message = "La fecha no debe de ser nula")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    @NotNull(message = "Debe de estar relacionado a un usuario")
    private UserDTO user;
    @NotNull(message = "Debe de estar relacionado a algun evento")
    private EventDTO event;
}
