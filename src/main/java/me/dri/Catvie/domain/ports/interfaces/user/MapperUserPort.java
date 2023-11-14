package me.dri.Catvie.domain.ports.interfaces.user;

import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import me.dri.Catvie.domain.models.dto.user.UserDTO;
import me.dri.Catvie.domain.models.entities.User;


public interface MapperUserPort {


    User convertRegisterDTOToUser(RegisterDTO user);
    User convertLoginDTOToUser(LoginDTO user);

    UserDTO convertUserToDTO(User user);


    User convertUserDTOToUser(UserDTO userDTO);




}
