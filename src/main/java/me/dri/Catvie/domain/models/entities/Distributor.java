package me.dri.Catvie.domain.models.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Distributor implements Serializable {

    private Long id;

    private String name;

    private List<Film> filmList = new ArrayList<>();


    public Distributor() {

    }

    public Distributor(Long id, String name) {
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

    public List<Film> getFilmList() {
        return filmList;
    }

    public void setFilmList(List<Film> filmList) {
        this.filmList = filmList;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distributor that = (Distributor) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(filmList, that.filmList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, filmList);
    }

    @Override
    public String toString() {
        return "Distributor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", filmList=" + filmList +
                '}';
    }
}
