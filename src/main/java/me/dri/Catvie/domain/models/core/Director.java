package me.dri.Catvie.domain.models.core;


import me.dri.Catvie.utils.EntityModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Director implements Serializable, EntityModel {

    private Long id;
    private String name;

    private List<Film> films = new ArrayList<>();

    public Director() {

    }

    public Director(Long id, String name) {
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

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }


    @Override
    public Object getDirectorObj() {
        return this;
    }

    @Override
    public Object getUserObj() {
        return null;
    }
}
