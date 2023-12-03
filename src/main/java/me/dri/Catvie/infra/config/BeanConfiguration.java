package me.dri.Catvie.infra.config;


import me.dri.Catvie.domain.adapters.services.auth.AuthenticationServiceImpl;
import me.dri.Catvie.domain.adapters.services.director.DirectorServiceImpl;
import me.dri.Catvie.domain.adapters.services.film.FilmServiceImpl;
import me.dri.Catvie.domain.adapters.services.genre.GenreServiceImpl;
import me.dri.Catvie.domain.adapters.services.mappers.MapperFilmImpl;
import me.dri.Catvie.domain.adapters.services.mappers.MapperUserImpl;
import me.dri.Catvie.domain.adapters.services.notes.NotesAudienceServicesImpl;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationServicePort;
import me.dri.Catvie.domain.ports.interfaces.auth.TokenServicesPort;
import me.dri.Catvie.domain.ports.interfaces.director.DirectorServicePort;
import me.dri.Catvie.domain.ports.interfaces.film.FilmServicePort;
import me.dri.Catvie.domain.ports.interfaces.genre.GenreServicesPort;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperFilmDomainPort;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperUserDomainPort;
import me.dri.Catvie.domain.ports.interfaces.notes.NotesAudienceServicesPort;
import me.dri.Catvie.domain.ports.repositories.*;
import me.dri.Catvie.infra.tokens.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {


    @Bean
    MapperUserDomainPort mapperUserPort() {
        return new MapperUserImpl();
    }

    @Bean
    MapperFilmDomainPort mapperFilmDomain(MapperUserDomainPort mapperUserDomainPort) {
        return new MapperFilmImpl(mapperUserDomainPort);
    }


    @Bean
    GenreServicesPort genreServicesPort (GenreRepositoryPort genreRepositoryPort) {
        return  new GenreServiceImpl(genreRepositoryPort);
    }


    @Bean
    FilmServicePort filmServicePort(FilmRepositoryPort filmRepositoryPort, MapperFilmDomainPort mapperEntitiesPort, GenreServicesPort genreServicesPort, DirectorServicePort directorServicePort) {
        return new FilmServiceImpl(filmRepositoryPort, mapperEntitiesPort, genreServicesPort, directorServicePort);
    }

    @Bean
    TokenServicesPort tokenServicesPort() {
        return new TokenService();
    }


    @Bean
    AuthenticationServicePort userServicePort(AuthenticationPort authenticationPort, MapperUserDomainPort mapperUserPort, UserRepositoryPort userRepositoryPort) {
        return new AuthenticationServiceImpl(authenticationPort, mapperUserPort, userRepositoryPort);
    }

    @Bean
    DirectorServicePort directorServicePort(DirectorRepositoryPort repositoryPort) {
        return new DirectorServiceImpl(repositoryPort);
    }

    @Bean
    NotesAudienceServicesPort notesAudienceServicesPort(NotesAudiencesPort audiencesPort, MapperFilmDomainPort mapperFilmDomainPort, FilmRepositoryPort filmRepositoryPort) {
        return new NotesAudienceServicesImpl(audiencesPort, mapperFilmDomainPort, filmRepositoryPort);
    }

}
