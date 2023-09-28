package me.dri.Catvie.entity.models.dto;

import me.dri.Catvie.entity.models.Director;
import me.dri.Catvie.entity.models.Distributor;
import me.dri.Catvie.entity.models.Genre;

import java.util.Date;
import java.util.List;

public record FilmDto(String title, List<Genre> genres,
                      String original_language, Director director,
                      String writer, Date release_date,
                      Integer runtime, Distributor distributor,
                      String production_co, Double average_rating_critic, Double average_rating_audience) {
}
