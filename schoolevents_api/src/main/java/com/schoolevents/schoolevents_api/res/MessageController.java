package com.schoolevents.schoolevents_api.res;

import com.schoolevents.schoolevents_api.Services.MessageService;
import com.schoolevents.schoolevents_api.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/all")
    public List<Message> getMessages() {
        return messageService.findAll();
    }

    @GetMapping("/by_id/{id}")
    public Message getMessage(@PathVariable Long id) {
        return messageService.findById(id);
    }

    @GetMapping("/by_user/{id}")
    public List<Message> getMessagesByUser(@PathVariable Long id){
        return messageService.findByUserId(id);
    }

    @PostMapping("/create")
    public Message addMessage(@RequestBody Message message){
        return messageService.save(message);
    }

    @PutMapping("/update/{id}")
    public Message updateMessage(@PathVariable Long id, @RequestBody Message message){
        return messageService.updateMessage(message, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMessage(@PathVariable Long id){
        messageService.delete(id);
    }


}
