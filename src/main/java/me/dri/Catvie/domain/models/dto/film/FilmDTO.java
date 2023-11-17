package me.dri.Catvie.domain.models.dto.film;

import me.dri.Catvie.domain.models.dto.director.DirectorCreateDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreDTO;

import java.util.Date;
import java.util.Set;

public record FilmDTO(String title, Set<GenreDTO> genres,
                      String original_language, DirectorCreateDTO director,
                      String writer, Date release_date,
                      Integer runtime, String distributor,
                      String production_co, Double average_rating_critic, Double average_rating_audience, String url) {
}
