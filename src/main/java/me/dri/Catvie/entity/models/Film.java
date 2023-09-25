package me.dri.Catvie.entity.models;


import jakarta.persistence.*;
import me.dri.Catvie.entity.enums.Genres;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Genres genre;
    private String original_language;
    private String director;
    private String writer;
    private Date release_date;
    private Integer runtime;
    private String distributor;
    private String production_co;
    private Double average_rating;

    public Film() {

    }

    public Film(Long id, String title, Genres genre, String original_language, String director, String writer, Date release_date, Integer runtime, String distributor, String production_co, Double average_rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.original_language = original_language;
        this.director = director;
        this.writer = writer;
        this.release_date = release_date;
        this.runtime = runtime;
        this.distributor = distributor;
        this.production_co = production_co;
        this.average_rating = average_rating;
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

    public Genres getGenre() {
        return genre;
    }

    public void setGenre(Genres genre) {
        this.genre = genre;
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

    public Double getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(Double average_rating) {
        this.average_rating = average_rating;
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
                ", genre=" + genre +
                ", original_language='" + original_language + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", release_date=" + release_date +
                ", runtime=" + runtime +
                ", distributor='" + distributor + '\'' +
                ", production_co='" + production_co + '\'' +
                ", average_rating=" + average_rating +
                '}';
    }
}
