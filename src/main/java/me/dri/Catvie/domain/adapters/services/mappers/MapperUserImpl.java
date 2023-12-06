package me.dri.Catvie.domain.adapters.services.mappers;

import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import me.dri.Catvie.domain.models.dto.user.UserDTO;
import me.dri.Catvie.domain.models.dto.user.UserResponseDTO;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperUserDomainPort;

public class MapperUserImpl implements MapperUserDomainPort {

    public MapperUserImpl(){}

    @Override
    public User convertRegisterDTOToUser(RegisterDTO user) {
        return new User(null, user.firstName(), user.lastName(), user.email(), user.password(), null, user.role());
    }

    @Override
    public User convertLoginDTOToUser(LoginDTO user) {
        return null;
    }

    @Override
    public UserDTO convertUserToDTO(User user) {
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getToken(), user.getRole());
    }

    @Override
    public User convertUserDTOToUser(UserDTO userDTO) {
        return new User(null, userDTO.firstName(), userDTO.lastName(), userDTO.email(), userDTO.password(), userDTO.token(), userDTO.role());
    }

    @Override
    public UserResponseDTO convertUserDTOToResponseDTO(User user) {
        return new UserResponseDTO(user.getId());
    }
}
