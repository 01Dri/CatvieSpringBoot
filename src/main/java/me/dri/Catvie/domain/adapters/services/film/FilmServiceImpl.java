package me.dri.Catvie.domain.adapters.services.film;

import me.dri.Catvie.domain.exceptions.ContentInformationsFilmMissing;
import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.ports.interfaces.director.DirectorServicePort;
import me.dri.Catvie.domain.ports.interfaces.film.FilmServicePort;
import me.dri.Catvie.domain.ports.interfaces.film.MapperEntitiesPort;
import me.dri.Catvie.domain.ports.interfaces.genre.GenreServicesPort;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;

import java.util.List;

public class FilmServiceImpl  implements FilmServicePort {

    private final FilmRepositoryPort filmRepositoryPort;

    private final MapperEntitiesPort mapperEntitiesPort;

    private final GenreServicesPort genreServicesPort;

    private final DirectorServicePort directorServicePort;


    public FilmServiceImpl(FilmRepositoryPort filmRepositoryPort, MapperEntitiesPort mapperEntitiesPort, GenreServicesPort genreServicesPort, DirectorServicePort directorServicePort) {
        this.filmRepositoryPort = filmRepositoryPort;
        this.mapperEntitiesPort = mapperEntitiesPort;
        this.genreServicesPort = genreServicesPort;
        this.directorServicePort = directorServicePort;
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
    public FilmResponseDTO create(FilmDTO filmDto) {
        if (this.filmIsValid(filmDto)) {
            throw new ContentInformationsFilmMissing("Missing information's in the object FilmDTO");
        }
        var genres = this.genreServicesPort.verifyExistingGenres(filmDto.genres());
        var director = this.directorServicePort.findByName(filmDto.director().name());
        if (genres != null && director != null) {
            Film film = this.mapperEntitiesPort.convertFilmDtoToFilm(filmDto, genres, director);
            this.filmRepositoryPort.create(film);
            return new FilmResponseDTO(filmDto.title(), filmDto.genres(), filmDto.original_language(),
                    filmDto.release_date(), filmDto.runtime(), filmDto.distributor(), filmDto.production_co(),
                    filmDto.average_rating_critic(), filmDto.average_rating_audience(), filmDto.url());
        } else {
            throw new RuntimeException("Teste");
        }
    }

    @Override
    public void save(FilmDTO film) {
        if (this.filmIsValid(film)) {
            throw new ContentInformationsFilmMissing("Missing information's in the object FilmDTO");
        }
        var genres = this.genreServicesPort.verifyExistingGenres(film.genres());
        var director = this.directorServicePort.findByName(film.title());
        Film filmToSave = this.mapperEntitiesPort.convertFilmDtoToFilm(film, genres, director);
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
