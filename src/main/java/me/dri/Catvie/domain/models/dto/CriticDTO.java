package me.dri.Catvie.domain.models.dto;

import me.dri.Catvie.infra.adapters.entities.FilmEntity;

import java.util.List;

public record CriticDTO(String name, List<FilmEntity> films) {

}


