package me.dri.Catvie.infra.ports;

import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.infra.entities.UserEntity;

public interface MapperUserPort {

    UserEntity convertUserToUserEntity(User user, String pass);
}
