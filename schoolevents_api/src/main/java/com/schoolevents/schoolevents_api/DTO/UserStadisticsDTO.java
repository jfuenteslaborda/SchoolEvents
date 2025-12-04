package com.schoolevents.schoolevents_api.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStadisticsDTO {
    @NotBlank(message = "Debe de estar asociado a algun usuario")
    private Integer user_id;
    @NotBlank(message = "Debe de tener algún nombre")
    private String full_name;
    private Long signs_total;
}
