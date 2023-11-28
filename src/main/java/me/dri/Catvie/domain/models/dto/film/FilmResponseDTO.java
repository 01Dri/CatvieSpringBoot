package me.dri.Catvie.domain.models.dto.film;

import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.entities.Distributor;

import java.util.Date;
import java.util.Set;

public record FilmResponseDTO(String title, Set<GenreDTO> genres, String original_language, Date release_date, Integer runtime,
                              String distributor, String writer,  String production_co, Double average_rating_critic, Double average_rating_audience, String url) {
}

