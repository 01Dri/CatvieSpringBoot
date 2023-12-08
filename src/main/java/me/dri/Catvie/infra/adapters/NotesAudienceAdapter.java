package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.exceptions.notes.FilmNotRated;
import me.dri.Catvie.domain.exceptions.notes.UserAlreadyRatedException;
import me.dri.Catvie.domain.exceptions.user.NotFoundUser;
import me.dri.Catvie.domain.models.entities.NotesAudience;
import me.dri.Catvie.domain.ports.repositories.NotesAudiencesPort;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.entities.NotesAudienceEntity;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.jpa.FilmRepositoryJPA;
import me.dri.Catvie.infra.jpa.NotesAudiencesRepositoryJPA;
import me.dri.Catvie.infra.jpa.UserRepositoryJPA;
import me.dri.Catvie.infra.ports.mappers.MapperFilmInfraPort;
import me.dri.Catvie.infra.ports.mappers.MapperUserInfraPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NotesAudienceAdapter implements NotesAudiencesPort {


    private final FilmRepositoryJPA filmRepositoryJPA;

    private final NotesAudiencesRepositoryJPA audiencesRepositoryJPA;

    private final UserRepositoryJPA userRepositoryJPA;

    private final MapperFilmInfraPort mapperFilmInfraPort;

    private final MapperUserInfraPort mapperUserInfraPort;

    @Autowired
    public NotesAudienceAdapter(FilmRepositoryJPA filmRepositoryJPA, NotesAudiencesRepositoryJPA audiencesRepositoryJPA, UserRepositoryJPA userRepositoryJPA, MapperFilmInfraPort mapperFilmInfraPort, MapperUserInfraPort mapperUserInfraPort) {
        this.filmRepositoryJPA = filmRepositoryJPA;
        this.audiencesRepositoryJPA = audiencesRepositoryJPA;
        this.userRepositoryJPA = userRepositoryJPA;
        this.mapperFilmInfraPort = mapperFilmInfraPort;
        this.mapperUserInfraPort = mapperUserInfraPort;
    }


    @Override
    public NotesAudience addNoteByFilmId(Double note, Long idFilm, String emailUser) {
        var filmEntity = this.filmRepositoryJPA.findFilmById(idFilm).orElseThrow(() -> new NotFoundFilm("Film by id not found"));
        var userEntity = this.userRepositoryJPA.findByEmail(emailUser).orElseThrow(() -> new NotFoundUser("User not found by id"));
        this.verifyIfUserAlreadyRated((UserEntity) userEntity, filmEntity);
        NotesAudienceEntity notesAudienceEntity = new NotesAudienceEntity(null, filmEntity,(UserEntity) userEntity, note);
        var entity = this.audiencesRepositoryJPA.save(notesAudienceEntity);
        var averageNoteAudience = this.getAverageNotesByFilmId(filmEntity.getId());
        filmEntity.setAverageRatingAudience(averageNoteAudience);
        this.filmRepositoryJPA.save(filmEntity);
        return new NotesAudience(entity.getId(), this.mapperFilmInfraPort.convertyFilmEntityToFilm(filmEntity),
                this.mapperUserInfraPort.convertUserEntityToUser((UserEntity) userEntity), note, filmEntity.getAverageRatingAudience());
    }

    @Override
    public NotesAudience addNoteByFilmTitle(Double note, String titleFilm, String emailUser) {
        var filmEntity = this.filmRepositoryJPA.findFilmByTitle(titleFilm).orElseThrow(() -> new NotFoundFilm("Not found film by title"));
        var userEntity = this.userRepositoryJPA.findByEmail(emailUser).orElseThrow(() -> new NotFoundUser("Not found user by title"));
        this.verifyIfUserAlreadyRated((UserEntity) userEntity, filmEntity);
        NotesAudienceEntity notesAudienceEntity = new NotesAudienceEntity(null, filmEntity, (UserEntity) userEntity, note);
        var entity = this.audiencesRepositoryJPA.save(notesAudienceEntity);
        Long idNote = entity.getId();
        var averageNoteAudience = this.getAverageNotesByFilmId(filmEntity.getId());
        filmEntity.setAverageRatingAudience(averageNoteAudience);
        this.filmRepositoryJPA.save(filmEntity);
        return new NotesAudience(idNote, this.mapperFilmInfraPort.convertyFilmEntityToFilm(filmEntity),
                this.mapperUserInfraPort.convertUserEntityToUser((UserEntity) userEntity), note, filmEntity.getAverageRatingAudience());

    }

    @Override
    public NotesAudience changeNoteByFilmId(Double newNote, Long idFilm, String emailUser, Long idNote) { //
        // Verify if your old note exist on database, else throw exception for the user
        var filmEntity = this.filmRepositoryJPA.findFilmById(idFilm).orElseThrow(() -> new NotFoundFilm("Not found film by title"));
        var userEntity = this.userRepositoryJPA.findByEmail(emailUser).orElseThrow(() -> new NotFoundUser("Not found user by title"));
        if (this.verifyAlreadyRatedThisFilm((UserEntity) userEntity, filmEntity)) {
            var noteExisting = this.audiencesRepositoryJPA.findById(idNote).orElseThrow(() -> new NotFoundUser("Not found user by id"));
            noteExisting.setNote(newNote);
            this.audiencesRepositoryJPA.save(noteExisting);
        }
        return new NotesAudience(idNote, this.mapperFilmInfraPort.convertyFilmEntityToFilm(filmEntity),
                this.mapperUserInfraPort.convertUserEntityToUser((UserEntity) userEntity), newNote, filmEntity.getAverageRatingAudience());
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

    private boolean verifyAlreadyRatedThisFilm(UserEntity user, FilmEntity film) {
        var entity  = this.audiencesRepositoryJPA.findUserAlreadyRatedFilm(user.getId(), film.getId());
        if (entity.isEmpty()) {
            throw new FilmNotRated("User doesn't rated this film");
        }
        return true;
    }
}
