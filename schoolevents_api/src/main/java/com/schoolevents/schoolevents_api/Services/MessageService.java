package com.schoolevents.schoolevents_api.Services;


import com.schoolevents.schoolevents_api.DTO.MessageDTO;
import com.schoolevents.schoolevents_api.exception.ElementNotFoundException;
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
        if (messages.isEmpty()){
            throw new ElementNotFoundException("No hay mensajes registrados");
        } else return messagesDTO;
    }

    public MessageDTO findById(Long id){
        Message m = messageRepository.findById(id);
        if (m == null) {
            throw new ElementNotFoundException("Mensaje no encontrado con el id: "+id);
        } else return messageMapper.messageToMessageDTO(m);
    }

    public List<MessageDTO> findByUserId(Long user_id){
        List<Message> messages = messageRepository.findByUserId(user_id);
        List<MessageDTO> messagesDTO = new java.util.ArrayList<>(List.of());
        for (Message message : messages) {
            messagesDTO.add(messageMapper.messageToMessageDTO(message));
        }
        if (messages.isEmpty()){
            throw new ElementNotFoundException("No hay mensajes registrados para el usuario con el id: "+user_id);
        } else return messagesDTO;
    }

    public MessageDTO save(Message message){
        if (message == null) {
            throw new ElementNotFoundException("No se puede guardar un mensaje vacio");
        } else {
            Message m = messageRepository.save(message);
            return messageMapper.messageToMessageDTO(m);
        }
    }

    public MessageDTO updateMessage(Message message, Long id){
        if (message == null) {
            throw new ElementNotFoundException("No se puede actualizar un mensaje vacio");
        } else {
            if (messageRepository.findById(id) == null) {
                throw new ElementNotFoundException("Mensaje no encontrado con el id: "+id);
            } else {
                Message m = messageRepository.findById(id);
                m.setContent(message.getContent());
                m.setUser(message.getUser());
                m.setSend_date(message.getSend_date());
                Message m0 = messageRepository.save(m);
                return messageMapper.messageToMessageDTO(m0);
            }
        }
    }

    public void delete(Long id){
        if (messageRepository.findById(id) == null) {
            throw new ElementNotFoundException("Mensaje no encontrado con el id: "+id);
        } else  messageRepository.deleteById(id);
    }
}
