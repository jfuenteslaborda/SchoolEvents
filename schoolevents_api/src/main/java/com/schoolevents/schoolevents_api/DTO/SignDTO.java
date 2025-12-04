package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.time.LocalDate;

@Data
public class SignDTO {
    @NotBlank(message = "El id no debe de ser nulo")
    private Long id;
    @NotBlank(message = "La fecha no debe de ser nula")
    @Pattern(
            regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$",
            message = "La fecha debe tener el formato dd-MM-yyyy"
    )
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    @NotBlank(message = "Debe de estar relacionado a un usuario")
    private UserDTO user;
    @NotBlank(message = "Debe de estar relacionado a algun evento")
    private EventDTO event;
}
