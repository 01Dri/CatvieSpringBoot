package me.dri.Catvie.domain.adapters.services.mappers;

import me.dri.Catvie.domain.models.core.Film;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperFilmResponsePort;
import me.dri.Catvie.domain.utils.BuilderFilm;
import me.dri.Catvie.domain.utils.FilmBuilder;
import me.dri.Catvie.domain.utils.FilmResponseDTOBuilder;
import me.dri.Catvie.infra.entities.FilmEntity;

import java.util.List;

public class MapperFilmResponseImpl implements MapperFilmResponsePort {

    private final BuilderFilm builderFilmResponseDTO = new FilmResponseDTOBuilder<>();
    private final BuilderFilm builderFilm = new FilmBuilder<>();

    public MapperFilmResponseImpl() {
    }

    @Override
    public List<FilmResponseDTO> convertListFilmToFilmResponseDTOList(List<Film> films) {
        return films.stream().map(
                film -> (FilmResponseDTO) this.builderFilmResponseDTO
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
                        .build()).toList();
    }


    @Override
    public FilmResponseDTO convertFilmToResponseDTO(Film film) {
        return (FilmResponseDTO) this.builderFilmResponseDTO
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

    @Override
    public List<Film> convertListFilmEntityToFilmList(List<FilmEntity> filmsAll) {
        return filmsAll.stream().map(
                film -> (Film) this.builderFilm
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
                        .build()).toList();

    }
}
