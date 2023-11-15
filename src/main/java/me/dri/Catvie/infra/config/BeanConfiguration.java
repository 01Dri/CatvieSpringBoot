package me.dri.Catvie.infra.config;


import me.dri.Catvie.domain.adapters.services.auth.AuthenticationServiceImpl;
import me.dri.Catvie.domain.adapters.services.director.DirectorServiceImpl;
import me.dri.Catvie.domain.adapters.services.film.FilmServiceImpl;
import me.dri.Catvie.domain.adapters.services.genre.GenreServiceImpl;
import me.dri.Catvie.domain.adapters.services.mappers.MapperUserImpl;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationServicePort;
import me.dri.Catvie.domain.ports.interfaces.auth.TokenServicesPort;
import me.dri.Catvie.domain.ports.interfaces.director.DirectorServicePort;
import me.dri.Catvie.domain.ports.interfaces.film.FilmServicePort;
import me.dri.Catvie.domain.ports.interfaces.film.MapperEntities;
import me.dri.Catvie.domain.ports.interfaces.film.MapperEntitiesPort;
import me.dri.Catvie.domain.ports.interfaces.genre.GenreServicesPort;
import me.dri.Catvie.domain.ports.interfaces.user.MapperUserPort;
import me.dri.Catvie.domain.adapters.services.mappers.MapperFilmImpl;
import me.dri.Catvie.domain.adapters.services.mappers.MapperEntitiesImpl;
import me.dri.Catvie.domain.ports.repositories.DirectorRepositoryPort;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.domain.ports.repositories.GenreRepositoryPort;
import me.dri.Catvie.domain.ports.repositories.UserRepositoryPort;
import me.dri.Catvie.infra.adapters.mapper.MapperEntityAdapter;
import me.dri.Catvie.infra.adapters.mapper.MapperGenreAdapter;
import me.dri.Catvie.infra.ports.MapperGenrePort;
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
    MapperGenrePort mapperGenrePort() {
        return new MapperGenreAdapter();
    }

    @Bean
    GenreServicesPort genreServicesPort (GenreRepositoryPort genreRepositoryPort) {
        return  new GenreServiceImpl(genreRepositoryPort);
    }

    @Bean
    me.dri.Catvie.infra.ports.MapperUserPort mapperUserAdapter() {
        return new MapperEntityAdapter();
    }

    @Bean
    FilmServicePort filmServicePort(FilmRepositoryPort filmRepositoryPort, MapperEntitiesPort mapperEntitiesPort, GenreServicesPort genreServicesPort, DirectorServicePort directorServicePort) {
        return new FilmServiceImpl(filmRepositoryPort, mapperEntitiesPort, genreServicesPort, directorServicePort);
    }

    @Bean
    TokenServicesPort tokenServicesPort() {
        return new TokenService();
    }


    @Bean
    AuthenticationServicePort userServicePort(AuthenticationPort authenticationPort, MapperUserPort mapperUserPort, UserRepositoryPort userRepositoryPort) {
        return new AuthenticationServiceImpl(authenticationPort, mapperUserPort, userRepositoryPort);
    }

    @Bean
    DirectorServicePort directorServicePort(DirectorRepositoryPort repositoryPort) {
        return new DirectorServiceImpl(repositoryPort);
    }


}
