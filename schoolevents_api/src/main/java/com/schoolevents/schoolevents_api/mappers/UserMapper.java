package com.schoolevents.schoolevents_api.mappers;

import com.schoolevents.schoolevents_api.DTO.SignDTO;
import com.schoolevents.schoolevents_api.DTO.UserDTO;
import com.schoolevents.schoolevents_api.models.Sign;
import com.schoolevents.schoolevents_api.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface UserMapper {

    @Mapping(target = "date", dateFormat = "dd-MM-yyyy", source = "date")

    UserDTO userToUserDTO(User user);

    @Mapping(target = "date", dateFormat = "yyyy-MM-DD", source = "date")

    User userDTOToUser(UserDTO userDTO);

}
