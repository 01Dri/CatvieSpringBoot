package me.dri.Catvie.usecases;

import me.dri.Catvie.entity.exceptions.ContentIsMissing;
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
        if (!this.filmIsValid(filmDto)) {
            throw new ContentIsMissing("Missing information's in the object FilmDTO");
        }
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

    public boolean filmIsValid(FilmDto filmDto) {
        if (filmDto.title() == null || filmDto.title().isEmpty() || filmDto.title().isBlank()  ||
                filmDto.original_language() == null || filmDto.original_language().isBlank() || filmDto.original_language().isEmpty() ||
                filmDto.writer() == null || filmDto.writer().isEmpty() || filmDto.writer().isBlank()
                || filmDto.runtime() == null || filmDto.production_co() == null || filmDto.production_co().isEmpty() || filmDto.production_co().isBlank() ) {
            return false;
        }
        return true;
    }
}


