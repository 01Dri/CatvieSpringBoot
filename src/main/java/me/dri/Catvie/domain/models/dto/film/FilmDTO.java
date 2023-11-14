package me.dri.Catvie.domain.models.dto.film;

import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Distributor;
import me.dri.Catvie.domain.models.entities.Genre;

import java.util.Date;
import java.util.List;
import java.util.Set;

public record FilmDTO(String title, Set<GenreDTO> genres,
                      String original_language, Director directorEntity,
                      String writer, Date release_date,
                      Integer runtime, Distributor distributor,
                      String production_co, Double average_rating_critic, Double average_rating_audience, String url) {
}
