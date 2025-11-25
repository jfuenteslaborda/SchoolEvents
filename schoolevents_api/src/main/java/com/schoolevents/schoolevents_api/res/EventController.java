package com.schoolevents.schoolevents_api.res;

import com.schoolevents.schoolevents_api.DTO.EventDTO;
import com.schoolevents.schoolevents_api.Services.EventService;
import com.schoolevents.schoolevents_api.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("events")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/all")
    public List<EventDTO> getEvents() {
        return eventService.findAll();
    }

    @GetMapping("/by_id/{id}")
    public EventDTO getEvent(@PathVariable Long id) {
        return eventService.findById(id);
    }

    @GetMapping("/by_title/{title}")
    public EventDTO getEventByTitle(@PathVariable String title) {
        return eventService.findByTitle(title);
    }

    @GetMapping("/by_date/{date}")
    public List<EventDTO> getEventByDate(@PathVariable LocalDate date) {
        return eventService.findByDate(date);
    }

    @GetMapping("/by_two_weeks")
    public List<EventDTO> getEventByTwoWeeks() {
        return eventService.findByTwoWeeksLater();
    }

    @PostMapping("/create")
    public EventDTO addEvent(@RequestBody Event event){
        return eventService.save(event);
    }

    @PutMapping("/update/{id}")
    public EventDTO updateEvent(@RequestBody Event event,  @PathVariable Long id){
        return eventService.updateEvent(event, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEvent(@PathVariable Long id){
        eventService.deleteById(id);
    }

}
