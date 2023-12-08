package me.dri.Catvie.domain.adapters.services.film;

import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.exceptions.film.*;
import me.dri.Catvie.domain.models.dto.film.FilmRequestDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.domain.ports.interfaces.film.FilmServicePort;
import me.dri.Catvie.domain.ports.interfaces.genre.GenreServicesPort;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperFilmResponsePort;
import me.dri.Catvie.domain.ports.repositories.DirectorRepositoryPort;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Set;

public class FilmServiceImpl  implements FilmServicePort {

    private final FilmRepositoryPort filmRepositoryPort;

    private final MapperFilmResponsePort mapperFilmResponse;

    private final GenreServicesPort genreServicesPort;

    private final DirectorRepositoryPort directorRepository;

    private final ModelMapper modelMapper;


    public FilmServiceImpl(FilmRepositoryPort filmRepositoryPort, MapperFilmResponsePort mapperFilmResponse, GenreServicesPort genreServicesPort, DirectorRepositoryPort directorRepository, ModelMapper modelMapper) {
        this.filmRepositoryPort = filmRepositoryPort;
        this.mapperFilmResponse = mapperFilmResponse;
        this.genreServicesPort = genreServicesPort;
        this.directorRepository = directorRepository;
        this.modelMapper = new ModelMapper();
    }




    @Override
    public FilmResponseDTO findById(Long id) {
        Film film = this.filmRepositoryPort.findById(id);
        return this.mapperFilmResponse.convertFilmToResponseDTO(film);
    }

    @Override
    public List<FilmResponseDTO> findAll() {
        List<Film> films = this.filmRepositoryPort.findAllFilmEntity();
        return this.mapperFilmResponse.convertListFilmToFilmResponseDTOList(films);
    }

    @Override
    public FilmResponseDTO findByTitle(String title) {
        Film film = this.filmRepositoryPort.findByTitle(title);
        return this.mapperFilmResponse.convertFilmToResponseDTO(film);

    }

    @Override
    public FilmResponseDTO create(FilmRequestDTO filmRequestDTO,
                                  String subjectByToken) {
        this.filmIsValid(filmRequestDTO);
        Set<Genre> genresSetResponseByServices = this.genreServicesPort.
                verifyExistingGenres(filmRequestDTO.getGenres());
        Director directorByRepository = this.directorRepository.findByName(filmRequestDTO.getDirector().getName());
        Film filmConvertedByModel = this.modelMapper.map(filmRequestDTO, Film.class);
        filmConvertedByModel.setGenres(genresSetResponseByServices);
        filmConvertedByModel.setDirector(directorByRepository);
        filmConvertedByModel.setAverageRatingAudience(0.0);
        filmConvertedByModel.setAverageRatingCritic(0.0);
        Film filmByInfraAdapter = this.filmRepositoryPort.create(filmConvertedByModel, subjectByToken);
        return this.mapperFilmResponse.convertFilmToResponseDTO(filmByInfraAdapter);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IdFilmIsNullException("Content 'id' is null");
        }
        this.filmRepositoryPort.deleteById(id);
    }

    @Override
    public FilmResponseDTO update(FilmRequestDTO filmRequestDTO) {
        this.filmIsValid(filmRequestDTO);
        Set<Genre> genresSetResponseByServices = this.genreServicesPort.verifyExistingGenres(filmRequestDTO.getGenres());
        Director directorByRepository = this.directorRepository.findByName(filmRequestDTO.getDirector().getName());
        Film filmConverted = this.modelMapper.map(filmRequestDTO, Film.class);
        filmConverted.setGenres(genresSetResponseByServices);
        filmConverted.setDirector(directorByRepository);
        this.filmRepositoryPort.update(filmConverted);
        return this.mapperFilmResponse.convertFilmToResponseDTO(filmConverted);
    }


    public void filmIsValid(FilmRequestDTO filmRequestDTO) {
        try {
            if (filmRequestDTO.getTitle().isEmpty() || filmRequestDTO.getTitle().isBlank()) {
                throw new InvalidTitleFilmException("Content 'title' is empty");
            }
        } catch (NullPointerException e) {
            throw new InvalidTitleFilmException("Content 'title' is null");
        }

        try {
            if(filmRequestDTO.getOriginalLanguage().isEmpty() || filmRequestDTO.getOriginalLanguage().isBlank()) {
                throw new InvalidLanguageFilmException("Content 'original_language' is empty");
            }
        } catch (NullPointerException e) {
            throw new InvalidLanguageFilmException("Content 'original_language' is null");
        }
        try {
            if(filmRequestDTO.getWriter().isEmpty() || filmRequestDTO.getWriter().isBlank()) {
                throw new InvalidWriterFilmException("Content 'writer' is empty");
            }
        } catch (NullPointerException e) {
            throw new InvalidWriterFilmException("Content 'writer' is null");
        }

        try {
            if(filmRequestDTO.getDistributor().isEmpty() || filmRequestDTO.getDistributor().isBlank()) {
                throw new InvalidDistributorFilmException("Content 'distributor' is empty");
            }
        } catch (NullPointerException e) {
            throw new InvalidDistributorFilmException("Content 'distributor' is null");
        }

        try {
            if(filmRequestDTO.getProductionCo().isEmpty() || filmRequestDTO.getProductionCo().isBlank()) {
                throw new InvalidProdutionFilmException("Content 'prodution' is empty");
            }
        } catch (NullPointerException e) {
            throw new InvalidProdutionFilmException("Content 'prodution' is null");
        }

        try {
            if(filmRequestDTO.getPosterUrl().isEmpty() || filmRequestDTO.getPosterUrl().isBlank()) {
                throw new InvalidUrlImageFilmException("Content 'url' is empty");
            }
        } catch (NullPointerException e) {
            throw new InvalidUrlImageFilmException("Content 'url' is null");
        }

        if (filmRequestDTO.getRuntime() == null) {
            throw new InvalidRuntimeFilmException("Content 'runtime' is null");
        }

        if (filmRequestDTO.getReleaseDate() == null) {
            throw new InvalidReleaseDateFilmException("Content 'release_date' is null");
        }

    }

}
