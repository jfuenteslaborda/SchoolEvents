package com.schoolevents.schoolevents_api.mappers;

import com.schoolevents.schoolevents_api.DTO.EventDTO;
import com.schoolevents.schoolevents_api.models.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface EventMapper {

    @Mapping(target = "date", dateFormat = "dd-MM-yyyy", source = "date")

    EventDTO eventToEventDTO(Event event);

    @Mapping(target = "date", dateFormat = "dd-MM-yyyy", source = "date")

    Event eventDTOToEvent(EventDTO eventDTO);

}
