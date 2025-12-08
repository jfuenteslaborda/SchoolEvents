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

    public UserDTO save(User user){
        if (user == null) {
            throw new ElementNotFoundException("No se puede guardar un usuario vacio");
        } else {
            User u = userRepository.save(user);
            return userMapper.userToUserDTO(u);
        }
    }

    public UserDTO update(User user, Long id){
        if (user == null) {
            throw new ElementNotFoundException("No se puede actualizar un usuario vacio");
        } else {
            if (userRepository.findById(id) == null) {
                throw new ElementNotFoundException("Usuario no encontrado con el id: "+id);
            } else {
                User u = userRepository.findById(id);
                u.setEmail(user.getEmail());
                u.setDate(user.getDate());
                u.setPassword(user.getPassword());
                u.setPhoto(user.getPhoto());
                u.setFull_name(user.getFull_name());
                u.setIs_Admin(user.getIs_Admin());
                User u0 = userRepository.save(u);
                return userMapper.userToUserDTO(u0);
            }
        }
    }

    public void delete(Long id){
        if (userRepository.findById(id) == null) {
            throw new ElementNotFoundException("No se puede eliminar un usuario sin id");
        } else userRepository.deleteById(id);
    }
}
