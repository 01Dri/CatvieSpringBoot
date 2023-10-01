package me.dri.Catvie.infra.config;


import me.dri.Catvie.domain.adapters.services.FilmServiceImpl;
import me.dri.Catvie.domain.ports.interfaces.FilmServicePort;
import me.dri.Catvie.domain.ports.interfaces.MapperEntitiesPort;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.domain.utils.MapperEntities;
import me.dri.Catvie.infra.utils.mapper.MapperEntitiesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    MapperEntitiesPort mapperEntitiesPort() {
        return new MapperEntities();
    }

    @Bean
    me.dri.Catvie.infra.utils.mapper.MapperEntities mapperEntities() {
        return new MapperEntitiesImpl();
    }
    @Bean
    FilmServicePort filmServicePort(FilmRepositoryPort filmRepositoryPort, MapperEntitiesPort mapperEntitiesPort) {
        return new FilmServiceImpl(filmRepositoryPort, mapperEntitiesPort);
    }

}
