package me.dri.Catvie.unittest.mappers;

import me.dri.Catvie.domain.models.core.Film;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.unittest.mocks.MockFilm;
import me.dri.Catvie.utils.FilmBuilder;
import me.dri.Catvie.utils.interfaces.BuilderFilm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Mapper film is used to convert my entities and models
 * Example:
 * FilmEntity (Infra layer) -> Film (Domain layer)
 */

public class MapperFIlmTest {

    MockFilm mockFilm;

    @BeforeEach
    void setup() {
        this.mockFilm = new MockFilm();
    }



    @Test
    void testMapFilmToFilmEntity() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Film film = this.mockFilm.mockFilm();
        BuilderFilm<FilmEntity> builderFilm = new FilmBuilder<>();
        FilmEntity filmResult = (FilmEntity) this.setAttributesToBuilder(builderFilm, film, true);
        assertEquals(film.getGenres().size(), filmResult.getGenres().size());
        assertEquals(film.getPosterUrl(), filmResult.getPosterUrl());
        assertEquals(film.getDirector().getId(), filmResult.getDirector().getId());
        this.checkAllAsserts(film, filmResult);
    }


    @Test
    void testMapFilmEntityToFilm() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        FilmEntity film = this.mockFilm.mockFilmEntity();
        BuilderFilm<Film> builderFilm = new FilmBuilder<>();
        Film filmResult = (Film) this.setAttributesToBuilder(builderFilm, film, false);
        assertEquals(film.getGenres().size(), filmResult.getGenres().size());
        assertEquals(film.getPosterUrl(), filmResult.getPosterUrl());
        assertEquals(film.getDirector().getId(), filmResult.getDirector().getId());
        this.checkAllAsserts(film, filmResult);
    }

    /**
     * These tests bellow should to throw an exception
     * Because you want to convert film entity to film, then condition of builder must be "false"
     * Else will happen an error of casting
     */
    @Test
    void testInvalidCastingMapFilmToFilmEntity() {
        FilmEntity film = this.mockFilm.mockFilmEntity();
        BuilderFilm<Film> builderFilm = new FilmBuilder<>();
        assertThrows(ClassCastException.class, () -> this.setAttributesToBuilder(builderFilm, film, true));
    }

    @Test
    void testInvalidCastingMapFilmEntityToFilm() {
        Film film = this.mockFilm.mockFilm();
        BuilderFilm<FilmEntity> builderFilm = new FilmBuilder<>();
        assertThrows(ClassCastException.class, () -> this.setAttributesToBuilder(builderFilm, film, false));
    }


    private Object setAttributesToBuilder(BuilderFilm builder, Film film, Boolean condition) {
              return builder.isEntity(condition)
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

    private Object setAttributesToBuilder(BuilderFilm builder, FilmEntity film, Boolean condition) {
        return builder.isEntity(condition)
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

    private void checkAllAsserts(Object before, Object after) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String[] methodNames = {"getTitle", "getOriginalLanguage", "getReleaseDate", "getRuntime",
                "getDistributor", "getProductionCo", "getAverageRatingAudience",
                "getAverageRatingCritic", "getPosterUrl",  "getLinks"};

        for (String methodName : methodNames) {
            Object beforeResult = before.getClass().getMethod(methodName).invoke(before);
            Object afterResult = after.getClass().getMethod(methodName).invoke(after);
            assertEquals(beforeResult, afterResult);
        }
    }
}
