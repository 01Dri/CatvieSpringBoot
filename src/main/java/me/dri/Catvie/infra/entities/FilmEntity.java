package me.dri.Catvie.infra.entities;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "films")
public class FilmEntity extends RepresentationModel<FilmEntity> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToMany
    @JoinTable(
            name = "film_genre",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<GenreEntity> genres;
    private String originalLanguage;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private DirectorEntity director;

    private String writer;
    @Column(name = "release_date")
    private Date releaseDate;
    private Integer runtime;
    private String distributor;
    @Column(name = "production_co")
    private String productionCo;
    @Column(name = "average_rating_critic")

    private Double averageRatingCritic;
    @Column(name = "average_rating_audience")
    private Double averageRatingAudience;

    @Column(name = "poster_url")
    private String posterUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public FilmEntity() {

    }

    @JsonCreator
    public FilmEntity(@JsonProperty("id") Long id, String title, Set<GenreEntity> genres, String originalLanguage, DirectorEntity director, String writer, Date releaseDate, Integer runtime, String distributor, String productionCo, Double averageRatingCritic, Double averageRatingAudience, String posterUrl, UserEntity user) {
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

    public Set<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreEntity> genres) {
        this.genres = genres;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public DirectorEntity getDirector() {
        return director;
    }

    public void setDirector(DirectorEntity director) {
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

    public String getProductionCo() {
        return productionCo;
    }

    public void setProductionCo(String productionCo) {
        this.productionCo = productionCo;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        FilmEntity that = (FilmEntity) object;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(genres, that.genres) && Objects.equals(originalLanguage, that.originalLanguage) && Objects.equals(director, that.director) && Objects.equals(writer, that.writer) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(runtime, that.runtime) && Objects.equals(distributor, that.distributor) && Objects.equals(productionCo, that.productionCo) && Objects.equals(averageRatingCritic, that.averageRatingCritic) && Objects.equals(averageRatingAudience, that.averageRatingAudience) && Objects.equals(posterUrl, that.posterUrl) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, title, genres, originalLanguage, director, writer, releaseDate, runtime, distributor, productionCo, averageRatingCritic, averageRatingAudience, posterUrl, user);
    }

    @Override
    public String toString() {
        return "FilmEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genres=" + genres +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", director=" + director +
                ", writer='" + writer + '\'' +
                ", releaseDate=" + releaseDate +
                ", runtime=" + runtime +
                ", distributor='" + distributor + '\'' +
                ", productionCo='" + productionCo + '\'' +
                ", averageRatingCritic=" + averageRatingCritic +
                ", averageRatingAudience=" + averageRatingAudience +
                ", posterUrl='" + posterUrl + '\'' +
                ", user=" + user +
                '}';
    }
}