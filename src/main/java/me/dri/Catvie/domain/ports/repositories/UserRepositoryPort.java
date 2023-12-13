package me.dri.Catvie.domain.ports.repositories;

import me.dri.Catvie.domain.models.core.User;

import java.util.List;

public interface UserRepositoryPort {

    User findById(Long id);

    List<User> findAll();

    User findByName(String title);

    User findByEmail(String email);

    void create(User FilmRequestDTO);

    void save(User film);

    void delete(User film);

    User update(User film);
}
