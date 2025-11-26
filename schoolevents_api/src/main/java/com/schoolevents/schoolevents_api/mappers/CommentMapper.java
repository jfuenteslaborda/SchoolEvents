package com.schoolevents.schoolevents_api.mappers;

import com.schoolevents.schoolevents_api.DTO.CommentDTO;
import com.schoolevents.schoolevents_api.Services.EventService;
import com.schoolevents.schoolevents_api.Services.UserService;
import com.schoolevents.schoolevents_api.models.Comment;
import com.schoolevents.schoolevents_api.models.Event;
import com.schoolevents.schoolevents_api.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    @Autowired
    protected UserService userService;

    @Autowired
    protected EventService eventService;

    @Mapping(target = "date", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "user_id", source = "user.id")
    @Mapping(target = "event_id", source = "event.id")
    public abstract CommentDTO commentToCommentDTO(Comment comment);

    @Mapping(target = "date", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "user", source = "user_id", qualifiedByName = "mapToUser")
    @Mapping(target = "event", source = "event_id", qualifiedByName = "mapToEvent")
    public abstract Comment commentDTOToComment(CommentDTO commentDTO);

    @Named("mapToUser")
    public User mapToUser(Long userId) {
        if (userId != null) {
            return userService.findByIdEntity(userId);
        }
        return null;
    }

    @Named("mapToEvent")
    public Event mapToEvent(Long eventId) {
        if (eventId != null) {
            return eventService.findByIdEntity(eventId);
        }
        return null;
    }
}
