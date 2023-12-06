package me.dri.Catvie.dozer;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import me.dri.Catvie.domain.enums.UserRole;
import me.dri.Catvie.domain.models.dto.user.UserDTO;
import me.dri.Catvie.domain.models.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DozerTests {



    @Test
    void testSimpleDozer() {
        User user = new User(1L, "diego", "henrique","diego@gmail.com", "codigo1234567", "otkeasjfash", UserRole.USER);
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        UserDTO dest = mapper.map(user, UserDTO.class);
        Assertions.assertEquals(user.getEmail(), dest.email());
    }


}
