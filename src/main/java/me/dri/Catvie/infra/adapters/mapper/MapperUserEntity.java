package me.dri.Catvie.infra.adapters.mapper;

import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.ports.mappers.MapperUserInfraPort;
import org.springframework.stereotype.Component;

@Component
public class MapperUserEntity implements MapperUserInfraPort {
    @Override
    public UserEntity convertUserToUserEntity(User user, String passEcrypted) {
        return new UserEntity(null, user.getFirstName(), user.getLastName(), user.getEmail(), passEcrypted, null, user.getRole());
    }

    @Override
    public User convertUserEntityToUser(UserEntity userEntity) {
        return new User(userEntity.getId(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getToken(), userEntity.getRole());
    }
}
