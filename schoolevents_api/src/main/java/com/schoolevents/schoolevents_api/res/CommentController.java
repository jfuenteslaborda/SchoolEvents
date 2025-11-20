package com.schoolevents.schoolevents_api.res;

import com.schoolevents.schoolevents_api.Services.CommentService;
import com.schoolevents.schoolevents_api.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public List<Comment> getComments() {
        return commentService.findAll();
    }

    @GetMapping("/by_user/{id}")
    public List<Comment> getCommentByUserId(@PathVariable("id") long id) {
        return commentService.findByUserId(id);
    }

    @GetMapping("/by_event/{id}")
    public List<Comment> getCommentsByEventId(@PathVariable("id") long id) {
        return commentService.findByEventId(id);
    }

    @GetMapping("{id}")
    public Comment getCommentById(@PathVariable long id) {
        return commentService.findById(id);
    }

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.save(comment);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@RequestBody Comment comment,  @PathVariable long id) {
        return commentService.updateComment(comment, id);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable long id) {
        commentService.deleteById(id);
    }
}
