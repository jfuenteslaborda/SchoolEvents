package com.schoolevents.schoolevents_api.DTO;

import com.schoolevents.schoolevents_api.models.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
public class CommentDTO {
    private Long id;
    private String description;
    private String date;
    private Integer user_id;
    private Integer event_id;
}
