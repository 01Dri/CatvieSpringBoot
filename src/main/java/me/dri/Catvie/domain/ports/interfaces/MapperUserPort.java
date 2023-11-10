package me.dri.Catvie.domain.ports.interfaces;

import me.dri.Catvie.domain.models.dto.LoginDTO;
import me.dri.Catvie.domain.models.dto.RegisterDTO;
import me.dri.Catvie.domain.models.dto.UserDTO;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.infra.entities.UserEntity;

public interface MapperUserPort {


    User convertRegisterDTOToUser(RegisterDTO user);
    User convertLoginDTOToUser(LoginDTO user);

    UserDTO convertUserToDTO(User user);


    User convertUserDTOToUser(UserDTO userDTO);




}
