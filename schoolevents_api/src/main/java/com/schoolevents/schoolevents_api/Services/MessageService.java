package com.schoolevents.schoolevents_api.Services;


import com.schoolevents.schoolevents_api.DTO.MessageDTO;
import com.schoolevents.schoolevents_api.exception.ElementNotFoundException;
import com.schoolevents.schoolevents_api.mappers.MessageMapper;
import com.schoolevents.schoolevents_api.mappers.UserMapper;
import com.schoolevents.schoolevents_api.models.*;
import com.schoolevents.schoolevents_api.repositories.MessageRepository;
import com.schoolevents.schoolevents_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, MessageMapper messageMapper, UserMapper userMapper, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
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

    public MessageDTO save(MessageDTO messageDTO) {
        if (messageDTO == null) {
            throw new ElementNotFoundException("No se puede guardar un mensaje vacío");
        }

        if (messageDTO.getUser() == null || messageDTO.getUser().getId() == null) {
            throw new ElementNotFoundException("El mensaje debe estar asociado a un usuario válido");
        }

        User user = userRepository.findById(messageDTO.getUser().getId());
        if (user == null) {
            throw new ElementNotFoundException("Usuario no encontrado con id: " + messageDTO.getUser().getId());
        }

        Message messageEntity = messageMapper.messageDTOToMessage(messageDTO);

        messageEntity.setUser(user);

        Message saved = messageRepository.save(messageEntity);

        return messageMapper.messageToMessageDTO(saved);
    }


    public MessageDTO updateMessage(MessageDTO messageDTO, Long id) {
        if (messageDTO == null) {
            throw new ElementNotFoundException("No se puede actualizar un mensaje vacío");
        }

        Message existingMessage = messageRepository.findById(id);
        if (existingMessage == null) {
            throw new ElementNotFoundException("Mensaje no encontrado con el id: " + id);
        }

        existingMessage.setContent(messageDTO.getContent());

        existingMessage.setSend_date(messageDTO.getSend_date());

        if (messageDTO.getUser() == null || messageDTO.getUser().getId() == null) {
            throw new ElementNotFoundException("El mensaje debe estar asociado a un usuario válido");
        }

        User user = userRepository.findById(messageDTO.getUser().getId());
        if (user == null) {
            throw new ElementNotFoundException("Usuario no encontrado con id: " + messageDTO.getUser().getId());
        }
        existingMessage.setUser(user);

        Message updated = messageRepository.save(existingMessage);

        return messageMapper.messageToMessageDTO(updated);
    }


    public void delete(Long id){
        if (messageRepository.findById(id) == null) {
            throw new ElementNotFoundException("Mensaje no encontrado con el id: "+id);
        } else  messageRepository.deleteById(id);
    }
}
