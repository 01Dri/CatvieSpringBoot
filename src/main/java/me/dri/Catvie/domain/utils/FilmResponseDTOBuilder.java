package me.dri.Catvie.domain.utils;

import me.dri.Catvie.domain.models.core.Director;
import me.dri.Catvie.domain.models.core.Genre;
import me.dri.Catvie.domain.models.core.User;
import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreResponseDTO;
import me.dri.Catvie.domain.models.dto.user.UserResponseFilmRequestDTO;
import org.springframework.hateoas.Links;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FilmResponseDTOBuilder<T> implements BuilderFilm<T> {
    private Long id;
    private String title;
    private Set<GenreResponseDTO> genres = new HashSet<>();
    private String originalLanguage;
    private Date releaseDate;
    private Integer runtime;
    private String distributor;
    private String writer;
    private String productionCo;
    private Double averageRatingCritic;
    private Double averageRatingAudience;
    private DirectorResponseDTO director;
    private String posterUrl;
    private UserResponseFilmRequestDTO postedByUser;
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
                .map(g -> new GenreResponseDTO(((Genre) g).getId(), ((Genre) g).getGenreName()))
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
        Director directorObj = (Director) director;
        this.director = new DirectorResponseDTO(directorObj.getId(), directorObj.getName());
        return this;
    }

    @Override
    public BuilderFilm withPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
        return this;
    }

    @Override
    public BuilderFilm withUser(Object user) {
        if (user instanceof User) {
            this.postedByUser = new UserResponseFilmRequestDTO(((User) user).getId());
        }
        return this;
    }

    @Override
    public BuilderFilm withLinks(Links links) {
        this.links = links;
        return this;
    }

    @Override
    public BuilderFilm isEntity(Boolean conditio) {
        return null;
    }

    @Override
    public FilmResponseDTO build() {
        return new FilmResponseDTO(id, title, genres, originalLanguage, releaseDate, runtime, distributor, writer, productionCo, averageRatingCritic, averageRatingAudience, director, posterUrl, postedByUser, links);
    }
}
