package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MessageDTO {
    private Long id;
    @NotBlank(message = "Debe de tener alguna descripcion")
    private String content;
    @NotNull(message = "La fecha no debe de ser nula")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate send_date;
    @NotNull(message = "Debe de estar relacionado con algun usuario")
    private UserDTO user;
}
