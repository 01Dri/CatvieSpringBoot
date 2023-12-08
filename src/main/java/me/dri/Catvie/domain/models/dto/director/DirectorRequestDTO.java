package me.dri.Catvie.domain.models.dto.director;

import me.dri.Catvie.domain.models.dto.film.FilmRequestDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class DirectorRequestDTO implements Serializable {

    private String name;

    private List<FilmRequestDTO> films;

    public DirectorRequestDTO(String name, List<FilmRequestDTO> films) {
        this.name = name;
        this.films = films;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FilmRequestDTO> getFilms() {
        return films;
    }

    public void setFilms(List<FilmRequestDTO> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectorRequestDTO that = (DirectorRequestDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(films, that.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, films);
    }

    @Override
    public String toString() {
        return "DirectorRequestDTO{" +
                "name='" + name + '\'' +
                ", films=" + films +
                '}';
    }
}



