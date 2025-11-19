package com.schoolevents.schoolevents_api.Services;


import com.schoolevents.schoolevents_api.models.*;
import com.schoolevents.schoolevents_api.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findAll(){
        return messageRepository.findAll();
    }

    public Message findById(Long id){
        return messageRepository.findById(id);
    }

    public List<Message> findByUserId(Long user_id){
        return messageRepository.findByUserId(user_id);
    }

    public Message save(Message message){
        return messageRepository.save(message);
    }

    public Message update(Message message, Long id){
        return messageRepository.update(message, id);
    }

    public void delete(Long id){
        messageRepository.deleteById(id);
    }
}
