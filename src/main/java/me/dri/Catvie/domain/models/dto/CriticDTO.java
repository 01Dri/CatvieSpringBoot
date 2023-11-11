package me.dri.Catvie.domain.models.dto;

import me.dri.Catvie.domain.models.entities.Film;

import java.util.List;

public record CriticDTO(String name, List<Film> films) {

}


