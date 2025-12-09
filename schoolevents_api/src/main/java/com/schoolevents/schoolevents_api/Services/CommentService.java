package com.schoolevents.schoolevents_api.Services;

import com.schoolevents.schoolevents_api.DTO.CommentDTO;
import com.schoolevents.schoolevents_api.exception.ElementNotFoundException;
import com.schoolevents.schoolevents_api.mappers.CommentMapper;
import com.schoolevents.schoolevents_api.repositories.CommentRepository;
import com.schoolevents.schoolevents_api.repositories.EventRepository;
import com.schoolevents.schoolevents_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.schoolevents.schoolevents_api.models.*;

import java.util.List;
import java.util.ArrayList;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper, EventRepository eventRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<CommentDTO> findAll() {
        List<Comment> comments= commentRepository.findAll();
        List<CommentDTO> commentsDTO = new ArrayList<>(List.of());
         for (Comment comment : comments) {
             commentsDTO.add(commentMapper.commentToCommentDTO(comment));
         }
         if (comments.isEmpty()){
             throw new ElementNotFoundException("No hay comentarios registrados");
         }else return commentsDTO;
    }

    public List<CommentDTO> findByUserId(Long user_id) {
        List<Comment> comments = commentRepository.findByUserId(user_id);
        List<CommentDTO> commentsDTO = new ArrayList<>(List.of());
        for (Comment comment : comments) {
            commentsDTO.add(commentMapper.commentToCommentDTO(comment));
        }
        if (comments.isEmpty()){
            throw new ElementNotFoundException("El usuario con el id: " + user_id + " no tiene comentarios");
        }else return commentsDTO;
    }

    public List<CommentDTO> findByEventId(Long event_id) {
        List<Comment> comments= commentRepository.findByEventId(event_id);
        List<CommentDTO> commentsDTO = new ArrayList<>(List.of());
        for (Comment comment : comments) {
            commentsDTO.add(commentMapper.commentToCommentDTO(comment));
        }
        if (comments.isEmpty()){
            throw new ElementNotFoundException("El evento con el id: " + event_id + " no tiene comentarios");
        } else return commentsDTO;
    }

    public CommentDTO findById(Long id) {
        Comment comment = commentRepository.findById(id);
        if (comment == null) {
            throw new ElementNotFoundException("Comentario no encontrado con el id: "+id);
        }else{
            return commentMapper.commentToCommentDTO(comment);
        }
    }

    public CommentDTO save(Comment comment) {
        Long userId = comment.getUser().getId();

        User user = userRepository.findById(userId);

        Long eventId = comment.getEvent().getId();

        Event event = eventRepository.findById(eventId);

        comment.setUser(user);
        comment.setEvent(event);

        if (commentRepository.save(comment) == null) {
            throw new ElementNotFoundException("No se pudo guardar el comentario");
        }else {
            Comment saved = commentRepository.save(comment);
            return commentMapper.commentToCommentDTO(saved);
        }
    }

    public CommentDTO updateComment(Comment newData, Long id) {

        if (commentRepository.findById(id) == null) {
            throw new ElementNotFoundException("Comentario no encontrado con el id: "+id);
        } else {
            Comment c = commentRepository.findById(id);
            c.setDescription(newData.getDescription());
            Comment comment = commentRepository.save(c);
            return commentMapper.commentToCommentDTO(comment);
        }
    }

    public void deleteById(Long id) {
        if (commentRepository.findById(id) == null) {
            throw new ElementNotFoundException("Comentario no encontrado con el id: "+id);
        } else {
            commentRepository.deleteById(id);
        }
    }
}
