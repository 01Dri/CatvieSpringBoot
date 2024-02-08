package me.dri.Catvie.domain.ports.repositories;

import me.dri.Catvie.domain.models.core.User;

import java.util.List;

public interface UserRepositoryPort {

    User findById(Long id);

}
