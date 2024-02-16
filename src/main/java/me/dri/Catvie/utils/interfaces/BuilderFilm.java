package me.dri.Catvie.utils.interfaces;

import org.springframework.hateoas.Links;

import java.util.Date;
import java.util.Set;

public interface BuilderFilm<T> {

    BuilderFilm withId(Long id);
    BuilderFilm withTitle(String id);
    BuilderFilm withGenre(Set<Object> genres);
    BuilderFilm withOriginalLanguage(String id);
    BuilderFilm withReleaseDate(Date id);
    BuilderFilm withRuntime(Integer id);
    BuilderFilm withDistributor(String id);
    BuilderFilm withWriter(String id);
    BuilderFilm withProductionCo(String id);
    BuilderFilm withAverageRatingCritic(Double id);
    BuilderFilm withAverageRatingAudience(Double id);
    BuilderFilm withDirector(EntityModel id);

    BuilderFilm withPosterUrl(String posterUrl);
    BuilderFilm withUser(EntityModel id);
    BuilderFilm withLinks(Links links);

    BuilderFilm isEntity(Boolean conditio);

    T build();



}
