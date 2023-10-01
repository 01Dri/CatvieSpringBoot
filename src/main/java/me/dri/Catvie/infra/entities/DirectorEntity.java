package me.dri.Catvie.infra.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "directors")
public class DirectorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "director")
    @JsonIgnore
    private List<FilmEntity> films = new ArrayList<>();

    public DirectorEntity() {

    }

    public DirectorEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FilmEntity> getFilms() {
        return films;
    }

    public void setFilms(List<FilmEntity> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectorEntity directorEntity = (DirectorEntity) o;
        return Objects.equals(id, directorEntity.id) && Objects.equals(name, directorEntity.name) && Objects.equals(films, directorEntity.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, films);
    }

    @Override
    public String toString() {
        return "DirectorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", films=" + films +
                '}';
    }
}
