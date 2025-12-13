package com.schoolevents.schoolevents_api.Services;

import com.schoolevents.schoolevents_api.DTO.UserDTO;
import com.schoolevents.schoolevents_api.DTO.UserStadisticsDTO;
import com.schoolevents.schoolevents_api.exception.ElementNotFoundException;
import com.schoolevents.schoolevents_api.mappers.UserMapper;
import com.schoolevents.schoolevents_api.models.*;
import com.schoolevents.schoolevents_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> findAll(){
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = new java.util.ArrayList<>(List.of());
        for (User user : users){
            usersDTO.add(userMapper.userToUserDTO(user));
        }
        if (users.isEmpty()){
            throw new ElementNotFoundException("No hay usuarios registrados");
        } else return usersDTO;
    }

    public UserDTO findById(Long id){
        User user = userRepository.findById(id);
        if (user == null) {
            throw new ElementNotFoundException("Usuario no encontrado con el id: "+id);
        } else return userMapper.userToUserDTO(user);
    }

    public UserStadisticsDTO getUserStadistics(){
        if (userRepository.getUserStadistic() == null){
            return new UserStadisticsDTO();
        } else return userRepository.getUserStadistic();
    }

    public UserDTO findByEmail(String email){
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ElementNotFoundException("Usuario no encontrado con el email: "+email);
        } else return userMapper.userToUserDTO(user);
    }

    public UserDTO save(UserDTO userDTO) {
        if (userDTO == null) {
            throw new ElementNotFoundException("No se puede guardar un usuario vacío");
        }

        if (userDTO.getFull_name() == null || userDTO.getFull_name().isBlank()) {
            throw new IllegalArgumentException("El nombre del usuario no puede estar vacío");
        }
        if (userDTO.getEmail() == null || userDTO.getEmail().isBlank()) {
            throw new IllegalArgumentException("El email del usuario no puede estar vacío");
        }
        if (userDTO.getDate() == null) {
            throw new IllegalArgumentException("Debe existir una fecha de registro para el usuario");
        }
        if (userDTO.getIs_Admin() == null) {
            throw new IllegalArgumentException("El usuario debe tener un rol definido");
        }

        User existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser != null && !existingUser.getId().equals(userDTO.getId())) {
            throw new ElementNotFoundException("Ya existe un usuario con el email: " + userDTO.getEmail());
        }

        User userEntity = userMapper.userDTOToUser(userDTO);
        User savedUser = userRepository.save(userEntity);

        return userMapper.userToUserDTO(savedUser);
    }


    public UserDTO update(UserDTO userDTO, Long id) {
        if (userDTO == null) {
            throw new ElementNotFoundException("No se puede actualizar un usuario vacío");
        }

        User existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser != null && !existingUser.getId().equals(userDTO.getId())) {
            throw new ElementNotFoundException("Ya existe un usuario con el email: " + userDTO.getEmail());
        }

        User existingUser0 = userRepository.findById(id);
        if (existingUser0 == null) {
            throw new ElementNotFoundException("Usuario no encontrado con el id: " + id);
        }

        existingUser.setEmail(userDTO.getEmail());
        existingUser.setDate(userDTO.getDate());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setPhoto(userDTO.getPhoto());
        existingUser.setFull_name(userDTO.getFull_name());
        existingUser.setIs_Admin(userDTO.getIs_Admin());

        User updatedUser = userRepository.save(existingUser);
        return userMapper.userToUserDTO(updatedUser);
    }


    public void delete(Long id){
        if (userRepository.findById(id) == null) {
            throw new ElementNotFoundException("No se puede eliminar un usuario sin id");
        } else userRepository.deleteById(id);
    }
}
