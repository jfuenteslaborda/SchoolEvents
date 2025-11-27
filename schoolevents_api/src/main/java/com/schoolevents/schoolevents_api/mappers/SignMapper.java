package com.schoolevents.schoolevents_api.mappers;

import com.schoolevents.schoolevents_api.DTO.SignDTO;
import com.schoolevents.schoolevents_api.models.Sign;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SignMapper {

    @Mapping(target = "date", dateFormat = "dd-MM-yyyy", source = "date")
    @Mapping(target = "user_id", source = "user.id")
    @Mapping(target = "event_id", source = "event.id")

    SignDTO signToSignDTO(Sign sign);

    @Mapping(target = "date", dateFormat = "dd-MM-yyyy", source = "date")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "event", ignore = true)

    Sign signDTOToSign(SignDTO signDTO);


}
