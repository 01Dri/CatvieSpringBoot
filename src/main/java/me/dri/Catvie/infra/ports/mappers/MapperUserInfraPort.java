package me.dri.Catvie.infra.ports.mappers;

import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.infra.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public interface MapperUserInfraPort {

    UserEntity convertUserToUserEntity(User user, String pass);

    User convertUserEntityToUser(UserEntity userEntity);
}
