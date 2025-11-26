package com.schoolevents.schoolevents_api.mappers;

import com.schoolevents.schoolevents_api.DTO.MessageDTO;
import com.schoolevents.schoolevents_api.models.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "send_date", dateFormat = "dd-MM-yyyy", source = "send_date")

    MessageDTO messageToMessageDTO(Message message);

    @Mapping(target = "send_date", dateFormat = "yyyy-MM-dd", source = "send_date")

    Message messageDTOToMessage(MessageDTO messageDTO);

}
