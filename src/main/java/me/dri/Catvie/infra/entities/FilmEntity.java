package me.dri.Catvie.infra.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "films")
public class FilmEntity  implements Serializable {

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

    public FilmEntity(Long id, String title, Set<GenreEntity> genres, String originalLanguage, DirectorEntity director, String writer, Date releaseDate, Integer runtime, String distributor, String productionCo, Double averageRatingCritic, Double averageRatingAudience, String posterUrl, UserEntity user) {
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
}