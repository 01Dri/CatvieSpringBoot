package me.dri.Catvie.domain.ports.interfaces.user;

import me.dri.Catvie.domain.models.dto.user.UserResponseDTO;
import me.dri.Catvie.domain.models.dto.user.UserDTO;

import java.util.List;

public interface UserServicePort {

    UserResponseDTO findById(Long id);
    List<UserResponseDTO> findAll();
    UserResponseDTO findByName(String title);
    UserResponseDTO findByEmail(String email);
    void create(UserDTO FilmRequestDTO);
    void save(UserDTO film);
    void delete(UserDTO film);

}
