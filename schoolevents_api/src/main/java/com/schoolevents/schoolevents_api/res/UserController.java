package com.schoolevents.schoolevents_api.res;

import com.schoolevents.schoolevents_api.Services.MessageService;
import com.schoolevents.schoolevents_api.Services.UserService;
import com.schoolevents.schoolevents_api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> showUsers(User user){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User showUserById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/by_email/{email}")
    public User showUserByEmail(@PathVariable String email){
        return userService.findByEmail(email);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }


}
