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
        return userRepository.update(user, id);
    }

    public void delete(Long id){
        userRepository.delete(id);
    }
}
