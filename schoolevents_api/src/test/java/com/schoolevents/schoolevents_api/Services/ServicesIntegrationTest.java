package com.schoolevents.schoolevents_api.Services;

import com.schoolevents.schoolevents_api.DTO.*;
import com.schoolevents.schoolevents_api.exception.ElementNotFoundException;
import com.schoolevents.schoolevents_api.mappers.*;
import com.schoolevents.schoolevents_api.models.*;
import com.schoolevents.schoolevents_api.repositories.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ServicesIntegrationTest {
    @InjectMocks
    EventService eventServiceTest;
    @Mock
    EventRepository eventRepositoryTest;
    @Mock
    EventMapper eventMapperTest;

    @InjectMocks
    UserService userServiceTest;
    @Mock
    UserRepository userRepositoryTest;
    @Mock
    UserMapper userMapperTest;

    @InjectMocks
    CommentService commentServiceTest;
    @Mock
    CommentRepository commentRepositoryTest;
    @Mock
    CommentMapper commentMapperTest;

    @InjectMocks
    SignService  signServiceTest;
    @Mock
    SignRepository signRepositoryTest;
    @Mock
    SignMapper signMapperTest;

    @InjectMocks
    MessageService messageServiceTest;
    @Mock
    MessageRepository messageRepositoryTest;
    @Mock
    MessageMapper messageMapperTest;

    @InjectMocks
    ImageService imageServiceTest;
    @Mock
    ImageRepository imageRepositoryTest;
    @Mock
    ImageMapper imageMapperTest;


    //Ejemplo 1 - Crear Usuario - Negativo (Caso 1)
    @Test
    void saveUser(){
        when(userRepositoryTest.save(any(User.class)))
                .thenThrow(new ElementNotFoundException("El usuario no existe"));

        assertThrows(ElementNotFoundException.class, () -> userRepositoryTest.save(new User()));

        verify(userRepositoryTest, times(1)).save(any(User.class));
    }

    // Ejemplo 2 - Buscar Usuario por Id - Positivo
    @Test
    void findUserById(){
        //Given
        when(this.userRepositoryTest.findById(anyLong()))
                .thenReturn(new User());
        when(this.userMapperTest.userToUserDTO(any(User.class)))
                .thenReturn(new UserDTO());

        //Then
        this.userServiceTest.findById(anyLong());

        //When
        verify(this.userRepositoryTest, times(1)).findById(anyLong());
    }

    // Ejemplo 3 - Crear Evento - Positivo (Caso 2)
    @Test
    void saveEvent(){
        Event event = new Event();
        event.setId(1L);
        event.setDate(LocalDate.now());
        event.setDescription("Descripcion");
        event.setPrice(5.00F);
        event.setNeed_payment(true);
        event.setTitle("Title");
        event.setCapacity(6);


        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(null);
        eventDTO.setDate(LocalDate.now());
        eventDTO.setDescription("Descripcion");
        eventDTO.setPrice(5);
        eventDTO.setNeed_payment(true);
        eventDTO.setTitle("Title");
        eventDTO.setCapacity(6);


        when(eventRepositoryTest.save(any(Event.class)))
                .thenReturn(event);

        when(eventMapperTest.eventDTOToEvent(any(EventDTO.class)))
                .thenReturn(new Event());

        this.eventServiceTest.save(eventDTO);

        verify(eventRepositoryTest, times(1)).save(any(Event.class));
    }

    // Ejemplo 4 - Buscar Evento Por Título - Positivo (Caso 3)
    @Test
    void findByTitlePositive(){
        //Given
        when(this.eventRepositoryTest.findByTitle("Evento simulado"))
                .thenReturn(new Event());
        when(this.eventMapperTest.eventToEventDTO(any(Event.class))).thenReturn(new EventDTO());

        //Then
        this.eventServiceTest.findByTitle("Evento simulado");

        //When
        verify(this.eventRepositoryTest, times(1)).findByTitle("Evento simulado");
    }

    // Ejemplo 5 - Buscar Evento Por Título - Negativo
    @Test
    void findByTitleNegative() {

        when(this.eventRepositoryTest.findByTitle("Evento simulado"))
                .thenThrow(ElementNotFoundException.class);

        assertThrows(ElementNotFoundException.class, () ->
                this.eventServiceTest.findByTitle("Evento simulado")
        );

        verify(this.eventRepositoryTest, times(1))
                .findByTitle("Evento simulado");

        Mockito.verifyNoInteractions(this.eventMapperTest);
    }

    //Ejemplo 6 - Buscar Evento Por Id - Positivo (Caso 4)
    @Test
    void findEventById(){
        when(this.eventRepositoryTest.findById(anyLong()))
                .thenReturn(new Event());
        when(eventMapperTest.eventToEventDTO(any(Event.class)))
                .thenReturn(new EventDTO());

        this.eventServiceTest.findById(anyLong());

        verify(eventRepositoryTest, times(1)).findById(anyLong());
        verify(eventMapperTest, times(1)).eventToEventDTO(any(Event.class));

    }

    //Ejemplo 7 - Modificar Evento - Negativo (Caso 5)
    @Test
    void putEvent(){
        when(eventRepositoryTest.findById(anyLong()))
                .thenThrow(new ElementNotFoundException("El evento no existe"));

        assertThrows(ElementNotFoundException.class, () -> this.eventServiceTest.findById(anyLong()));

        verify(eventRepositoryTest, times(1)).findById(anyLong());
    }

    //Ejemplo 8 - Crear Registro - Positivo (Caso 6)
    @Test
    void createSign() {
        SignDTO signDTO = new SignDTO();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        signDTO.setUser(userDTO);

        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(2L);
        signDTO.setEvent(eventDTO);

        User user = new User();
        Event event = new Event();
        Sign sign = new Sign();
        Sign savedSign = new Sign();
        SignDTO savedSignDTO = new SignDTO();

        when(userRepositoryTest.findById(1L))
                .thenReturn(user);
        when(eventRepositoryTest.findById(2L))
                .thenReturn(event);
        when(signMapperTest.signDTOToSign(signDTO))
                .thenReturn(sign);
        when(signRepositoryTest.save(sign))
                .thenReturn(savedSign);
        when(signMapperTest.signToSignDTO(savedSign))
                .thenReturn(savedSignDTO);

        SignDTO result = signServiceTest.save(signDTO);

        assertNotNull(result);
        verify(userRepositoryTest, times(1)).findById(1L);
        verify(eventRepositoryTest, times(1)).findById(2L);
        verify(signMapperTest, times(1)).signDTOToSign(signDTO);
        verify(signRepositoryTest, times(1)).save(sign);
        verify(signMapperTest, times(1)).signToSignDTO(savedSign);
    }

    //Ejemplo 9 - Subir Imagen - Negativo (Caso 7)
    @Test
    void uploadImage() {
        ImageDTO imageDTO = new ImageDTO();
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(10L);
        imageDTO.setEvent(eventDTO);

        when(eventRepositoryTest.findById(10L))
                .thenReturn(null);

        assertThrows(ElementNotFoundException.class,
                () -> imageServiceTest.save(imageDTO));

        verify(eventRepositoryTest, times(1)).findById(10L);
        Mockito.verifyNoInteractions(imageMapperTest);
        Mockito.verifyNoInteractions(imageRepositoryTest);
    }

    //Ejemplo 10 - Consultar Eventos donde el Usuario Participa - Positivo (Caso 8)
    @Test
    void findSignsByUser() {
        Long userId = 5L;

        Sign sign1 = new Sign();
        Sign sign2 = new Sign();

        List<Sign> signs = List.of(sign1, sign2);

        SignDTO signDTO1 = new SignDTO();
        SignDTO signDTO2 = new SignDTO();

        when(signRepositoryTest.findByUserId(userId))
                .thenReturn(signs);
        when(signMapperTest.signToSignDTO(sign1))
                .thenReturn(signDTO1);
        when(signMapperTest.signToSignDTO(sign2))
                .thenReturn(signDTO2);

        List<SignDTO> result = signServiceTest.findByUserId(userId);

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(signRepositoryTest, times(1)).findByUserId(userId);
        verify(signMapperTest, times(1)).signToSignDTO(sign1);
        verify(signMapperTest, times(1)).signToSignDTO(sign2);
    }

    //Ejemplo 11 - Estadisticas de Eventos - Positivo (Caso 9)
    @Test
    void eventStadistic(){
        EventStadisticsDTO mockDto = Mockito.mock(EventStadisticsDTO.class);

        when(eventRepositoryTest.getEventStadistics())
                .thenReturn(List.of(mockDto));

        this.eventServiceTest.getEventsStadistic();

        verify(eventRepositoryTest, times(2)).getEventStadistics();
    }

    //Ejemplo 12 - Eventos en 2 semanas - Negativo
    @Test
    void eventsInTwoWeeks(){
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusWeeks(2);
        when(eventRepositoryTest.findByDateBetween(start, end))
                .thenThrow(new ElementNotFoundException("El evento no existe"));

        assertThrows(ElementNotFoundException.class, () -> eventRepositoryTest.findByDateBetween(start, end));

        verify(eventRepositoryTest, times(1)).findByDateBetween(start, end);
    }

    //Ejemplo 13 - Crear Comentario - Negativo
    @Test
    void uploadComment_negativo(){
        CommentDTO dto = new CommentDTO();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        dto.setUser(userDTO);

        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(2L);
        dto.setEvent(eventDTO);

        dto.setDescription("Hola");

        when(userRepositoryTest.findById(anyLong()))
                .thenReturn(new User());

        when(eventRepositoryTest.findById(anyLong()))
                .thenReturn(new Event());

        when(commentMapperTest.commentDTOToComment(any(CommentDTO.class)))
                .thenReturn(new Comment());

        when(commentMapperTest.commentToCommentDTO(any(Comment.class)))
                .thenReturn(new CommentDTO());

        when(commentRepositoryTest.save(any(Comment.class)))
                .thenThrow(new ElementNotFoundException("Error al guardar"));

        assertThrows(ElementNotFoundException.class, () -> commentServiceTest.save(dto));

        verify(commentRepositoryTest, times(1)).save(any(Comment.class));
    }




    //Ejemplo 14 - Crear Mensaje - Positivo
    @Test
    void uploadMessage() {
        MessageDTO dto = new MessageDTO();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        dto.setUser(userDTO);

        dto.setContent("Hola mundo");

        User userEntity = new User();
        userEntity.setId(1L);

        Message messageEntity = new Message();
        Message savedEntity = new Message();

        MessageDTO returnedDTO = new MessageDTO();

        when(userRepositoryTest.findById(anyLong()))
                .thenReturn(userEntity);

        when(messageMapperTest.messageDTOToMessage(any(MessageDTO.class)))
                .thenReturn(messageEntity);

        when(messageRepositoryTest.save(any(Message.class)))
                .thenReturn(savedEntity);

        when(messageMapperTest.messageToMessageDTO(any(Message.class)))
                .thenReturn(returnedDTO);

        MessageDTO result = messageServiceTest.save(dto);

        assertNotNull(result);
        verify(userRepositoryTest, times(1)).findById(1L);
        verify(messageRepositoryTest, times(1)).save(messageEntity);
        verify(messageMapperTest, times(1)).messageToMessageDTO(savedEntity);
    }



    //Ejemplo 15 - Actualizar Imagen - Negativa

    @Test
    void updateImage() {
        ImageDTO dto = new ImageDTO();
        dto.setSrc("nueva_ruta.jpg");
        dto.setDescription("Descripción");

        Long id = 1L;

        when(imageRepositoryTest.findById(id)).thenReturn(null);

        assertThrows(ElementNotFoundException.class, () -> imageServiceTest.updateImage(dto, id));

        verify(imageRepositoryTest, times(1)).findById(id);
        verify(imageRepositoryTest, never()).save(any(Image.class));
    }

    //Ejemplo 16 - Eliminar Registro - Positivo
    @Test
    void deleteSign() {
        Long id = 1L;

        when(signRepositoryTest.findById(id)).thenReturn(new Sign());

        signServiceTest.delete(id);

        verify(signRepositoryTest, times(1)).findById(id);
        verify(signRepositoryTest, times(1)).deleteById(id);
    }

    //Ejemplo 17 - Estadisticas de Usuario - Negativo (Caso 10)
    @Test
    void userStadistic(){
        when(userRepositoryTest.getUserStadistic())
                .thenThrow(new ElementNotFoundException("El usuario no existe"));

        assertThrows(ElementNotFoundException.class, () -> userRepositoryTest.getUserStadistic());

        verify(userRepositoryTest, times(1)).getUserStadistic();
    }













}
