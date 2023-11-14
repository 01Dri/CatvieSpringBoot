package me.dri.Catvie.domain.models.dto.film;

import me.dri.Catvie.domain.models.entities.Film;

import java.util.List;

public record DistributorrDTO(String name, List<Film> films) {

}


