package me.dri.Catvie.domain.adapters.services;

import me.dri.Catvie.domain.exceptions.ContentInformationsFilmMissing;
import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.models.dto.FilmDTO;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.ports.interfaces.FilmServicePort;
import me.dri.Catvie.domain.ports.interfaces.MapperEntitiesPort;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;

import java.util.List;

public class FilmServiceImpl  implements FilmServicePort {

    private final FilmRepositoryPort filmRepositoryPort;

    private final MapperEntitiesPort mapperEntitiesPort;


    public FilmServiceImpl(FilmRepositoryPort filmRepositoryPort, MapperEntitiesPort mapperEntitiesPort) {
        this.filmRepositoryPort = filmRepositoryPort;
        this.mapperEntitiesPort = mapperEntitiesPort;
    }
    @Override
    public FilmDTO findById(Long id) {
        Film film = this.filmRepositoryPort.findById(id);
        if (film == null ) {
            throw new NotFoundFilm("Film not exists");
        }
        return this.mapperEntitiesPort.convertFilmToDto(film);
    }

    @Override
    public List<FilmDTO> findAll() {
        List<Film> films = this.filmRepositoryPort.findAll();
        return this.mapperEntitiesPort.convertListFilmToListDto(films);
    }

    @Override
    public FilmDTO findByTitle(String title) {
        Film film = this.filmRepositoryPort.findByTitle(title);
        if (film == null) {
            throw new NotFoundFilm("Film with name " + title + " Not exists!");
        }
        return this.mapperEntitiesPort.convertFilmToDto(film);

    }

    @Override
    public void create(FilmDTO filmDto) {
        if (this.filmIsValid(filmDto)) {
            throw new ContentInformationsFilmMissing("Missing information's in the object FilmDTO");
        }
        Film film = this.mapperEntitiesPort.convertFilmDtoToFilm(filmDto);
        this.filmRepositoryPort.save(film);
    }

    @Override
    public void save(FilmDTO film) {
        if (this.filmIsValid(film)) {
            throw new ContentInformationsFilmMissing("Missing information's in the object FilmDTO");
        }
        Film filmToSave = this.mapperEntitiesPort.convertFilmDtoToFilm(film);
        this.filmRepositoryPort.save(filmToSave);
    }

    @Override
    public void deleteById(Long id) {
        Film film = this.filmRepositoryPort.findById(id);
        if (film == null) {
            throw new NotFoundFilm("Entity not found");
        }
        this.filmRepositoryPort.delete(film);
    }


    public boolean filmIsValid(FilmDTO filmDto) {
        return filmDto.title() == null || filmDto.title().isEmpty() ||
                filmDto.title().isBlank() || filmDto.original_language() == null ||
                filmDto.original_language().isBlank() || filmDto.original_language().isEmpty() ||
                filmDto.writer() == null || filmDto.writer().isEmpty() || filmDto.writer().isBlank()
                || filmDto.runtime() == null || filmDto.production_co() == null ||
                filmDto.production_co().isEmpty() || filmDto.production_co().isBlank();
    }
}
