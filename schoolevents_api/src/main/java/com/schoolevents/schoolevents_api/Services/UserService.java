package com.schoolevents.schoolevents_api.Services;

import com.schoolevents.schoolevents_api.models.*;
import com.schoolevents.schoolevents_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User update(User user, Long id){
        User u = userRepository.findById(id);
        u.setEmail(user.getEmail());
        u.setDate(user.getDate());
        u.setPassword(user.getPassword());
        u.setPhoto(user.getPhoto());
        u.setFull_name(user.getFull_name());
        u.setIs_Admin(user.getIs_Admin());
        return userRepository.save(u);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
