package me.dri.Catvie.domain.ports.interfaces;

import me.dri.Catvie.domain.models.entities.User;

public interface AuthenticationPort {
    void register(User register);
    void login(User user);
}
