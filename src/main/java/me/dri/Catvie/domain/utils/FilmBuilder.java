package me.dri.Catvie.domain.utils;

import me.dri.Catvie.domain.models.core.Director;
import me.dri.Catvie.domain.models.core.Film;
import me.dri.Catvie.domain.models.core.Genre;
import me.dri.Catvie.domain.models.core.User;
import me.dri.Catvie.infra.entities.DirectorEntity;
import me.dri.Catvie.infra.entities.GenreEntity;
import me.dri.Catvie.infra.entities.UserEntity;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FilmBuilder<T> implements BuilderFilm<T> {
    private Long id;
    private String title;
    private Set<Genre> genres = new HashSet<>();
    private String originalLanguage;
    private Date releaseDate;
    private Integer runtime;
    private String distributor;
    private String writer;
    private String productionCo;
    private Double averageRatingCritic;
    private Double averageRatingAudience;
    private Director director;
    private String posterUrl;
    private User postedByUser;
    private Links links;

    @Override
    public BuilderFilm withId(Long id) {
        this.id = id;
        return this;
    }


    @Override
    public BuilderFilm withTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public BuilderFilm<T> withGenre(Set<T> genres) {
        this.genres.addAll(genres.stream()
                .map(g -> new Genre(((GenreEntity) g).getId(), ((GenreEntity) g).getGenreName()))
                .toList());
        return this;
    }

    @Override
    public BuilderFilm withOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
        return this;
    }

    @Override
    public BuilderFilm withReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    @Override
    public BuilderFilm withRuntime(Integer runtime) {
        this.runtime = runtime;
        return this;
    }

    @Override
    public BuilderFilm withDistributor(String distributor) {
        this.distributor = distributor;
        return this;
    }

    @Override
    public BuilderFilm withWriter(String writer) {
        this.writer = writer;
        return this;
    }

    @Override
    public BuilderFilm withProductionCo(String productionCo) {
        this.productionCo = productionCo;
        return this;
    }

    @Override
    public BuilderFilm withAverageRatingCritic(Double averageRatingCritic) {
        this.averageRatingCritic = averageRatingCritic;
        return this;
    }

    @Override
    public BuilderFilm withAverageRatingAudience(Double averageRatingAudience) {
        this.averageRatingAudience = averageRatingAudience;
        return this;
    }

    @Override
    public BuilderFilm withDirector(Object director) {
        DirectorEntity directorObj = (DirectorEntity) director;
        this.director = new Director(directorObj.getId(), directorObj.getName());
        return this;
    }

    @Override
    public BuilderFilm withPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
        return this;
    }

    @Override
    public BuilderFilm withUser(Object user) {
        this.postedByUser = new User(((UserEntity) user).getId(), ((UserEntity) user).getFirstName(), ((UserEntity) user).getLastName(), ((UserEntity) user).getEmail(), ((UserEntity) user).getPassword(), ((UserEntity) user).getToken(),
                ((UserEntity) user).getRole());
        return this;
    }

    @Override
    public BuilderFilm withLinks(Links links) {
        this.links = links;
        return this;
    }

    @Override
    public Film build() {
        Film film =  new Film(id, title, genres, originalLanguage, director, writer, releaseDate, runtime, distributor, productionCo, averageRatingCritic, averageRatingAudience, posterUrl, postedByUser);
        this.setEachLinks(this.links, film); // Setting links hateoas  on each film
        return film;
    }

    private void setEachLinks(Links links, Film film) {
        for (Link l : links) {
            film.add(l);
        }
    }

}
