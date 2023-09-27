package me.dri.Catvie.entity.factory.impl;
import me.dri.Catvie.entity.factory.interfaces.IFactoryFilm;
import me.dri.Catvie.entity.models.Director;
import me.dri.Catvie.entity.models.Distributor;
import me.dri.Catvie.entity.models.Film;
import me.dri.Catvie.entity.models.Genre;

import java.util.Date;
import java.util.List;

public class FilmFactory  implements IFactoryFilm {

    @Override
    public Film getInstance(String title, List<Genre> genres, String original_language, Director director, String writer, Date release_date, Integer runtime, Distributor distributor, String production_co, Double average_rating_critic, Double average_rating_audience) {
        return new Film(null, title, genres, original_language, director, writer, release_date, runtime, distributor, production_co, average_rating_critic, average_rating_audience);
    }
}
