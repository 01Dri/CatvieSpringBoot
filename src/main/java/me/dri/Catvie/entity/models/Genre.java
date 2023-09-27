package me.dri.Catvie.entity.models;

import jakarta.persistence.*;
import me.dri.Catvie.entity.enums.Genres;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "genres")
public class Genre implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
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

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genreName=" + genreName +
                '}';
    }
}
