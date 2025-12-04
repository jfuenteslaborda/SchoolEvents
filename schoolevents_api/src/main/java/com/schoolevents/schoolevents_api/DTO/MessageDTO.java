package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MessageDTO {
    @NotBlank(message = "El id no debe de estar nulo o blanco")
    private Long id;
    @NotBlank(message = "Debe de tener alguna descripcion")
    private String content;
    @NotBlank(message = "La fecha no debe de ser nula")
    @Pattern(
            regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$",
            message = "La fecha debe tener el formato dd-MM-yyyy"
    )
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate send_date;
    @NotBlank(message = "Debe de estar relacionado con algun usuario")
    private UserDTO user;
}
