package me.dri.Catvie.domain.models.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Film implements Serializable {

    private Long id;
    private String title;

    private List<Genre> genres;
    private String original_language;

    private Director directorEntity;
    private String writer;
    private Date release_date;
    private Integer runtime;

    private Distributor distributor;
    private String production_co;
    private Double average_rating_critic;
    private Double average_rating_audience;


    public Film() {

    }

    public Film(Long id, String title, List<Genre> genres, String original_language, Director directorEntity, String writer, Date release_date, Integer runtime, Distributor distributor, String production_co, Double average_rating_critic, Double average_rating_audience) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.original_language = original_language;
        this.directorEntity = directorEntity;
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

    public void setId(Long id) {
        this.id = id;
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

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public Director getDirectorEntity() {
        return directorEntity;
    }

    public void setDirectorEntity(Director directorEntity) {
        this.directorEntity = directorEntity;
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

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
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
        return Objects.equals(id, film.id) && Objects.equals(title, film.title) && Objects.equals(genres, film.genres) && Objects.equals(original_language, film.original_language) && Objects.equals(directorEntity, film.directorEntity) && Objects.equals(writer, film.writer) && Objects.equals(release_date, film.release_date) && Objects.equals(runtime, film.runtime) && Objects.equals(distributor, film.distributor) && Objects.equals(production_co, film.production_co) && Objects.equals(average_rating_critic, film.average_rating_critic) && Objects.equals(average_rating_audience, film.average_rating_audience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genres, original_language, directorEntity, writer, release_date, runtime, distributor, production_co, average_rating_critic, average_rating_audience);
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genres=" + genres +
                ", original_language='" + original_language + '\'' +
                ", directorEntity=" + directorEntity +
                ", writer='" + writer + '\'' +
                ", release_date=" + release_date +
                ", runtime=" + runtime +
                ", distributor=" + distributor +
                ", production_co='" + production_co + '\'' +
                ", average_rating_critic=" + average_rating_critic +
                ", average_rating_audience=" + average_rating_audience +
                '}';
    }
}

