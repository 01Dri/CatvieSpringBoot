package me.dri.Catvie.infra.adapters.mapper;

import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.ports.MapperUserPort;

public class MapperEntityAdapter  implements MapperUserPort {
    @Override
    public UserEntity convertUserToUserEntity(User user, String passEcrypted) {
        return new UserEntity(null, user.getFirstName(), user.getLastName(), user.getEmail(), passEcrypted, null, user.getRole());
    }
}
