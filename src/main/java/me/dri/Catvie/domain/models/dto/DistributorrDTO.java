package me.dri.Catvie.domain.models.dto;

import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.infra.entities.FilmEntity;

import java.util.List;

public record DistributorrDTO(String name, List<Film> films) {

}


