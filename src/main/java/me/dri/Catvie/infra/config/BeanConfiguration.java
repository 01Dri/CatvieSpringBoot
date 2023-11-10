package me.dri.Catvie.infra.config;


import me.dri.Catvie.domain.adapters.services.AuthenticationServiceImpl;
import me.dri.Catvie.domain.adapters.services.FilmServiceImpl;
import me.dri.Catvie.domain.adapters.services.mappers.MapperUserImpl;
import me.dri.Catvie.domain.ports.interfaces.*;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.domain.adapters.services.mappers.MapperFilmImpl;
import me.dri.Catvie.domain.adapters.services.mappers.MapperEntitiesImpl;
import me.dri.Catvie.domain.ports.repositories.UserRepositoryPort;
import me.dri.Catvie.infra.adapters.mapper.MapperEntityAdapter;
import me.dri.Catvie.infra.tokens.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    MapperEntitiesPort mapperEntitiesPort() {
        return new MapperFilmImpl();
    }

    @Bean
    MapperEntities mapperEntities() {
        return new MapperEntitiesImpl();
    }

    @Bean
    MapperUserPort mapperUserPort() {
        return new MapperUserImpl();
    }

    @Bean
    me.dri.Catvie.infra.ports.MapperUserPort mapperUserAdapter() {
        return new MapperEntityAdapter();
    }
    @Bean
    FilmServicePort filmServicePort(FilmRepositoryPort filmRepositoryPort, MapperEntitiesPort mapperEntitiesPort) {
        return new FilmServiceImpl(filmRepositoryPort, mapperEntitiesPort);
    }

    @Bean
    TokenServicesPort tokenServicesPort() {
        return new TokenService();
    }


    @Bean
    AuthenticationServicePort userServicePort(AuthenticationPort authenticationPort, MapperUserPort mapperUserPort, TokenServicesPort tokenServicesPort, UserRepositoryPort userRepositoryPort) {
        return new AuthenticationServiceImpl(authenticationPort, tokenServicesPort, mapperUserPort, userRepositoryPort);
    }



}
