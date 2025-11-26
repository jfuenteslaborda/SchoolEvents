package com.schoolevents.schoolevents_api.Services;

import com.schoolevents.schoolevents_api.DTO.CommentDTO;
import com.schoolevents.schoolevents_api.mappers.CommentMapper;
import com.schoolevents.schoolevents_api.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.schoolevents.schoolevents_api.models.*;
import java.util.ArrayList;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    private CommentMapper commentMapper;

    public List<CommentDTO> findAll() {
        List<Comment> comments= commentRepository.findAll();
        List<CommentDTO> commentsDTO = new java.util.ArrayList<>(List.of());
         for (Comment comment : comments) {
             commentsDTO.add(commentMapper.commentToCommentDTO(comment));
         }
         return commentsDTO;
    }

    public List<CommentDTO> findByUserId(Long user_id) {
        List<Comment> comments = commentRepository.findByUserId(user_id);
        List<CommentDTO> commentsDTO = new java.util.ArrayList<>(List.of());
        for (Comment comment : comments) {
            commentsDTO.add(commentMapper.commentToCommentDTO(comment));
        }
        return commentsDTO;
    }

    public List<CommentDTO> findByEventId(Long event_id) {
        List<Comment> comments= commentRepository.findByEventId(event_id);
        List<CommentDTO> commentsDTO = new java.util.ArrayList<>(List.of());
        for (Comment comment : comments) {
            commentsDTO.add(commentMapper.commentToCommentDTO(comment));
        }
        return commentsDTO;
    }

    public CommentDTO findById(Long id) {
        Comment comment = commentRepository.findById(id);
        return commentMapper.commentToCommentDTO(comment);
    }

    public CommentDTO save(Comment comment) {
        Comment comment0 = commentRepository.save(comment);
        return commentMapper.commentToCommentDTO(comment0);
    }

    public CommentDTO updateComment(Comment newData, Long id) {
        Comment c = commentRepository.findById(id);
        c.setDescription(newData.getDescription());
        Comment comment = commentRepository.save(c);
        return commentMapper.commentToCommentDTO(comment);
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
