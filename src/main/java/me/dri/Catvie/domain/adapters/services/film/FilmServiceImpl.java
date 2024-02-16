package me.dri.Catvie.domain.adapters.services.film;

import me.dri.Catvie.domain.exceptions.film.IdFilmIsNullException;
import me.dri.Catvie.domain.exceptions.validations.FilmValidations;
import me.dri.Catvie.domain.models.core.Director;
import me.dri.Catvie.domain.models.core.Film;
import me.dri.Catvie.domain.models.core.Genre;
import me.dri.Catvie.domain.models.dto.film.FilmRequestDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.film.FilmServicePort;
import me.dri.Catvie.domain.ports.interfaces.genre.GenreServicesPort;
import me.dri.Catvie.utils.interfaces.MapperFilms;
import me.dri.Catvie.domain.ports.repositories.DirectorRepositoryPort;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Set;

public class FilmServiceImpl  implements FilmServicePort {

    private final FilmRepositoryPort filmRepositoryPort;

    private final MapperFilms mapperFilmResponse;

    private final GenreServicesPort genreServicesPort;

    private final DirectorRepositoryPort directorRepository;

    private final ModelMapper modelMapper;


    public FilmServiceImpl(FilmRepositoryPort filmRepositoryPort, MapperFilms mapperFilmResponse, GenreServicesPort genreServicesPort, DirectorRepositoryPort directorRepository, ModelMapper modelMapper) {
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
        System.out.println(film.getLinks());
        return this.mapperFilmResponse.convertFilmToResponseDTO(film);

    }

    @Override
    public FilmResponseDTO create(FilmRequestDTO filmRequestDTO,
                                  String subjectByToken) throws NoSuchFieldException, IllegalAccessException {
        Film filmConvertedToModel = this.convertFilmRequestDtoToFilm(filmRequestDTO);
        // After film created, to set your notes to zero
        filmConvertedToModel.setAverageRatingAudience(0.0);
        filmConvertedToModel.setAverageRatingCritic(0.0);

        Film filmByInfraAdapter = this.filmRepositoryPort.create(filmConvertedToModel, subjectByToken);
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
    public FilmResponseDTO update(FilmRequestDTO filmRequestDTO) throws NoSuchFieldException, IllegalAccessException {
        Film filmConvertedToModel = this.convertFilmRequestDtoToFilm(filmRequestDTO);
        this.filmRepositoryPort.update(filmConvertedToModel);
        return this.mapperFilmResponse.convertFilmToResponseDTO(filmConvertedToModel);
    }

    private Film convertFilmRequestDtoToFilm(FilmRequestDTO filmRequestDTO) throws IllegalAccessException {
        FilmValidations.validateFilmRequestDto(filmRequestDTO);
        Set<Genre> genresSetResponseByServices = this.genreServicesPort.getAllGenresByGenreDTO(filmRequestDTO.getGenres());
        Director directorByRepository = this.directorRepository.findByName(filmRequestDTO.getDirector().getName());
        Film filmConverted = this.modelMapper.map(filmRequestDTO, Film.class);
        filmConverted.setGenres(genresSetResponseByServices);
        filmConverted.setDirector(directorByRepository);
        return filmConverted;
    }
}
