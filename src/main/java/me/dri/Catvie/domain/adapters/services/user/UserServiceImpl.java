package me.dri.Catvie.domain.adapters.services.user;

import me.dri.Catvie.domain.models.dto.user.UserResponseDTO;
import me.dri.Catvie.domain.models.dto.user.UserDTO;
import me.dri.Catvie.domain.models.core.User;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperUserResponsePort;
import me.dri.Catvie.domain.ports.interfaces.user.UserServicePort;
import me.dri.Catvie.domain.ports.repositories.UserRepositoryPort;

import java.util.List;

public class UserServiceImpl implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    private final MapperUserResponsePort mapperUserPort;

    public UserServiceImpl(UserRepositoryPort userRepositoryPort, MapperUserResponsePort mapperUserPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.mapperUserPort = mapperUserPort;
    }


    @Override
    public UserResponseDTO findById(Long id) {
        User userByInfraAdapter = this.userRepositoryPort.findById(id);
        return this.mapperUserPort.converUserToUserResponseDTO(userByInfraAdapter);
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return null;
    }

    @Override
    public UserResponseDTO findByName(String title) {
        return null;
    }

    @Override
    public UserResponseDTO findByEmail(String email) {
        return null;
    }

    @Override
    public void create(UserDTO FilmRequestDTO) {

    }

    @Override
    public void save(UserDTO userDTO) {
        User user = this.mapperUserPort.convertUserDTOToUser(userDTO);
        this.userRepositoryPort.save(user);
    }

    @Override
    public void delete(UserDTO film) {

    }
}
