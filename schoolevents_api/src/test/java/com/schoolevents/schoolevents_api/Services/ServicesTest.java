package com.schoolevents.schoolevents_api.Services;

import com.schoolevents.schoolevents_api.DTO.EventDTO;
import com.schoolevents.schoolevents_api.DTO.UserDTO;
import com.schoolevents.schoolevents_api.exception.ElementNotFoundException;
import com.schoolevents.schoolevents_api.mappers.*;
import com.schoolevents.schoolevents_api.models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServicesTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;
    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private SignService signService;
    @Autowired
    private SignMapper signMapper;

    private User user1;
    private User user2;
    private Event event1;
    private Event event2;

    @BeforeAll
    void setupDatabase() {

        User tempUser1 = new User("Juan Pérez","juan@gmail.com","123","photo1.jpg",LocalDate.now(),0);
        User tempUser2 = new User("Ana López","ana@gmail.com","456","photo2.jpg",LocalDate.now(),0);

        UserDTO savedUserDTO1 = userService.save(userMapper.userToUserDTO(tempUser1));
        UserDTO savedUserDTO2 = userService.save(userMapper.userToUserDTO(tempUser2));

        user1 = userMapper.userDTOToUser(savedUserDTO1);
        user2 = userMapper.userDTOToUser(savedUserDTO2);

        Event tempEvent1 = new Event("Concierto","Concierto en vivo",15F,100,LocalDate.now().plusDays(5),true,"event1.jpg");
        Event tempEvent2 = new Event("Teatro","Obra teatral",0F,80,LocalDate.now().plusDays(10),false,"event2.jpg");

        EventDTO savedEventDTO1 = eventService.save(eventMapper.eventToEventDTO(tempEvent1));
        EventDTO savedEventDTO2 = eventService.save(eventMapper.eventToEventDTO(tempEvent2));

        event1 = eventMapper.eventDTOToEvent(savedEventDTO1);
        event2 = eventMapper.eventDTOToEvent(savedEventDTO2);

        commentService.save(commentMapper.commentToCommentDTO(new Comment("¡Genial!", LocalDate.now(), user1, event1)));
        commentService.save(commentMapper.commentToCommentDTO(new Comment("Me encanta", LocalDate.now(), user2, event2)));

        imageService.save(imageMapper.imageToImageDTO(new Image("img1.jpg","Escenario",event1)));
        imageService.save(imageMapper.imageToImageDTO(new Image("img2.jpg","Cartel",event2)));

        messageService.save(messageMapper.messageToMessageDTO(new Message("¿Hay entradas?",LocalDate.now(),user1)));
        messageService.save(messageMapper.messageToMessageDTO(new Message("¿Dónde es?",LocalDate.now(),user2)));

        signService.save(signMapper.signToSignDTO(new Sign(user1,event1,LocalDate.now())));
        signService.save(signMapper.signToSignDTO(new Sign(user2,event2,LocalDate.now())));
    }

    // -- TESTS --

    //Crear un Usario - Positivo
    @Test
    void saveUserTestPositive() {
        User user = new User("Prueba", "prueba@gmail.com", "123123123", "data/photo", LocalDate.now(), 0);
        assertNotNull("creado", userService.save(userMapper.userToUserDTO(user)));
    }

    //Crear un Usuario - Negativo
    @Test
    void saveUserTestNegative() {
        User user = new User("Prueba", "", "123123123", "data/photo", LocalDate.now(), 0);
        assertThrows(IllegalArgumentException.class, () -> {
            userService.save(userMapper.userToUserDTO(user));
        });
    }

    //Crear un Evento - Positivo
    @Test
    void saveEventTestPositive() {
        Event event = new Event("Evento", "Mu chulo", 20.00F, 10, LocalDate.now(), true, "data/photo");
        assertNotNull("creado", eventService.save(eventMapper.eventToEventDTO(event)));
    }

    //Crear un Evento - Negativo
    @Test
    void saveEventTestNegative() {
        Event event = new Event("Evento", "Mu chulo", 20.00F, 2, LocalDate.now(), true, "data/photo");
        assertThrows(IllegalArgumentException.class, () -> {
            eventService.save(eventMapper.eventToEventDTO(event));
        });
    }

    //Buscar un Evento - Positivo
    @Test
    void findEventByIdTestPositive() {
        Event event = eventMapper.eventDTOToEvent(eventService.findById(1L));
        assertNotNull("Encontrado", event);
    }

    //Buscar un Evento - Negativo
    @Test
    void findEventByIdTestNegative() {
        assertThrows(ElementNotFoundException.class, () -> {
            eventService.findById(8L);
        });
    }

    //Busca una lista de Eventos filtrados - Positivo
    @Test
    void findEventsByFilterTestPositive() {
        List<EventDTO> events = eventService.findByDate(LocalDate.now().plusDays(5));
        assertNotNull("Encontrado", events);
    }

    //Busca una lista de Eventos filtrados - Negativo
    @Test
    void findEventsByFilterTestNegative() {
        assertThrows(ElementNotFoundException.class, () -> {
            eventService.findByDate(LocalDate.now().plusDays(30));
        });
    }

    //Actualiza el Evento buscado - Positivo
    @Test
    void updateEventTestPositive() {
        Event tempEvent1 = new Event("Concierto0","Concierto en vivo",15F,100,LocalDate.now().plusDays(5),true,"event1.jpg");
        assertNotNull("Actualizado", eventService.updateEvent(eventMapper.eventToEventDTO(tempEvent1), 1L));
    }

    //Actualiza el Evento buscado - Negativo
    @Test
    void updateEventTestNegative() {
        assertThrows(ElementNotFoundException.class, () -> {
            Event tempEvent1 = new Event("Concierto0","Concierto en vivo",15F,100,LocalDate.now().plusDays(5),true,"event1.jpg");
            eventService.updateEvent(eventMapper.eventToEventDTO(tempEvent1), 10L);
        });
    }
}
