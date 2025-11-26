package com.schoolevents.schoolevents_api.Services;

import com.schoolevents.schoolevents_api.DTO.CommentDTO;
import com.schoolevents.schoolevents_api.mappers.CommentMapper;
import com.schoolevents.schoolevents_api.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.schoolevents.schoolevents_api.models.*;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    private CommentMapper commentMapper;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public List<Comment> findByUserId(Long user_id) {
        return commentRepository.findByUserId(user_id);
    }

    public List<Comment> findByEventId(Long event_id) {
        return commentRepository.findByEventId(event_id);
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment newData, Long id) {
        Comment c = commentRepository.findById(id);
        c.setDescription(newData.getDescription());
        return  commentRepository.save(c);
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
