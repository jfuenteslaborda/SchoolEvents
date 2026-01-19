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

    // Ejemplo 1 - Buscar Evento Por Titulo
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

    // Ejemplo 2 - Buscar Evento Por Titulo - Negativo
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

    @InjectMocks
    UserService userServiceTest;
    @Mock
    UserRepository userRepositoryTest;
    @Mock
    UserMapper userMapperTest;

    // Ejemplo 3 - Buscar Usuario por Id - Positivo
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

    @InjectMocks
    CommentService commentServiceTest;
    @Mock
    CommentRepository commentRepositoryTest;
    @Mock
    CommentMapper commentMapperTest;

    //Ejemplo 4 - Crear Comentario - Negativo
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

    @InjectMocks
    MessageService messageServiceTest;
    @Mock
    MessageRepository messageRepositoryTest;
    @Mock
    MessageMapper messageMapperTest;


    //Ejemplo 5 - Crear Mensaje - Positivo
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

    @InjectMocks
    ImageService imageServiceTest;

    @Mock
    ImageRepository imageRepositoryTest;

    //Ejemplo 6 - Actualizar Imagen - Negativa

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

    @InjectMocks
    SignService signServiceTest;
    @Mock
    SignRepository signRepositoryTest;

    //Ejemplo 7 - Eliminar Registro - Positivo
    @Test
    void deleteSign_positivo() {
        Long id = 1L;

        when(signRepositoryTest.findById(id)).thenReturn(new Sign());

        signServiceTest.delete(id);

        verify(signRepositoryTest, times(1)).findById(id);
        verify(signRepositoryTest, times(1)).deleteById(id);
    }








}
