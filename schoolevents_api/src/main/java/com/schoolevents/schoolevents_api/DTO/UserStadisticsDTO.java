package com.schoolevents.schoolevents_api.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStadisticsDTO {
    private Integer user_id;
    private String full_name;
    private Long signs_total;
}
