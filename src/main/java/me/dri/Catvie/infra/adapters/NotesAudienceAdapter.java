package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.ports.repositories.NotesAudiencesPort;
import me.dri.Catvie.infra.jpa.FilmRepositoryJPA;
import me.dri.Catvie.infra.jpa.NotesAudiencesRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NotesAudienceAdapter implements NotesAudiencesPort {


    private final NotesAudiencesRepositoryJPA repositoryJPA;

    private final FilmRepositoryJPA filmRepositoryJPA;

    @Autowired
    public NotesAudienceAdapter(NotesAudiencesRepositoryJPA repositoryJPA, FilmRepositoryJPA filmRepositoryJPA) {
        this.repositoryJPA = repositoryJPA;
        this.filmRepositoryJPA = filmRepositoryJPA;
    }



    @Override
    public Long addNote(Double note, Film film) {
        var filmEntity = this.filmRepositoryJPA.findFilmById(film.getId()).orElseThrow(() -> new NotFoundFilm("Film by id not found"));
        return null;
    }

    @Override
    public Double getAverageNotesByFilmId(Long id) {
        return null;
    }

    @Override
    public Double getAverageNotesByFilmName(String name) {
        return null;
    }
}
