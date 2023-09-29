package me.dri.Catvie.infra.adapters.repositories;

import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.ports.interfaces.DozerMapperPort;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.infra.adapters.entities.FilmEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FilmRepository implements FilmRepositoryPort {

    private final FilmRepositoryJPA filmRepositoryJPA;

    private final DozerMapperPort dozerMapperPort;
    
    @Autowired
    public FilmRepository(FilmRepositoryJPA filmRepositoryJPA, DozerMapperPort dozerMapperPort) {
        this.filmRepositoryJPA = filmRepositoryJPA;
        this.dozerMapperPort = dozerMapperPort;
    }

    @Override
    public Film findById(Long id) {
        FilmEntity film = this.filmRepositoryJPA.findFilmById(id);
        return this.dozerMapperPort.map(film, Film.class);
    }

    @Override
    public List<Film> findAll() {
        List<FilmEntity> films = this.filmRepositoryJPA.findAll();
        return this.dozerMapperPort.mapCollections(films, Film.class);
    }

    @Override
    public Film findByTitle(String title) {
        FilmEntity film = this.filmRepositoryJPA.findFilmByTitle(title);
        return this.dozerMapperPort.map(film, Film.class);
    }

    @Override
    public void create(Film film) {
        FilmEntity filmEntity = this.dozerMapperPort.map(film, FilmEntity.class);
        this.filmRepositoryJPA.save(filmEntity);

    }

    @Override
    public void save(Film film) {
        FilmEntity filmEntity = this.dozerMapperPort.map(film, FilmEntity.class);
        this.filmRepositoryJPA.delete(filmEntity);
    }

    @Override
    public void delete(Film film) {
        FilmEntity filmEntity = this.dozerMapperPort.map(film, FilmEntity.class);
        this.filmRepositoryJPA.delete(filmEntity);
    }
}


