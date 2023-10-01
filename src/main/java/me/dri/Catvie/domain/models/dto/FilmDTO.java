package me.dri.Catvie.domain.models.dto;

import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Distributor;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.Genre;

import java.util.Date;
import java.util.List;

public record FilmDTO(String title, List<Genre> genres,
                      String original_language, Director directorEntity,
                      String writer, Date release_date,
                      Integer runtime, Distributor distributor,
                      String production_co, Double average_rating_critic, Double average_rating_audience) {
}
