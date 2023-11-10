package me.dri.Catvie.infra.ports;

import me.dri.Catvie.infra.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJPA  extends JpaRepository<UserEntity, Long> {

    UserDetails findByEmail(String email);
}
