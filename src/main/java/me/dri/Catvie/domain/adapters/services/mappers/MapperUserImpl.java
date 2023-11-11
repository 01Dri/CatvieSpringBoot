package me.dri.Catvie.domain.adapters.services.mappers;

import me.dri.Catvie.domain.models.dto.LoginDTO;
import me.dri.Catvie.domain.models.dto.RegisterDTO;
import me.dri.Catvie.domain.models.dto.UserDTO;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.interfaces.MapperUserPort;

public class MapperUserImpl implements MapperUserPort {

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
}
