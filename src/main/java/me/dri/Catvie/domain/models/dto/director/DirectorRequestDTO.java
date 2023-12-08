package me.dri.Catvie.domain.models.dto.director;

import java.io.Serializable;
import java.util.Objects;

public class DirectorRequestDTO implements Serializable {

    private String name;


    public DirectorRequestDTO() {

    }
    public DirectorRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectorRequestDTO that = (DirectorRequestDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "DirectorRequestDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}



