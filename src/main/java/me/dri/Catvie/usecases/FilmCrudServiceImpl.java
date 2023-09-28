package me.dri.Catvie.usecases;

import me.dri.Catvie.entity.interfaces.FilmCrudInterface;
import me.dri.Catvie.entity.models.Film;
import me.dri.Catvie.entity.models.dto.FilmDto;
import me.dri.Catvie.infra.interfaces.IDozerMapper;
import me.dri.Catvie.infra.repositories.FilmRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class FilmCrudServiceImpl implements FilmCrudInterface {

    private final FilmRepositoryJPA filmRepositoryJPA;
    private final IDozerMapper mapper;


    @Autowired
    public FilmCrudServiceImpl(FilmRepositoryJPA filmRepositoryJPA, IDozerMapper mapper) {
        this.filmRepositoryJPA = filmRepositoryJPA;
        this.mapper = mapper;
    }
    @Override
    public Film findById(Long id) {
        return this.filmRepositoryJPA.findFilmById(id);
    }

    @Override
    public List<Film> findAll() {
        return null;
    }

    @Override
    public Film findByTitle(String title) {
        return null;
    }

    @Override
    public void create(FilmDto filmDto) {
        Film film = this.mapper.map(filmDto, Film.class);
        this.filmRepositoryJPA.save(film);
    }


    @Override
    public Long save(Film film) {
        return null;
    }

    @Override
    public Long delete(Film film) {
        return null;
    }
}
