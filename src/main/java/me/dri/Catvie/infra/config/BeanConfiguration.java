package me.dri.Catvie.infra.config;


import me.dri.Catvie.domain.adapters.services.auth.AuthenticationServiceImpl;
import me.dri.Catvie.domain.adapters.services.director.DirectorServiceImpl;
import me.dri.Catvie.domain.adapters.services.film.FilmServiceImpl;
import me.dri.Catvie.domain.adapters.services.genre.GenreServiceImpl;
import me.dri.Catvie.utils.MapperFIlmsImpl;
import me.dri.Catvie.utils.MapperUserResponseImpl;
import me.dri.Catvie.domain.adapters.services.notes.NotesAudienceServicesImpl;
import me.dri.Catvie.domain.adapters.services.user.UserServiceImpl;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationServicePort;
import me.dri.Catvie.domain.ports.interfaces.auth.TokenServicesPort;
import me.dri.Catvie.domain.ports.interfaces.director.DirectorServicePort;
import me.dri.Catvie.domain.ports.interfaces.film.FilmServicePort;
import me.dri.Catvie.domain.ports.interfaces.genre.GenreServicesPort;
import me.dri.Catvie.utils.interfaces.MapperFilms;
import me.dri.Catvie.utils.interfaces.MapperUserResponsePort;
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
    MapperFilms mapperFilmDomain() {
        return new MapperFIlmsImpl();
    }


    @Bean
    GenreServicesPort genreServicesPort (GenreRepositoryPort genreRepositoryPort) {
        return  new GenreServiceImpl(genreRepositoryPort);
    }


    @Bean
    FilmServicePort filmServicePort(FilmRepositoryPort filmRepositoryPort, MapperFilms mapperEntitiesPort, GenreServicesPort genreServicesPort, DirectorRepositoryPort directorServicePort, ModelMapper mapper) {
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
    AuthenticationServicePort userAuthServicePort(AuthenticationPort authenticationPort, MapperUserResponsePort mapperUserPort) {
        return new AuthenticationServiceImpl(authenticationPort, mapperUserPort);
    }

    @Bean
    DirectorServicePort directorServicePort(DirectorRepositoryPort repositoryPort) {
        return new DirectorServiceImpl(repositoryPort);
    }

    @Bean
    NotesAudienceServicesPort notesAudienceServicesPort(NotesAudiencesPort audiencesPort) {
        return new NotesAudienceServicesImpl(audiencesPort);
    }


}
