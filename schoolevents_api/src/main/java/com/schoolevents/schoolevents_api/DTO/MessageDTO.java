package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MessageDTO {
    @NotBlank(message = "El id no debe de estar nulo o blanco")
    private Long id;
    @NotBlank(message = "Debe de tener alguna descripcion")
    private String content;
    @NotBlank(message = "La fecha no debe de ser nula")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate send_date;
    @NotBlank(message = "Debe de estar relacionado con algun usuario")
    private UserDTO user;
}
