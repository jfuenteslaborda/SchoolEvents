package com.schoolevents.schoolevents_api.res;

import com.schoolevents.schoolevents_api.DTO.CommentDTO;
import com.schoolevents.schoolevents_api.Services.CommentService;
import com.schoolevents.schoolevents_api.models.Comment;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    public List<CommentDTO> getComments() {
        return commentService.findAll();
    }

    @GetMapping("/by_user/{id}")
    public List<CommentDTO> getCommentByUserId(@Valid @PathVariable("id") long id) {
        return commentService.findByUserId(id);
    }

    @GetMapping("/by_event/{id}")
    public List<CommentDTO> getCommentsByEventId(@Valid @PathVariable("id") long id) {
        return commentService.findByEventId(id);
    }

    @GetMapping("/by_id/{id}")
    public CommentDTO getCommentById(@Valid @PathVariable long id) {
        return commentService.findById(id);
    }

    @PostMapping("/create")
    public CommentDTO addComment(@Valid @RequestBody CommentDTO commentDTO) {
        return commentService.save(commentDTO);
    }

    @PutMapping("/update/{id}")
    public CommentDTO updateComment(@Valid @RequestBody Comment comment, @Valid @PathVariable long id) {
        return commentService.updateComment(comment, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteComment(@Valid @PathVariable long id) {
        commentService.deleteById(id);
    }
}
