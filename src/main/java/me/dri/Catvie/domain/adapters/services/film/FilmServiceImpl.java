package me.dri.Catvie.domain.adapters.services.film;

import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.exceptions.film.*;
import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.ports.interfaces.director.DirectorServicePort;
import me.dri.Catvie.domain.ports.interfaces.film.FilmServicePort;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperFilmDomainPort;
import me.dri.Catvie.domain.ports.interfaces.genre.GenreServicesPort;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;

import java.util.List;

public class FilmServiceImpl  implements FilmServicePort {

    private final FilmRepositoryPort filmRepositoryPort;

    private final MapperFilmDomainPort mapperEntitiesPort;

    private final GenreServicesPort genreServicesPort;

    private final DirectorServicePort directorServicePort;


    public FilmServiceImpl(FilmRepositoryPort filmRepositoryPort, MapperFilmDomainPort mapperEntitiesPort, GenreServicesPort genreServicesPort, DirectorServicePort directorServicePort) {
        this.filmRepositoryPort = filmRepositoryPort;
        this.mapperEntitiesPort = mapperEntitiesPort;
        this.genreServicesPort = genreServicesPort;
        this.directorServicePort = directorServicePort;
    }

    @Override
    public FilmResponseDTO findById(Long id) {
        Film film = this.filmRepositoryPort.findById(id);
        if (film == null ) {
            throw new NotFoundFilm("Film not exists");
        }
        return this.mapperEntitiesPort.convertFilmToResponseDTO(film);
    }

    @Override
    public List<FilmResponseDTO> findAll() {
        List<Film> films = this.filmRepositoryPort.findAllFilmEntity();
        return this.mapperEntitiesPort.convertListFilmToListDto(films);
    }

    @Override
    public FilmResponseDTO findByTitle(String title) {
        Film film = this.filmRepositoryPort.findByTitle(title);
        if (film == null) {
            throw new NotFoundFilm("Film with name " + title + " Not exists!");
        }
        return this.mapperEntitiesPort.convertFilmToResponseDTO(film);

    }

    @Override
    public FilmResponseDTO create(FilmDTO filmDto) {
        this.filmIsValid(filmDto);
        var genres = this.genreServicesPort.verifyExistingGenres(filmDto.genres());
        var director = this.directorServicePort.findByName(filmDto.director().name());
        Film film = this.mapperEntitiesPort.convertFilmDtoToFilm(filmDto, genres, director);
        this.filmRepositoryPort.create(film);
        return this.mapperEntitiesPort.convertFilmToResponseDTO(film);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IdFilmIsNullException("Content 'id' is null");
        }
        this.filmRepositoryPort.deleteById(id);
    }

    @Override
    public FilmResponseDTO update(FilmDTO filmDTO) {
        this.filmIsValid(filmDTO);
        var genres = this.genreServicesPort.verifyExistingGenres(filmDTO.genres());
        var director = this.directorServicePort.findByName(filmDTO.director().name());
        Film film = this.mapperEntitiesPort.convertFilmDtoToFilm(filmDTO, genres, director);
        this.filmRepositoryPort.update(film);
        return this.mapperEntitiesPort.convertFilmToResponseDTO(film);
    }


    public void filmIsValid(FilmDTO filmDto) {
        try {
            if (filmDto.title().isEmpty() || filmDto.title().isBlank()) {
                throw new InvalidTitleFilmException("Content 'title' is empty");
            }
        } catch (NullPointerException e) {
            throw new InvalidTitleFilmException("Content 'title' is null");
        }

        try {
            if(filmDto.originalLanguage().isEmpty() || filmDto.originalLanguage().isBlank()) {
                throw new InvalidLanguageFilmException("Content 'original_language' is empty");
            }
        } catch (NullPointerException e) {
            throw new InvalidLanguageFilmException("Content 'original_language' is null");
        }
        try {
            if(filmDto.writer().isEmpty() || filmDto.writer().isBlank()) {
                throw new InvalidWriterFilmException("Content 'writer' is empty");
            }
        } catch (NullPointerException e) {
            throw new InvalidWriterFilmException("Content 'writer' is null");
        }

        try {
            if(filmDto.distributor().isEmpty() || filmDto.distributor().isBlank()) {
                throw new InvalidDistributorFilmException("Content 'distributor' is empty");
            }
        } catch (NullPointerException e) {
            throw new InvalidDistributorFilmException("Content 'distributor' is null");
        }

        try {
            if(filmDto.productionCo().isEmpty() || filmDto.productionCo().isBlank()) {
                throw new InvalidProdutionFilmException("Content 'prodution' is empty");
            }
        } catch (NullPointerException e) {
            throw new InvalidProdutionFilmException("Content 'prodution' is null");
        }

        try {
            if(filmDto.posterUrl().isEmpty() || filmDto.posterUrl().isBlank()) {
                throw new InvalidUrlImageFilmException("Content 'url' is empty");
            }
        } catch (NullPointerException e) {
            throw new InvalidUrlImageFilmException("Content 'url' is null");
        }

        if (filmDto.runtime() == null) {
            throw new InvalidRuntimeFilmException("Content 'runtime' is null");
        }

        if (filmDto.releaseDate() == null) {
            throw new InvalidReleaseDateFilmException("Content 'release_date' is null");
        }

        if (filmDto.averageRatingCritic() == null) {
            throw new InvalidAverageCriticFilmException("Content 'average_rating_critic' is null");
        }
        if (filmDto.averageRatingAudience() == null) {
            throw new InvalidAverageAudienceFilmException("Content 'average_rating_audience' is null");
        }
    }

}
