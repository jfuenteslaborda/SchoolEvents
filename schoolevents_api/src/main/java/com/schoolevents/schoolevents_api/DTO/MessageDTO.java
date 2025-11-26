package com.schoolevents.schoolevents_api.DTO;

import com.schoolevents.schoolevents_api.models.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
public class MessageDTO {
    private Long id;
    private String content;
    private LocalDate send_date;
}
