package com.schoolevents.schoolevents_api.repositories;

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

    @Query(value = "SELECT e FROM Event e WHERE e.date BETWEEN :today AND :inTwoWeeks",
            nativeQuery = true)
    List<Event> filterByTwoWeeksLater(@Param("today") LocalDate today,
                                    @Param("inTwoWeeks") LocalDate inTwoWeeks);


    Event save(Event event);
    void deleteById(Long id);
}
