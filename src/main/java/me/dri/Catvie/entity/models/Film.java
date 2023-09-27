package me.dri.Catvie.entity.models;


import jakarta.persistence.*;
import me.dri.Catvie.entity.enums.Genres;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "films")
public class Film {

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
    private List<Genre> genres;
    private String original_language;
    private String director;
    private String writer;
    private Date release_date;
    private Integer runtime;
    private String distributor;
    private String production_co;
    private Double average_rating_critic;
    private Double average_rating_audience;


    public Film() {

    }

    public Film(Long id, String title, List<Genre> genres, String original_language, String director, String writer, Date release_date, Integer runtime, String distributor, String production_co, Double average_rating_critic, Double average_rating_audience) {
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
    }

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Genre> getGenres() {
        return genres;
    }


    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
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

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(id, film.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genres=" + genres +
                ", original_language='" + original_language + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", release_date=" + release_date +
                ", runtime=" + runtime +
                ", distributor='" + distributor + '\'' +
                ", production_co='" + production_co + '\'' +
                ", average_rating_critic=" + average_rating_critic +
                ", average_rating_audience=" + average_rating_audience +
                '}';
    }
}

