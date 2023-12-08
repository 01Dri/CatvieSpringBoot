package me.dri.Catvie.domain.models.dto.director;

import me.dri.Catvie.domain.models.entities.Film;

import java.util.List;

public record DirectorResponseDTO(String name, List<Film> FilmRequestDTOS){
}
