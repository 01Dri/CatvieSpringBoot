package me.dri.Catvie.entity.models;

import jakarta.persistence.*;
import me.dri.Catvie.entity.enums.Genres;

@Entity
@Table(name = "genres")
public class Genre {


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
}
