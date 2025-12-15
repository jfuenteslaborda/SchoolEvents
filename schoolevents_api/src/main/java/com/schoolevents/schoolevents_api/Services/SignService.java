package com.schoolevents.schoolevents_api.Services;

import com.schoolevents.schoolevents_api.DTO.SignDTO;
import com.schoolevents.schoolevents_api.exception.ElementNotFoundException;
import com.schoolevents.schoolevents_api.mappers.SignMapper;
import com.schoolevents.schoolevents_api.mappers.UserMapper;
import com.schoolevents.schoolevents_api.models.Event;
import com.schoolevents.schoolevents_api.models.Sign;
import com.schoolevents.schoolevents_api.models.User;
import com.schoolevents.schoolevents_api.repositories.EventRepository;
import com.schoolevents.schoolevents_api.repositories.SignRepository;
import com.schoolevents.schoolevents_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignService {

    private final SignRepository signRepository;
    private final SignMapper signMapper;
    private final UserMapper userMapper;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public SignService(SignRepository signRepository, SignMapper signMapper, UserMapper userMapper, EventRepository eventRepository, UserRepository userRepository) {
        this.signRepository = signRepository;
        this.signMapper = signMapper;
        this.userMapper = userMapper;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<SignDTO> findAll(){
        List<Sign> signs = signRepository.findAll();
        List<SignDTO> signsDTO = new java.util.ArrayList<>(List.of());
        for (Sign sign : signs) {
            signsDTO.add(signMapper.signToSignDTO(sign));
        }
        if (signs.isEmpty()){
            throw new ElementNotFoundException("No hay comentarios registrados");
        } else return signsDTO;
    }

    public SignDTO findId(Long id){
        Sign sign = signRepository.findById(id);
        if (sign == null) {
            throw new ElementNotFoundException("No hay registros con este id "+id);
        } else return signMapper.signToSignDTO(sign);
    }

    public List<SignDTO> findByEventId(Long event_id){
        List<Sign> signs = signRepository.findByEventId(event_id);
        List<SignDTO> signsDTO = new java.util.ArrayList<>(List.of());
        for (Sign sign : signs) {
            signsDTO.add(signMapper.signToSignDTO(sign));
        }
        if (signs.isEmpty()){
            throw new ElementNotFoundException("No hay comentarios registrados para el evento con el id: "+event_id);
        } else return signsDTO;
    }

    public List<SignDTO> findByUserId(Long user_id){
        List<Sign> signs = signRepository.findByUserId(user_id);
        List<SignDTO> signsDTO = new java.util.ArrayList<>(List.of());
        for (Sign sign : signs) {
            signsDTO.add(signMapper.signToSignDTO(sign));
        }
        if (signs.isEmpty()){
            throw new ElementNotFoundException("No hay comentarios registrados para el usuario con el id: "+user_id);
        } else return signsDTO;
    }

    public SignDTO save(SignDTO signDTO) {
        if (signDTO == null) {
            throw new ElementNotFoundException("No se puede guardar un registro de firma vacío");
        }

        if (signDTO.getUser() == null || signDTO.getUser().getId() == null) {
            throw new ElementNotFoundException("El registro debe estar asociado a un usuario válido");
        }
        User user = userRepository.findById(signDTO.getUser().getId());
        if (user == null) {
            throw new ElementNotFoundException("Usuario no encontrado con id: " + signDTO.getUser().getId());
        }

        if (signDTO.getEvent() == null || signDTO.getEvent().getId() == null) {
            throw new ElementNotFoundException("El registro debe estar asociado a un evento válido");
        }
        Event event = eventRepository.findById(signDTO.getEvent().getId());
        if (event == null) {
            throw new ElementNotFoundException("Evento no encontrado con id: " + signDTO.getEvent().getId());
        }

        Sign sign = signMapper.signDTOToSign(signDTO);
        sign.setUser(user);
        sign.setEvent(event);

        Sign savedSign = signRepository.save(sign);

        return signMapper.signToSignDTO(savedSign);
    }


    public SignDTO update(SignDTO signDTO, Long id) {
        if (signDTO == null) {
            throw new ElementNotFoundException("No se puede actualizar un registro de firma vacío");
        }

        Sign existingSign = signRepository.findById(id);
        if (existingSign == null) {
            throw new ElementNotFoundException("No se puede actualizar un registro de firma inexistente con id: " + id);
        }

        if (signDTO.getUser() == null || signDTO.getUser().getId() == null) {
            throw new ElementNotFoundException("El registro debe estar asociado a un usuario válido");
        }
        User user = userRepository.findById(signDTO.getUser().getId());
        if (user == null) {
            throw new ElementNotFoundException("Usuario no encontrado con id: " + signDTO.getUser().getId());
        }

        if (signDTO.getEvent() == null || signDTO.getEvent().getId() == null) {
            throw new ElementNotFoundException("El registro debe estar asociado a un evento válido");
        }
        Event event = eventRepository.findById(signDTO.getEvent().getId());
        if (event == null) {
            throw new ElementNotFoundException("Evento no encontrado con id: " + signDTO.getEvent().getId());
        }

        existingSign.setUser(user);
        existingSign.setEvent(event);
        existingSign.setDate(signDTO.getDate());

        Sign updatedSign = signRepository.save(existingSign);

        return signMapper.signToSignDTO(updatedSign);
    }


    public void delete(Long id){
        if (signRepository.findById(id) == null) {
            throw new ElementNotFoundException("No se puede eliminar un comentario sin id");
        } else signRepository.deleteById(id);
    }
}
