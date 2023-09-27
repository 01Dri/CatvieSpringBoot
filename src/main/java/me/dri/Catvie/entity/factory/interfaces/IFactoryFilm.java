package me.dri.Catvie.entity.factory.interfaces;
import me.dri.Catvie.entity.models.Film;
import me.dri.Catvie.entity.models.Genre;

import java.util.Date;
import java.util.List;

public interface IFactoryFilm {

    Film getInstance(String title, List<Genre> genres, String original_language, String director, String writer, Date release_date, Integer runtime, String distributor, String production_co, Double average_rating_critic, Double average_rating_audience);


}
