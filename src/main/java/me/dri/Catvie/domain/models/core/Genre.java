package me.dri.Catvie.domain.models.core;

import me.dri.Catvie.domain.enums.Genres;

import java.io.Serializable;
import java.util.Objects;

public class Genre  implements Serializable {

    private Long id;
    private Genres genreName;

    public Genre() {

    }

    public Genre(Long id, Genres genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genres getGenreName() {
        return genreName;
    }

    public void setGenreName(Genres genreName) {
        this.genreName = genreName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id) && genreName == genre.genreName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genreName);
    }

}
