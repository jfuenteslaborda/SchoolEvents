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

    public Message updateMessage(Message message, Long id){
        Message m = messageRepository.findById(id);
        m.setMessage(message.getMessage());
        m.setUser(message.getUser());
        m.setSend_date(message.getSend_date());
        return messageRepository.save(m);
    }

    public void delete(Long id){
        messageRepository.deleteById(id);
    }
}
