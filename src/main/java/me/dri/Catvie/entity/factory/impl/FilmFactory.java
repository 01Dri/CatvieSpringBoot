package me.dri.Catvie.entity.factory.impl;

import me.dri.Catvie.entity.enums.Genres;
import me.dri.Catvie.entity.factory.interfaces.IFactoryFilm;
import me.dri.Catvie.entity.models.Film;

import java.util.Date;
import java.util.List;

public class FilmFactory  implements IFactoryFilm {

    @Override
    public Film getInstance(String title, List<Genres> genres, String original_language, String director, String writer, Date release_date, Integer runtime, String distributor, String production_co, Double average_rating_critic, Double average_rating_audience) {
        return new Film(null, title, genres, original_language, director, writer, release_date, runtime, distributor, production_co, average_rating_critic, average_rating_audience);
    }
}
