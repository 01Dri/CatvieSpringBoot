package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.repositories.UserRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAdapter  implements UserRepositoryPort {
    @Override
    public User findById(Long id) {
        return null;
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
    public void create(User filmDto) {

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
