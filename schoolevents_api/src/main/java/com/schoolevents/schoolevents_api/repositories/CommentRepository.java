package com.schoolevents.schoolevents_api.repositories;

import org.springframework.data.repository.Repository;
import com.schoolevents.schoolevents_api.models.*;

import java.util.*;


public interface CommentRepository extends Repository<Comment, Long> {
    List<Comment> findAll();
    List<Comment> findByUserId(Long user_id);
    List<Comment> findByEventId(Long event_id);
    Comment findById(Long id);
    Comment save(Comment comment);
    Comment update(Comment comment, Long id);
    void deleteById(Long id);
}
