package me.dri.Catvie.domain.models.core;


import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;


public class Film  extends RepresentationModel<Film> implements Serializable {

    private Long id;
    private String title;

    private Set<Genre> genres;
    private String originalLanguage;

    private Director director;
    private String writer;
    private Date releaseDate;
    private Integer runtime;

    private String distributor;
    private String productionCo;
    private Double averageRatingCritic;
    private Double averageRatingAudience;

    private String posterUrl;

    private User user;




    public Film() {

    }

    public Film(Long id, String title, Set<Genre> genres, String originalLanguage, Director director, String writer, Date releaseDate, Integer runtime, String distributor, String productionCo, Double averageRatingCritic, Double averageRatingAudience, String posterUrl, User user) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.originalLanguage = originalLanguage;
        this.director = director;
        this.writer = writer;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.distributor = distributor;
        this.productionCo = productionCo;
        this.averageRatingCritic = averageRatingCritic;
        this.averageRatingAudience = averageRatingAudience;
        this.posterUrl = posterUrl;
        this.user = user;
    }

    public String getProductionCo() {
        return productionCo;
    }

    public void setProductionCo(String productionCo) {
        this.productionCo = productionCo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }


    public Double getAverageRatingCritic() {
        return averageRatingCritic;
    }

    public void setAverageRatingCritic(Double averageRatingCritic) {
        this.averageRatingCritic = averageRatingCritic;
    }

    public Double getAverageRatingAudience() {
        return averageRatingAudience;
    }

    public void setAverageRatingAudience(Double averageRatingAudience) {
        this.averageRatingAudience = averageRatingAudience;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(id, film.id) && Objects.equals(title, film.title) && Objects.equals(genres, film.genres) && Objects.equals(originalLanguage, film.originalLanguage) && Objects.equals(director, film.director) && Objects.equals(writer, film.writer) && Objects.equals(releaseDate, film.releaseDate) && Objects.equals(runtime, film.runtime) && Objects.equals(distributor, film.distributor) && Objects.equals(productionCo, film.productionCo) && Objects.equals(averageRatingCritic, film.averageRatingCritic) && Objects.equals(averageRatingAudience, film.averageRatingAudience) && Objects.equals(posterUrl, film.posterUrl) && Objects.equals(user, film.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genres, originalLanguage, director, writer, releaseDate, runtime, distributor, productionCo, averageRatingCritic, averageRatingAudience, posterUrl, user);
    }



}

