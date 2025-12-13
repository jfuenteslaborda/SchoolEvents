package com.schoolevents.schoolevents_api.Services;


import com.schoolevents.schoolevents_api.DTO.EventDTO;
import com.schoolevents.schoolevents_api.DTO.EventStadisticsDTO;
import com.schoolevents.schoolevents_api.exception.ElementNotFoundException;
import com.schoolevents.schoolevents_api.exception.HttpMessageNotReadableException;
import com.schoolevents.schoolevents_api.mappers.EventMapper;
import com.schoolevents.schoolevents_api.models.*;
import com.schoolevents.schoolevents_api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public List<EventDTO> findAll() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventsDTO = new java.util.ArrayList<>(List.of());
        for (Event event : events) {
            eventsDTO.add(eventMapper.eventToEventDTO(event));
        }
        if (events.isEmpty()){
            throw new ElementNotFoundException("No hay eventos registrados");
        }else return eventsDTO;
    }

    public EventDTO findById(Long id) {
        Event e = eventRepository.findById(id);
        if (e == null) {
            throw new ElementNotFoundException("Evento no encontrado con el id: "+id);
        } else return eventMapper.eventToEventDTO(e);
    }

    public EventDTO findByTitle(String title) {
        Event e = eventRepository.findByTitle(title);
        if (e == null) {
            throw new ElementNotFoundException("Evento con el titulo no encontrado: "+title);
        } else return eventMapper.eventToEventDTO(e);
    }

    public List<EventDTO> findByDate(LocalDate date) {
        List<Event> events = eventRepository.findByDate(date);
        List<EventDTO> eventsDTO = new java.util.ArrayList<>(List.of());
        for (Event event : events) {
            eventsDTO.add(eventMapper.eventToEventDTO(event));
        }
        if (events.isEmpty()){
            throw new ElementNotFoundException("No hay eventos registrados en la fecha: "+date);
        } else return eventsDTO;
    }

    public List<EventDTO> findByTwoWeeksLater() {
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusWeeks(2);
        List<Event> events = eventRepository.findByDateBetween(start, end);
        List<EventDTO> eventsDTO = new java.util.ArrayList<>(List.of());
        for (Event event : events) {
            eventsDTO.add(eventMapper.eventToEventDTO(event));
        }
        if (events.isEmpty()){
            throw new ElementNotFoundException("No hay eventos en dos semanas");
        } else return eventsDTO;
    }

    public EventDTO save(EventDTO event) {
        if (event.getId() != null) {
            throw new IllegalArgumentException("El evento ya existe, no puede tener ID al crear");
        }
        if (event.getDate() == null) {
            throw new HttpMessageNotReadableException("La fecha no es correcta");
        }
        Event savedEvent = eventRepository.save(eventMapper.eventDTOToEvent(event));
        return eventMapper.eventToEventDTO(savedEvent);
    }


    public List<EventStadisticsDTO> getEventsStadistic() {
        if (eventRepository.getEventStadistics().isEmpty()){
            throw new ElementNotFoundException("No hay eventos registrados");
        }else return eventRepository.getEventStadistics();
    }

    public EventDTO updateEvent(EventDTO eventDTO, Long id) {
        if (eventDTO == null) {
            throw new IllegalArgumentException("No se puede actualizar un evento vacío");
        }

        Event existingEvent = eventRepository.findById(id);
        if (existingEvent == null) {
            throw new ElementNotFoundException("Evento no encontrado con el id: "+id);
        }

        existingEvent.setTitle(eventDTO.getTitle());
        existingEvent.setDescription(eventDTO.getDescription());
        existingEvent.setDate(eventDTO.getDate());
        existingEvent.setPrice(Float.valueOf(eventDTO.getPrice())); // si es Integer en DTO y entidad, no hace falta Float.valueOf
        existingEvent.setCapacity(eventDTO.getCapacity());
        existingEvent.setNeed_payment(eventDTO.getNeed_payment());

        Event updatedEvent = eventRepository.save(existingEvent);

        return eventMapper.eventToEventDTO(updatedEvent);
    }


    public void deleteById(Long id) {
        if (eventRepository.findById(id) == null) {
            throw new ElementNotFoundException("Evento no encontrado con el id: "+id);
        } else {
            eventRepository.deleteById(id);
        }
    }
}
