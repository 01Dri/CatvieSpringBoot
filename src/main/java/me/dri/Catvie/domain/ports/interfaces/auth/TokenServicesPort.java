package me.dri.Catvie.domain.ports.interfaces.auth;

import me.dri.Catvie.infra.entities.UserEntity;

import java.time.Instant;

public interface TokenServicesPort {

    String generateToken(UserEntity user);
    Instant generateExpirationDate();
    String validateToken(String token);

}
