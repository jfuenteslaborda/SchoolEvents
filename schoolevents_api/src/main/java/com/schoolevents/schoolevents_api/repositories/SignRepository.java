package com.schoolevents.schoolevents_api.repositories;

import com.schoolevents.schoolevents_api.models.Sign;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface SignRepository extends Repository<Sign, Long> {
    List<Sign> findAll();
    Sign findById(Long id);
    Sign findByEventId(Long event_id);
    Sign findByUserId(Long user_id);
    Sign save(Sign sign);
    Sign update(Sign sign, Long id);
    void delete(Long id);
}
