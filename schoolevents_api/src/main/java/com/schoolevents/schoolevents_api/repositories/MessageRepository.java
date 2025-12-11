package com.schoolevents.schoolevents_api.repositories;

import com.schoolevents.schoolevents_api.models.Message;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MessageRepository extends Repository<Message, Long> {
    List<Message> findAll();
    Message findById(Long id);
    List<Message> findByUserId(Long user_id);
    Message save(Message message);
    void deleteById(Long id);
}
