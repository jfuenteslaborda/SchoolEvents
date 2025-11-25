package com.schoolevents.schoolevents_api.res;

import com.schoolevents.schoolevents_api.DTO.UserDTO;
import com.schoolevents.schoolevents_api.Services.UserService;
import com.schoolevents.schoolevents_api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<UserDTO> showUsers(){
        return userService.findAll();
    }

    @GetMapping("/by_id/{id}")
    public UserDTO showUserById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/by_email/{email}")
    public UserDTO showUserByEmail(@PathVariable String email){
        return userService.findByEmail(email);
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/update/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.update(user, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }


}
