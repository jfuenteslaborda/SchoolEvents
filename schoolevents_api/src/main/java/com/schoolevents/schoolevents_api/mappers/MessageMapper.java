package com.schoolevents.schoolevents_api.mappers;

import com.schoolevents.schoolevents_api.DTO.MessageDTO;
import com.schoolevents.schoolevents_api.models.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "send_date", dateFormat = "dd-MM-yyyy", source = "send_date")
    @Mapping(target = "user_id", source = "user.id")

    MessageDTO messageToMessageDTO(Message message);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "send_date", dateFormat = "yyyy-MM-dd", source = "send_date")

    Message messageDTOToMessage(MessageDTO messageDTO);

}
