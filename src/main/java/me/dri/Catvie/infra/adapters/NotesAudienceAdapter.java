package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.ports.repositories.NotesAudiencesPort;
import me.dri.Catvie.infra.entities.NotesAudienceEntity;
import me.dri.Catvie.infra.jpa.FilmRepositoryJPA;
import me.dri.Catvie.infra.jpa.NotesAudiencesRepositoryJPA;
import me.dri.Catvie.infra.ports.mappers.MapperFilmInfraPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NotesAudienceAdapter implements NotesAudiencesPort {


    private final FilmRepositoryJPA filmRepositoryJPA;

    private final NotesAudiencesRepositoryJPA audiencesRepositoryJPA;

    private final MapperFilmInfraPort mapperFilmInfraPort;

    @Autowired
    public NotesAudienceAdapter(FilmRepositoryJPA filmRepositoryJPA, NotesAudiencesRepositoryJPA audiencesRepositoryJPA, MapperFilmInfraPort mapperFilmInfraPort) {
        this.filmRepositoryJPA = filmRepositoryJPA;
        this.audiencesRepositoryJPA = audiencesRepositoryJPA;
        this.mapperFilmInfraPort = mapperFilmInfraPort;
    }

    @Override
    public Film addNoteByFilmId(Double note, Long id) {
        var filmEntity = this.filmRepositoryJPA.findFilmById(id).orElseThrow(() -> new NotFoundFilm("Film by id not found"));
        var userEntity = filmEntity.getUser();
        NotesAudienceEntity notesAudienceEntity = new NotesAudienceEntity(null, filmEntity, userEntity, note);
        this.audiencesRepositoryJPA.save(notesAudienceEntity);
        return this.mapperFilmInfraPort.convertyFilmEntityToFilm(filmEntity);
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
