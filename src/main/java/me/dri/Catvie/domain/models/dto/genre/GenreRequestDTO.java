package me.dri.Catvie.domain.models.dto.genre;

import me.dri.Catvie.domain.enums.Genres;

import java.io.Serializable;
import java.util.Objects;

public class GenreRequestDTO implements Serializable {

    private Genres genreName;

    public GenreRequestDTO() {
    }

    public GenreRequestDTO(Genres genreName) {
        this.genreName = genreName;
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
        GenreRequestDTO that = (GenreRequestDTO) o;
        return genreName == that.genreName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreName);
    }

    @Override
    public String toString() {
        return "GenreRequestDTO{" +
                "genreName=" + genreName +
                '}';
    }
}
