package me.dri.Catvie.utils;

import me.dri.Catvie.domain.models.core.Director;
import me.dri.Catvie.domain.models.core.Genre;
import me.dri.Catvie.domain.models.core.User;
import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreResponseDTO;
import me.dri.Catvie.domain.models.dto.user.UserResponseFilmRequestDTO;
import me.dri.Catvie.utils.interfaces.BuilderFilm;
import me.dri.Catvie.utils.interfaces.EntityModel;
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
    private Object director;
    private String posterUrl;
    private Object postedByUser;
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
    public BuilderFilm withGenre(Set<Object> genres) {
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
    public BuilderFilm withDirector(EntityModel id) {
        this.director = id.getDirectorObj();
        return this;
    }

    @Override
    public BuilderFilm withPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
        return this;
    }

    @Override
    public BuilderFilm withUser(EntityModel id) {
        this.postedByUser = id.getUserObj();
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
    public T build() {
        return (T) new FilmResponseDTO(id, title, genres, originalLanguage, releaseDate, runtime, distributor, writer,
                productionCo, averageRatingCritic, averageRatingAudience, new DirectorResponseDTO(((Director)director).getId(), ((Director)director).getName()) , posterUrl, new UserResponseFilmRequestDTO(((User)postedByUser).getId()), links);
    }
}


