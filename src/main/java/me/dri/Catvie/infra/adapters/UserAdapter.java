package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.domain.exceptions.user.NotFoundUser;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.repositories.UserRepositoryPort;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.jpa.UserRepositoryJPA;
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
        System.out.println("teste");
        UserEntity userById = this.userRepositoryJPA.findById(id).orElseThrow(() -> new NotFoundUser("Not found user"));
        return this.mapperI.map(userById, User.class);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByName(String title) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public void create(User FilmRequestDTO) {

    }

    @Override
    public void save(User film) {

    }

    @Override
    public void delete(User film) {

    }

    @Override
    public User update(User user) {
        return null;
    }
}
