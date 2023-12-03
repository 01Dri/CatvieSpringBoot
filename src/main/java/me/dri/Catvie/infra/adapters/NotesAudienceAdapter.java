package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.exceptions.notes.UserAlreadyRatedException;
import me.dri.Catvie.domain.exceptions.user.NotFoundUser;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.ports.repositories.NotesAudiencesPort;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.entities.NotesAudienceEntity;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.jpa.FilmRepositoryJPA;
import me.dri.Catvie.infra.jpa.NotesAudiencesRepositoryJPA;
import me.dri.Catvie.infra.jpa.UserRepositoryJPA;
import me.dri.Catvie.infra.ports.mappers.MapperFilmInfraPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NotesAudienceAdapter implements NotesAudiencesPort {


    private final FilmRepositoryJPA filmRepositoryJPA;

    private final NotesAudiencesRepositoryJPA audiencesRepositoryJPA;

    private final UserRepositoryJPA userRepositoryJPA;

    private final MapperFilmInfraPort mapperFilmInfraPort;

    @Autowired
    public NotesAudienceAdapter(FilmRepositoryJPA filmRepositoryJPA, NotesAudiencesRepositoryJPA audiencesRepositoryJPA, UserRepositoryJPA userRepositoryJPA, MapperFilmInfraPort mapperFilmInfraPort) {
        this.filmRepositoryJPA = filmRepositoryJPA;
        this.audiencesRepositoryJPA = audiencesRepositoryJPA;
        this.userRepositoryJPA = userRepositoryJPA;
        this.mapperFilmInfraPort = mapperFilmInfraPort;
    }


    @Override
    public Film addNoteByFilmId(Double note, Long idFilm, String emailUser) {
        var filmEntity = this.filmRepositoryJPA.findFilmById(idFilm).orElseThrow(() -> new NotFoundFilm("Film by id not found"));
        var userEntity = this.userRepositoryJPA.findByEmail(emailUser).orElseThrow(() -> new NotFoundUser("User not found by id"));
        this.verifyIfUserAlreadyRated((UserEntity) userEntity, filmEntity);
        NotesAudienceEntity notesAudienceEntity = new NotesAudienceEntity(null, filmEntity,(UserEntity) userEntity, note);
        this.audiencesRepositoryJPA.save(notesAudienceEntity);
        var averageNoteAudience = this.getAverageNotesByFilmId(filmEntity.getId());
        filmEntity.setAverageRatingAudience(averageNoteAudience);
        this.filmRepositoryJPA.save(filmEntity);
        return this.mapperFilmInfraPort.convertyFilmEntityToFilm(filmEntity);
    }

    @Override
    public Film addNoteByFilmTitle(Double note, String titleFilm, String emailUser) {
        var filmEntity = this.filmRepositoryJPA.findFilmByTitle(titleFilm).orElseThrow(() -> new NotFoundFilm("Not found film by title"));
        var userEntity = this.userRepositoryJPA.findByEmail(emailUser).orElseThrow(() -> new NotFoundUser("Not found user by title"));
        this.verifyIfUserAlreadyRated((UserEntity) userEntity, filmEntity);
        NotesAudienceEntity notesAudienceEntity = new NotesAudienceEntity(null, filmEntity, (UserEntity) userEntity, note);
        this.audiencesRepositoryJPA.save(notesAudienceEntity);
        var averageNoteAudience = this.getAverageNotesByFilmId(filmEntity.getId());
        filmEntity.setAverageRatingAudience(averageNoteAudience);
        this.filmRepositoryJPA.save(filmEntity);
        return this.mapperFilmInfraPort.convertyFilmEntityToFilm(filmEntity);
    }

    @Override
    public Film changeNoteByFilmId(Double newNote, Long idFilm, String emailUser) { //
        // Verify if your old note exist on database, else throw exception for the user
        return null;
    }

    @Override
    public Double getAverageNotesByFilmId(Long id) {
        var notes = this.audiencesRepositoryJPA.findAllNotesByFilmId(id);
        double sum = 0;
        for (Double note: notes) {
            sum += note;
        }
        double average = sum / notes.size();
        return Math.round(average * 10.0) / 10.0;
    }

    @Override
    public Double getAverageNotesByFilmName(String name) {
        return null;
    }

    private void verifyIfUserAlreadyRated(UserEntity user, FilmEntity film) {
        var entity = this.audiencesRepositoryJPA.findUserAlreadyRatedFilm(user.getId(), film.getId());
        if (entity.isPresent()) {
            throw new UserAlreadyRatedException("User has already rated this film ");
        }
    }
}
