package me.dri.Catvie.domain.ports.interfaces.mappers;

import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import me.dri.Catvie.domain.models.dto.user.UserDTO;
import me.dri.Catvie.domain.models.dto.user.UserResponseDTO;
import me.dri.Catvie.domain.models.entities.User;


public interface MapperUserDomainPort {


    User convertRegisterDTOToUser(RegisterDTO user);
    User convertLoginDTOToUser(LoginDTO user);

    UserDTO convertUserToDTO(User user);


    User convertUserDTOToUser(UserDTO userDTO);

    UserResponseDTO convertUserDTOToResponseDTO(User user);




}
