package me.dri.Catvie.domain.factory.impl;
import me.dri.Catvie.domain.factory.interfaces.IFactoryFilm;
import me.dri.Catvie.infra.entities.DirectorEntity;
import me.dri.Catvie.infra.entities.DistributorEntity;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.entities.GenreEntity;

import java.util.Date;
import java.util.List;

public class FilmFactory  implements IFactoryFilm {

    @Override
    public FilmEntity getInstance(String title, List<GenreEntity> genres, String original_language, DirectorEntity directorEntity, String writer, Date release_date, Integer runtime, DistributorEntity distributor, String production_co, Double average_rating_critic, Double average_rating_audience) {
        return new FilmEntity(null, title, genres, original_language, directorEntity, writer, release_date, runtime, distributor, production_co, average_rating_critic, average_rating_audience);
    }
}
