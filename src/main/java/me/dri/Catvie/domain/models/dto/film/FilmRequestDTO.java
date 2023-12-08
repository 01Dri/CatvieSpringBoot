package me.dri.Catvie.domain.models.dto.film;

import me.dri.Catvie.domain.models.dto.director.DirectorRequestDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreRequestDTO;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class FilmRequestDTO implements Serializable {


    private String title;
    private Set<GenreRequestDTO> genres;

    private String originalLanguage;

    private DirectorRequestDTO director;

    private String writer;
    private Date releaseDate;

    private Integer runtime;
    private String distributor;

    private String productionCo;

    private String posterUrl;


    public FilmRequestDTO() {

    }

    public FilmRequestDTO(String title, Set<GenreRequestDTO> genres, String originalLanguage, DirectorRequestDTO director, String writer, Date releaseDate, Integer runtime, String distributor, String productionCo, String posterUrl) {
        this.title = title;
        this.genres = genres;
        this.originalLanguage = originalLanguage;
        this.director = director;
        this.writer = writer;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.distributor = distributor;
        this.productionCo = productionCo;
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<GenreRequestDTO> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreRequestDTO> genres) {
        this.genres = genres;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public DirectorRequestDTO getDirector() {
        return director;
    }

    public void setDirector(DirectorRequestDTO director) {
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

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmRequestDTO that = (FilmRequestDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(genres, that.genres) && Objects.equals(originalLanguage, that.originalLanguage) && Objects.equals(director, that.director) && Objects.equals(writer, that.writer) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(runtime, that.runtime) && Objects.equals(distributor, that.distributor) && Objects.equals(productionCo, that.productionCo) && Objects.equals(posterUrl, that.posterUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genres, originalLanguage, director, writer, releaseDate, runtime, distributor, productionCo, posterUrl);
    }

    @Override
    public String toString() {
        return "FilmRequestDTO{" +
                "title='" + title + '\'' +
                ", genres=" + genres +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", director=" + director +
                ", writer='" + writer + '\'' +
                ", releaseDate=" + releaseDate +
                ", runtime=" + runtime +
                ", distributor='" + distributor + '\'' +
                ", productionCo='" + productionCo + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                '}';
    }

}

