package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schoolevents.schoolevents_api.models.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String full_name;
    private String email;
    private String password;
    private String photo;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    private Integer is_Admin;
}
