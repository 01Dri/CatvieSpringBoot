package me.dri.Catvie.unittest.usertests;

import me.dri.Catvie.domain.adapters.services.user.UserServiceImpl;
import me.dri.Catvie.domain.models.dto.user.UserResponseDTO;
import me.dri.Catvie.domain.models.core.User;
import me.dri.Catvie.utils.interfaces.MapperUserResponsePort;
import me.dri.Catvie.domain.ports.repositories.UserRepositoryPort;
import me.dri.Catvie.unittest.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceDomainTest {


    @Mock
    UserRepositoryPort userAdapterInfraPort;


    @Mock
    MapperUserResponsePort mapperUser;


    @InjectMocks
    UserServiceImpl servicePort;

    MockUser mockerUser;

    @BeforeEach
    void setup() {
        this.mockerUser = new MockUser();
    }

    @Test
    void testFindById() {
        User mockUserByInfraPort = this.mockerUser.mockUser();
        UserResponseDTO mockUserResponseDTO = this.mockerUser.mockUserResponseDTO();
        when(this.userAdapterInfraPort.findById(1L)).thenReturn(mockUserByInfraPort);
        when(this.mapperUser.converUserToUserResponseDTO(mockUserByInfraPort)).thenReturn(mockUserResponseDTO);

        var result = this.servicePort.findById(1L);
        assertEquals(mockUserResponseDTO.email(), result.email());
    }
}
