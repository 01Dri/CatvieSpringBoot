package me.dri.Catvie.domain.adapters.services;

import me.dri.Catvie.domain.models.dto.UserDTO;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.interfaces.UserServicePort;
import me.dri.Catvie.domain.ports.repositories.UserRepositoryPort;

import java.util.List;

public class UserServiceImpl implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    public UserServiceImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
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
    public void save(UserDTO film) {

    }

    @Override
    public void delete(UserDTO film) {

    }
}
