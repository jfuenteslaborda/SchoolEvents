package com.schoolevents.schoolevents_api.Services;


import com.schoolevents.schoolevents_api.DTO.MessageDTO;
import com.schoolevents.schoolevents_api.mappers.MessageMapper;
import com.schoolevents.schoolevents_api.models.*;
import com.schoolevents.schoolevents_api.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageService(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    public List<MessageDTO> findAll(){
        List<Message> messages = messageRepository.findAll();
        List<MessageDTO> messagesDTO = new java.util.ArrayList<>(List.of());
        for (Message message : messages) {
            messagesDTO.add(messageMapper.messageToMessageDTO(message));
        }
        return messagesDTO;
    }

    public MessageDTO findById(Long id){
        Message m = messageRepository.findById(id);
        return messageMapper.messageToMessageDTO(m);
    }

    public List<MessageDTO> findByUserId(Long user_id){
        List<Message> messages = messageRepository.findByUserId(user_id);
        List<MessageDTO> messagesDTO = new java.util.ArrayList<>(List.of());
        for (Message message : messages) {
            messagesDTO.add(messageMapper.messageToMessageDTO(message));
        }
        return messagesDTO;
    }

    public MessageDTO save(Message message){
        Message m = messageRepository.save(message);
        return messageMapper.messageToMessageDTO(m);
    }

    public MessageDTO updateMessage(Message message, Long id){
        Message m = messageRepository.findById(id);
        m.setContent(message.getContent());
        m.setUser(message.getUser());
        m.setSend_date(message.getSend_date());
        Message m0 = messageRepository.save(m);
        return messageMapper.messageToMessageDTO(m0);
    }

    public void delete(Long id){
        messageRepository.deleteById(id);
    }
}
