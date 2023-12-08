package me.dri.Catvie.unittest.usertests;

import me.dri.Catvie.domain.exceptions.user.NotFoundUser;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.infra.adapters.UserAdapter;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.jpa.UserRepositoryJPA;
import me.dri.Catvie.infra.ports.mappers.MapperUserInfraPort;
import me.dri.Catvie.unittest.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAdapterInfraTest {



    @Mock
    UserRepositoryJPA userRepositoryJPA;

    @Mock
    MapperUserInfraPort mapperUserInfraPort;

    @Mock
    ModelMapper mapperI;

    @InjectMocks
    UserAdapter service;

    MockUser mockUser;
    @BeforeEach
    void setup() {
        this.mockUser = new MockUser();
    }



    @Test
    void testFindById() {
        UserEntity mockUserEntity = this.mockUser.mockUserEntity();
        User mockUser = this.mockUser.mockUser();
        when(this.userRepositoryJPA.findById(1L)).thenReturn(Optional.of(mockUserEntity));
        //when(this.mapperUserInfraPort.convertUserEntityToUser(mockUserEntity)).thenReturn(mockUser);
        var result = this.service.findById(1L);
        assertEquals(mockUserEntity.getId(), result.getId());
        verify(this.userRepositoryJPA, times(1)).findById(1L);
    }


    @Test
    void testExceptionNotFoundUserById() {
        when(this.userRepositoryJPA.findById(1L)).thenReturn(Optional.empty());
        var exception = assertThrows(NotFoundUser.class, () -> this.service.findById(1L));
        assertEquals("Not found user" , exception.getMessage());

    }
}
