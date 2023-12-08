package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.infra.ports.auth.EncoderPassword;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncoderPasswordAdapter implements EncoderPassword {
    @Override
    public String encode(String pass) {
        return new BCryptPasswordEncoder().encode(pass);
    }
}
