package com.schoolevents.schoolevents_api.mappers;

import com.schoolevents.schoolevents_api.DTO.CommentDTO;
import com.schoolevents.schoolevents_api.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "date", dateFormat = "dd-MM-yyyy", source = "date")

    CommentDTO commentToCommentDTO(Comment comment);

    @Mapping(target = "date", dateFormat = "yyyy-MM-dd", source = "date")

    Comment commentDTOToComment(CommentDTO commentDTO);
}
