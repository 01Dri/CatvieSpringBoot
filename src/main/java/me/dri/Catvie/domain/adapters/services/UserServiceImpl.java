package me.dri.Catvie.domain.adapters.services;

import me.dri.Catvie.domain.models.dto.user.UserDTO;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.interfaces.user.MapperUserPort;
import me.dri.Catvie.domain.ports.interfaces.user.UserServicePort;
import me.dri.Catvie.domain.ports.repositories.UserRepositoryPort;

import java.util.List;

public class UserServiceImpl implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    private final MapperUserPort mapperUserPort;

    public UserServiceImpl(UserRepositoryPort userRepositoryPort, MapperUserPort mapperUserPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.mapperUserPort = mapperUserPort;
    }


    @Override
    public UserDTO findById(Long id) {
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }

    @Override
    public UserDTO findByName(String title) {
        return null;
    }

    @Override
    public UserDTO findByEmail(String email) {
        return null;
    }

    @Override
    public void create(UserDTO filmDto) {

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
