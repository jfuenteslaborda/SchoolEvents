package com.schoolevents.schoolevents_api.repositories;

import com.schoolevents.schoolevents_api.DTO.EventStadisticsDTO;
import com.schoolevents.schoolevents_api.models.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends Repository<Event, Long> {

    List<Event> findAll();
    Event findById(Long id);
    Event findByTitle(String title);
    List<Event> findByDate(LocalDate date);
    List<Event> findByDateBetween(LocalDate start, LocalDate end);

    @Query(value = "SELECT\n" +
            "    e.id AS event_id,\n" +
            "    e.title,\n" +
            "    e.description,\n" +
            "    e.price,\n" +
            "    e.capacity,\n" +
            "    e.date, \n" +
            "    COUNT(s.user_id) AS assist_num\n" +
            "    FROM\n" +
            "    event e\n" +
            "JOIN\n" +
            "    sign s ON e.id = s.event_id\n" +
            "GROUP BY\n" +
            "    e.id, e.title, e.description, e.price, e.capacity, e.date\n" +
            "ORDER BY\n" +
            "    assist_num DESC\n" +
            "LIMIT 5;",nativeQuery = true)
    List<EventStadisticsDTO> getEventStadistics();
    Event save(Event event);
    void deleteById(Long id);
}
