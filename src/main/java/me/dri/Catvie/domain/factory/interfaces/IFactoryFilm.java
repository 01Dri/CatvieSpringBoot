package me.dri.Catvie.domain.factory.interfaces;
import me.dri.Catvie.infra.entities.DirectorEntity;
import me.dri.Catvie.infra.entities.DistributorEntity;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.entities.GenreEntity;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IFactoryFilm {

    FilmEntity getInstance(String title, Set<GenreEntity> genres, String original_language, DirectorEntity directorEntity, String writer, Date release_date, Integer runtime, DistributorEntity distributor, String production_co, Double average_rating_critic, Double average_rating_audience, String url);


}
