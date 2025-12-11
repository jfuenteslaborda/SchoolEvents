package com.schoolevents.schoolevents_api.mappers;

import com.schoolevents.schoolevents_api.DTO.CommentDTO;
import com.schoolevents.schoolevents_api.models.Comment;
import com.schoolevents.schoolevents_api.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "date", dateFormat = "dd-MM-yyyy")
    public abstract CommentDTO commentToCommentDTO(Comment comment);

    @Mapping(target = "date", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "event", ignore = true)
    public abstract Comment commentDTOToComment(CommentDTO commentDTO);

}