package me.dri.Catvie.domain.utils;

import me.dri.Catvie.domain.models.core.User;
import org.springframework.hateoas.Links;

import java.util.Date;
import java.util.Set;

public interface BuilderFilm<T> {

    BuilderFilm withId(Long id);
    BuilderFilm withTitle(String id);
    BuilderFilm<T> withGenre(Set<T> genres);
    BuilderFilm withOriginalLanguage(String id);
    BuilderFilm withReleaseDate(Date id);
    BuilderFilm withRuntime(Integer id);
    BuilderFilm withDistributor(String id);
    BuilderFilm withWriter(String id);
    BuilderFilm withProductionCo(String id);
    BuilderFilm withAverageRatingCritic(Double id);
    BuilderFilm withAverageRatingAudience(Double id);
    BuilderFilm withDirector(Object id);

    BuilderFilm withPosterUrl(String posterUrl);
    BuilderFilm withUser(Object id);
    BuilderFilm withLinks(Links links);

    Object build();


}
