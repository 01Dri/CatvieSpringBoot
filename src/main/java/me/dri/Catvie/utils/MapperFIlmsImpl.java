package me.dri.Catvie.utils;

import me.dri.Catvie.domain.models.core.Film;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.utils.interfaces.BuilderFilm;
import me.dri.Catvie.utils.interfaces.MapperFilms;

import java.util.List;

public class MapperFIlmsImpl implements MapperFilms {

    private final BuilderFilm<FilmResponseDTO> builderFilmResponseDTO = new FilmResponseDTOBuilder<>();
    private final BuilderFilm builderFilm = new FilmBuilder();

    public MapperFIlmsImpl() {
    }

    @Override
    public List<FilmResponseDTO> convertListFilmToFilmResponseDTOList(List<Film> films) {
        return films.stream().map(
                this::buildFilmResponseDTO).toList();
    }


    @Override
    public FilmResponseDTO convertFilmToResponseDTO(Film film) {
        return this.buildFilmResponseDTO(film);
    }

    @Override
    public Film convertFilmEntityToFilm(FilmEntity filmEntity) {
        return this.buildFilm(filmEntity);
    }

    @Override
    public FilmEntity convertFilmToFilmEntity(Film film) {
        return this.buildFilmEntity(film);
    }

    @Override
    public List<Film> convertListFilmEntityToFilmList(List<FilmEntity> filmsAll) {
        return filmsAll.stream().map(
                this::buildFilm).toList();
    }

    /**
     * This method is responsible to convert the type of the object using builder pattern
     * The attribute  "isEntity" is to check if object is a model or entity, examples bellow
     * If the object on parameter is model, and isEntity is true, the objet will be converted for entity infra
     *  Film - Model Domain >> FilmEntity - Entity Infra
     * if on the contrary, the object will be converted for model domain
     * FilmEntity - Infra >> Film - Model Domain
     */
    private Film buildFilm(FilmEntity filmEntity) {
        return (Film) this.builderFilm
                .isEntity(false)
                .withId(filmEntity.getId())
                .withTitle(filmEntity.getTitle())
                .withGenre(filmEntity.getGenres())
                .withOriginalLanguage(filmEntity.getOriginalLanguage())
                .withReleaseDate(filmEntity.getReleaseDate())
                .withRuntime(filmEntity.getRuntime())
                .withDistributor(filmEntity.getDistributor())
                .withWriter(filmEntity.getWriter())
                .withProductionCo(filmEntity.getProductionCo())
                .withAverageRatingCritic(filmEntity.getAverageRatingCritic())
                .withAverageRatingAudience(filmEntity.getAverageRatingAudience())
                .withDirector(filmEntity.getDirector())
                .withPosterUrl(filmEntity.getPosterUrl())
                .withUser(filmEntity.getUser())
                .withLinks(filmEntity.getLinks())
                .build();
    }

    private FilmResponseDTO buildFilmResponseDTO(Film film) {
        return (FilmResponseDTO) this.builderFilmResponseDTO
                .withTitle(film.getTitle())
                .withGenre(film.getGenres())
                .withId(film.getId())
                .withTitle(film.getTitle())
                .withGenre(film.getGenres())
                .withOriginalLanguage(film.getOriginalLanguage())
                .withReleaseDate(film.getReleaseDate())
                .withRuntime(film.getRuntime())
                .withDistributor(film.getDistributor())
                .withWriter(film.getWriter())
                .withProductionCo(film.getProductionCo())
                .withAverageRatingCritic(film.getAverageRatingCritic())
                .withAverageRatingAudience(film.getAverageRatingAudience())
                .withDirector(film.getDirector())
                .withPosterUrl(film.getPosterUrl())
                .withUser(film.getUser())
                .withLinks(film.getLinks())
                .build();
    }

    /**
     * This method is responsible to convert the type of the object using builder pattern
     * The attribute  "isEntity" is to check if object is a model or entity, examples bellow
     * If the object on parameter is model, and isEntity is true, the objet will be converted for entity infra
     *  Film - Model Domain >> FilmEntity - Entity Infra
     * if on the contrary, the object will be converted for model domain
     * FilmEntity - Infra >> Film - Model Domain
     */
    private FilmEntity buildFilmEntity(Film film) {
        return (FilmEntity) this.builderFilm
                .isEntity(true)
                .withId(film.getId())
                .withTitle(film.getTitle())
                .withGenre(film.getGenres())
                .withOriginalLanguage(film.getOriginalLanguage())
                .withReleaseDate(film.getReleaseDate())
                .withRuntime(film.getRuntime())
                .withDistributor(film.getDistributor())
                .withWriter(film.getWriter())
                .withProductionCo(film.getProductionCo())
                .withAverageRatingCritic(film.getAverageRatingCritic())
                .withAverageRatingAudience(film.getAverageRatingAudience())
                .withDirector(film.getDirector())
                .withPosterUrl(film.getPosterUrl())
                .withUser(film.getUser())
                .withLinks(film.getLinks())
                .build();
    }
}
