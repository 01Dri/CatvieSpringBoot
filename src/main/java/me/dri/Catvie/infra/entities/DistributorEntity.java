package me.dri.Catvie.infra.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "distributors")
public class DistributorEntity  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "distributor")
    @JsonIgnore
    private List<FilmEntity> filmList = new ArrayList<>();


    public DistributorEntity() {

    }

    public DistributorEntity(Long id, String name) {
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

    public List<FilmEntity> getFilmList() {
        return filmList;
    }

    public void setFilmList(List<FilmEntity> filmList) {
        this.filmList = filmList;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistributorEntity that = (DistributorEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(filmList, that.filmList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, filmList);
    }

    @Override
    public String toString() {
        return "DistributorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", filmList=" + filmList +
                '}';
    }
}
