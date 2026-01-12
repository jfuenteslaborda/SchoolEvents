package com.schoolevents.schoolevents_api.Services;

import com.schoolevents.schoolevents_api.mappers.UserMapper;
import com.schoolevents.schoolevents_api.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
class ServicesTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;



    @Test
    void saveUserTestPositive() {

        //Given
        User user = new User("Prueba", "prueba@gmail.com", "123123123", "data/photo", LocalDate.now(), 0);

        //Then
        userService.save(userMapper.userToUserDTO(user));

        //When
        assertNotNull("creado", user);
    }

    @Test
    void saveUserTestNegative() {

    }
}
