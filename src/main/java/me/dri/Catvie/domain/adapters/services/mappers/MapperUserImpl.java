package me.dri.Catvie.domain.adapters.services.mappers;

import me.dri.Catvie.domain.models.dto.UserDTO;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.interfaces.UserMapperPort;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUserImpl implements UserMapperPort {

    public MapperUserImpl() {}


    @Override
    public UserDTO convertUserToDto(User user) {
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getToken(), user.getRole());
    }

    @Override
    public List<UserDTO> convertListUserToDto(List<User> users) {
        return users.stream().map(user -> new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getToken(), user.getRole())).collect(Collectors.toList());
    }

    @Override
    public User convertDtoToUser(UserDTO user) {
        return new User(null, user.firstName(), user.lastName(), user.email(), user.password(), user.token(), user.role());
    }
}
