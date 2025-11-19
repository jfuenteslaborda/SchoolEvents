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

    public List<Event> findByTitle(String title) {
        return eventRepository.findByTitle(title);
    }

    public List<Event> findByDate(LocalDate date) {
        return eventRepository.findByDate(date);
    }

    public List<Event> findByTwoWeeksLater() {
        return eventRepository.findByTwoWeeksLater(LocalDate.now(), LocalDate.now().plusWeeks(2));
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Event update(Event event, Long id) {
        return eventRepository.update(event, id);
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }


}
