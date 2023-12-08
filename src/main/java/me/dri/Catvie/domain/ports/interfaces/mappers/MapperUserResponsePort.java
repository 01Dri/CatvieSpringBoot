package me.dri.Catvie.domain.ports.interfaces.mappers;

import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import me.dri.Catvie.domain.models.dto.user.UserDTO;
import me.dri.Catvie.domain.models.dto.user.UserResponseDTO;
import me.dri.Catvie.domain.models.dto.user.UserResponseFilmRequestDTO;
import me.dri.Catvie.domain.models.entities.User;


public interface MapperUserResponsePort {


    User convertRegisterDTOToUser(RegisterDTO user);
    User convertLoginDTOToUser(LoginDTO user);

    UserDTO convertUserToDTO(User user);


    User convertUserDTOToUser(UserDTO userDTO);

    UserResponseFilmRequestDTO convertUserToUserResponseFilmRequestDTO(User user);


    UserResponseDTO converUserToUserResponseDTO(User user);



}