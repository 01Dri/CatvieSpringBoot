package me.dri.Catvie.domain.adapters.services.mappers;

import me.dri.Catvie.domain.models.dto.FilmDTO;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.ports.interfaces.MapperEntitiesPort;

import java.util.List;
import java.util.stream.Collectors;

public class MapperFilmImpl implements MapperEntitiesPort {


    @Override
    public FilmDTO convertFilmToDto(Film film) {
       return new FilmDTO(film.getTitle(), film.getGenres(), film.getOriginal_language(), film.getDirectorEntity(), film.getWriter(), film.getRelease_date(), film.getRuntime(), film.getDistributor(), film.getProduction_co(), film.getAverage_rating_critic(), film.getAverage_rating_audience(), film.getUrl());
    }

    @Override
    public List<FilmDTO> convertListFilmToListDto(List<Film> films) {
        return films.stream().map(film -> new FilmDTO(film.getTitle(), film.getGenres(), film.getOriginal_language(), film.getDirectorEntity(), film.getWriter(), film.getRelease_date(), film.getRuntime(), film.getDistributor(), film.getProduction_co(), film.getAverage_rating_critic(), film.getAverage_rating_audience(), film.getUrl())).collect(Collectors.toList());
    }

    @Override
    public Film convertFilmDtoToFilm(FilmDTO filmDTO) {
        return new Film(null, filmDTO.title(), filmDTO.genres(), filmDTO.original_language(), filmDTO.directorEntity(), filmDTO.writer(), filmDTO.release_date(), filmDTO.runtime(), filmDTO.distributor(), filmDTO.production_co(), filmDTO.average_rating_critic(), filmDTO.average_rating_audience(), filmDTO.url());
    }
}
