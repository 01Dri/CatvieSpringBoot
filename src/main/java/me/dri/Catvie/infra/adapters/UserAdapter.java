package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.domain.exceptions.user.NotFoundUser;
import me.dri.Catvie.domain.models.core.User;
import me.dri.Catvie.domain.ports.repositories.UserRepositoryPort;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.repositoriesjpa.UserRepositoryJPA;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAdapter  implements UserRepositoryPort {


    private final UserRepositoryJPA userRepositoryJPA;

    private final ModelMapper mapperI;

    @Autowired
    public UserAdapter(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
        this.mapperI = new ModelMapper();
    }


    @Override
    public User findById(Long id) {
        UserEntity userById = this.userRepositoryJPA.findById(id).orElseThrow(() -> new NotFoundUser("Not found user"));
        return this.mapperI.map(userById, User.class);
    }

}
