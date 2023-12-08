package me.dri.Catvie.infra.config;


import me.dri.Catvie.domain.adapters.services.auth.AuthenticationServiceImpl;
import me.dri.Catvie.domain.adapters.services.director.DirectorServiceImpl;
import me.dri.Catvie.domain.adapters.services.film.FilmServiceImpl;
import me.dri.Catvie.domain.adapters.services.genre.GenreServiceImpl;
import me.dri.Catvie.domain.adapters.services.mappers.MapperFilmResponseImpl;
import me.dri.Catvie.domain.adapters.services.mappers.MapperUserResponseImpl;
import me.dri.Catvie.domain.adapters.services.notes.NotesAudienceServicesImpl;
import me.dri.Catvie.domain.adapters.services.user.UserServiceImpl;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationServicePort;
import me.dri.Catvie.domain.ports.interfaces.auth.TokenServicesPort;
import me.dri.Catvie.domain.ports.interfaces.director.DirectorServicePort;
import me.dri.Catvie.domain.ports.interfaces.film.FilmServicePort;
import me.dri.Catvie.domain.ports.interfaces.genre.GenreServicesPort;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperFilmResponsePort;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperUserResponsePort;
import me.dri.Catvie.domain.ports.interfaces.notes.NotesAudienceServicesPort;
import me.dri.Catvie.domain.ports.interfaces.user.UserServicePort;
import me.dri.Catvie.domain.ports.repositories.*;
import me.dri.Catvie.infra.tokens.TokenService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {


    @Bean
    ModelMapper mapper() {
        return new ModelMapper();
    }
    @Bean
    MapperUserResponsePort mapperUserPort() {
        return new MapperUserResponseImpl();
    }

    @Bean
    MapperFilmResponsePort mapperFilmDomain(MapperUserResponsePort mapperUserDomainPort) {
        return new MapperFilmResponseImpl(mapperUserDomainPort);
    }


    @Bean
    GenreServicesPort genreServicesPort (GenreRepositoryPort genreRepositoryPort) {
        return  new GenreServiceImpl(genreRepositoryPort);
    }


    @Bean
    FilmServicePort filmServicePort(FilmRepositoryPort filmRepositoryPort, MapperFilmResponsePort mapperEntitiesPort, GenreServicesPort genreServicesPort, DirectorRepositoryPort directorServicePort, ModelMapper mapper) {
        return new FilmServiceImpl(filmRepositoryPort, mapperEntitiesPort, genreServicesPort, directorServicePort, mapper);
    }

    @Bean
    TokenServicesPort tokenServicesPort() {
        return new TokenService();
    }

    @Bean
    UserServicePort userServicePort(UserRepositoryPort userRepositoryPort, MapperUserResponsePort mapperUserDomainPort) {
        return new UserServiceImpl(userRepositoryPort, mapperUserDomainPort);
    }


    @Bean
    AuthenticationServicePort userAuthServicePort(AuthenticationPort authenticationPort, MapperUserResponsePort mapperUserPort, UserRepositoryPort userRepositoryPort) {
        return new AuthenticationServiceImpl(authenticationPort, mapperUserPort, userRepositoryPort);
    }

    @Bean
    DirectorServicePort directorServicePort(DirectorRepositoryPort repositoryPort) {
        return new DirectorServiceImpl(repositoryPort);
    }

    @Bean
    NotesAudienceServicesPort notesAudienceServicesPort(NotesAudiencesPort audiencesPort, MapperFilmResponsePort mapperFilmDomainPort, FilmRepositoryPort filmRepositoryPort) {
        return new NotesAudienceServicesImpl(audiencesPort, mapperFilmDomainPort, filmRepositoryPort);
    }


}
