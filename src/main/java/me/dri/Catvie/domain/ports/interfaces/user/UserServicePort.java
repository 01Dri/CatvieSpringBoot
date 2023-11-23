package me.dri.Catvie.domain.ports.interfaces.user;

import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.dto.user.UserDTO;

import java.util.List;

public interface UserServicePort {

    UserDTO findById(Long id);
    List<UserDTO> findAll();
    UserDTO findByName(String title);
    UserDTO findByEmail(String email);
    void create(UserDTO filmDto);
    void save(UserDTO film);
    void delete(UserDTO film);

}