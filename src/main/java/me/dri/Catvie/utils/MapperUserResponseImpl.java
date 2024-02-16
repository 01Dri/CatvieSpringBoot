package me.dri.Catvie.utils;

import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import me.dri.Catvie.domain.models.dto.user.UserDTO;
import me.dri.Catvie.domain.models.dto.user.UserResponseDTO;
import me.dri.Catvie.domain.models.dto.user.UserResponseFilmRequestDTO;
import me.dri.Catvie.domain.models.core.User;
import me.dri.Catvie.utils.interfaces.MapperUserResponsePort;

public class MapperUserResponseImpl implements MapperUserResponsePort {

    public MapperUserResponseImpl() {
    }

    @Override
    public User convertRegisterDTOToUser(RegisterDTO user) {
        return new User(null, user.firstName(), user.lastName(), user.email(), user.password(), null, user.role());
    }


    @Override
    public User convertUserDTOToUser(UserDTO userDTO) {
        return new User(null, userDTO.firstName(), userDTO.lastName(), userDTO.email(), userDTO.password(), userDTO.token(), userDTO.role());
    }

    @Override
    public UserResponseFilmRequestDTO convertUserToUserResponseFilmRequestDTO(User user) {
            return new UserResponseFilmRequestDTO(user.getId());
    }

    @Override
    public UserResponseDTO converUserToUserResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),user.getRole());
    }

}

