package me.dri.Catvie.domain.factory.interfaces;
import me.dri.Catvie.infra.adapters.entities.DirectorEntity;
import me.dri.Catvie.infra.adapters.entities.DistributorEntity;
import me.dri.Catvie.infra.adapters.entities.FilmEntity;
import me.dri.Catvie.infra.adapters.entities.GenreEntity;

import java.util.Date;
import java.util.List;

public interface IFactoryFilm {

    FilmEntity getInstance(String title, List<GenreEntity> genres, String original_language, DirectorEntity directorEntity, String writer, Date release_date, Integer runtime, DistributorEntity distributor, String production_co, Double average_rating_critic, Double average_rating_audience);


}
