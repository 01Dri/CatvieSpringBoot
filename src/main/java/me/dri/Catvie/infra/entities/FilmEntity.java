package me.dri.Catvie.infra.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "films")
public class FilmEntity implements Serializable {

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
    private String original_language;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private DirectorEntity director;

    private String writer;
    private Date release_date;
    private Integer runtime;
    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private DistributorEntity distributor;
    private String production_co;
    private Double average_rating_critic;
    private Double average_rating_audience;

    private String url;


    public FilmEntity() {

    }

    public FilmEntity(Long id, String title, Set<GenreEntity> genres, String original_language, DirectorEntity director, String writer, Date release_date, Integer runtime, DistributorEntity distributor, String production_co, Double average_rating_critic, Double average_rating_audience, String url) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.original_language = original_language;
        this.director = director;
        this.writer = writer;
        this.release_date = release_date;
        this.runtime = runtime;
        this.distributor = distributor;
        this.production_co = production_co;
        this.average_rating_critic = average_rating_critic;
        this.average_rating_audience = average_rating_audience;
        this.url = url;
    }

    public Set<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreEntity> genres) {
        this.genres = genres;
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


    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
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

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public DistributorEntity getDistributor() {
        return distributor;
    }

    public void setDistributor(DistributorEntity distributor) {
        this.distributor = distributor;
    }

    public String getProduction_co() {
        return production_co;
    }

    public void setProduction_co(String production_co) {
        this.production_co = production_co;
    }

    public Double getAverage_rating_critic() {
        return average_rating_critic;
    }

    public void setAverage_rating_critic(Double average_rating_critic) {
        this.average_rating_critic = average_rating_critic;
    }

    public Double getAverage_rating_audience() {
        return average_rating_audience;
    }

    public void setAverage_rating_audience(Double average_rating_audience) {
        this.average_rating_audience = average_rating_audience;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}