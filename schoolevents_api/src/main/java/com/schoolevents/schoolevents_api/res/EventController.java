package com.schoolevents.schoolevents_api.res;

import com.schoolevents.schoolevents_api.DTO.EventDTO;
import com.schoolevents.schoolevents_api.DTO.EventStadisticsDTO;
import com.schoolevents.schoolevents_api.Services.EventService;
import com.schoolevents.schoolevents_api.models.Event;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public List<EventDTO> getEventByDate(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return eventService.findByDate(date);
    }

    @GetMapping("/by_two_weeks")
    public List<EventDTO> getEventByTwoWeeks() {
        return eventService.findByTwoWeeksLater();
    }

    @GetMapping("/stadistic")
    public List<EventStadisticsDTO> getEventStadistics() {
        return eventService.getEventsStadistic();
    }

    @PostMapping("/create")
    public EventDTO addEvent(@Valid @RequestBody EventDTO event){
        return eventService.save(event);
    }

    @PutMapping("/update/{id}")
    public EventDTO updateEvent(@Valid @RequestBody EventDTO event,  @PathVariable Long id){
        return eventService.updateEvent(event, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEvent(@PathVariable Long id){
        eventService.deleteById(id);
    }

}
