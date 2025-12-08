package com.schoolevents.schoolevents_api.Services;


import com.schoolevents.schoolevents_api.DTO.EventDTO;
import com.schoolevents.schoolevents_api.DTO.EventStadisticsDTO;
import com.schoolevents.schoolevents_api.exception.ElementNotFoundException;
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

    public EventDTO save(Event event) {
        if (event.getId() != null) {
            throw new ElementNotFoundException("El evento ya existe");
        }else {
            Event e = eventRepository.save(event);
            return eventMapper.eventToEventDTO(e);
        }
    }

    public List<EventStadisticsDTO> getEventsStadistic() {
        if (eventRepository.getEventStadistics().isEmpty()){
            throw new ElementNotFoundException("No hay eventos registrados");
        }else return eventRepository.getEventStadistics();
    }

    public EventDTO updateEvent(Event event, Long id) {
        if (eventRepository.findById(id) == null) {
            throw new ElementNotFoundException("Evento no encontrado con el id: "+id);
        } else {
            Event e = eventRepository.findById(id);
            e.setCapacity(event.getCapacity());
            e.setDescription(event.getDescription());
            e.setDate(event.getDate());
            e.setPrice(event.getPrice());
            e.setTitle(event.getTitle());
            e.setNeed_payment(event.getNeed_payment());
            Event event1 = eventRepository.save(e);
            return eventMapper.eventToEventDTO(event1);
        }
    }

    public void deleteById(Long id) {
        if (eventRepository.findById(id) == null) {
            throw new ElementNotFoundException("Evento no encontrado con el id: "+id);
        } else {
            eventRepository.deleteById(id);
        }
    }
}
