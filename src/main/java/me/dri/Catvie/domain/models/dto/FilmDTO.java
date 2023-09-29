package me.dri.Catvie.domain.models.dto;

import me.dri.Catvie.infra.adapters.entities.DirectorEntity;
import me.dri.Catvie.infra.adapters.entities.DistributorEntity;
import me.dri.Catvie.infra.adapters.entities.GenreEntity;

import java.util.Date;
import java.util.List;

public record FilmDTO(String title, List<GenreEntity> genres,
                      String original_language, DirectorEntity directorEntity,
                      String writer, Date release_date,
                      Integer runtime, DistributorEntity distributor,
                      String production_co, Double average_rating_critic, Double average_rating_audience) {
}
