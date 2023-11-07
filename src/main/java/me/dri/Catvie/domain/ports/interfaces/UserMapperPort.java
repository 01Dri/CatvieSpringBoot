package me.dri.Catvie.domain.ports.interfaces;

import me.dri.Catvie.domain.models.dto.UserDTO;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.User;

import java.util.List;

public interface UserMapperPort {


    UserDTO convertUserToDto(User user);
    List<UserDTO> convertListUserToDto(List<User> users);

    User convertDtoToUser(UserDTO user);

}
