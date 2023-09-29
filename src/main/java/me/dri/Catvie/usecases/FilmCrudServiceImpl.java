package me.dri.Catvie.usecases;

import me.dri.Catvie.entity.interfaces.FilmCrudInterface;
import me.dri.Catvie.entity.models.Film;
import me.dri.Catvie.entity.models.dto.FilmDto;
import me.dri.Catvie.infra.interfaces.IDozerMapper;
import me.dri.Catvie.infra.repositories.FilmRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class FilmCrudServiceImpl implements FilmCrudInterface {

    private final FilmRepositoryJPA filmRepositoryJPA;
    private final IDozerMapper mapper;


    @Autowired
    public FilmCrudServiceImpl(FilmRepositoryJPA filmRepositoryJPA, IDozerMapper mapper) {
        this.filmRepositoryJPA = filmRepositoryJPA;
        this.mapper = mapper;
    }

    @Override
    public Film findById(Long id) {
        return this.filmRepositoryJPA.findFilmById(id);
    }

    @Override
    public List<FilmDto> findAll() {
        List<Film> films = this.filmRepositoryJPA.findAllFilms();
        List<FilmDto> filmDtos = this.mapper.mapCollections(films, FilmDto.class);
        return filmDtos;
    }

    @Override
    public FilmDto findByTitle(String title) {
        Film film = this.filmRepositoryJPA.findFilmByTitle(title);
        return this.mapper.map(film, FilmDto.class);
    }

    @Override
    public void create(FilmDto filmDto) {
        Film film = this.mapper.map(filmDto, Film.class);
        this.filmRepositoryJPA.save(film);
    }

    @Override
    public void save(FilmDto film) {
        Film filmToSave = this.mapper.map(film, Film.class);
        this.filmRepositoryJPA.save(filmToSave);
    }

    @Override
    public void delete(FilmDto film) {
        Film filmToDelte = this.mapper.map(film, Film.class);
        this.filmRepositoryJPA.delete(filmToDelte);
    }
}


