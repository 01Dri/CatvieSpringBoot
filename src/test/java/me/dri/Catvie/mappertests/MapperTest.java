package me.dri.Catvie.mappertests;

import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.unittest.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

public class MapperTest {



    MockUser mockUser;
    @BeforeEach
    void setup() {
        mockUser = new MockUser();
    }


    @Test
    void testMapper() {


        User user = this.mockUser.mockUser();
        ModelMapper modelMapper = new ModelMapper();
        UserDtoM userDTO = modelMapper.map(user, UserDtoM.class);
        assertEquals(user.getEmail(), userDTO.getEmail());


    }
}
