package me.dri.Catvie.infra.auth;

import me.dri.Catvie.infra.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Auth  implements UserDetailsService {

    @Autowired
    UserRepositoryJPA userRepositoryJPA;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepositoryJPA.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Not found"));
    }
}
