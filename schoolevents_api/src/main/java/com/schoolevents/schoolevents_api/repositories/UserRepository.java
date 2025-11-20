package com.schoolevents.schoolevents_api.repositories;

import com.schoolevents.schoolevents_api.models.User;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<User, Long> {
    List<User> findAll();
    User findById(Long id);
    User findByEmail(String email);
    User save(User user);
    void deleteById(Long id);
}
