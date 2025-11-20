package com.schoolevents.schoolevents_api.Services;


import com.schoolevents.schoolevents_api.models.*;
import com.schoolevents.schoolevents_api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(Long id) {
        return eventRepository.findById(id);
    }

    public Event findByTitle(String title) {
        return eventRepository.findByTitle(title);
    }

    public List<Event> findByDate(LocalDate date) {
        return eventRepository.findByDate(date);
    }

    public List<Event> findByTwoWeeksLater() {
        return eventRepository.filterByTwoWeeksLater(LocalDate.now(), LocalDate.now().plusWeeks(2));
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Event event, Long id) {
        Event e = eventRepository.findById(id);
        e.setCapacity(event.getCapacity());
        e.setDescription(event.getDescription());
        e.setDate(event.getDate());
        e.setPrice(event.getPrice());
        e.setTitle(event.getTitle());
        e.setNeed_payment(event.getNeed_payment());
        return eventRepository.save(e);
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }


}
