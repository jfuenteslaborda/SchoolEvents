package com.schoolevents.schoolevents_api.mappers;

import com.schoolevents.schoolevents_api.DTO.SignDTO;
import com.schoolevents.schoolevents_api.models.Sign;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface SignMapper {

    @Mapping(target = "date", dateFormat = "dd-MM-yyyy", source = "date")

    SignDTO signToSignDTO(Sign sign);

    @Mapping(target = "date", dateFormat = "dd-MM-yyyy", source = "date")

    Sign signDTOToSign(SignDTO signDTO);


}
