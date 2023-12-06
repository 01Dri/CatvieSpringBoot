package me.dri.Catvie.domain.adapters.services.notes;

import me.dri.Catvie.domain.exceptions.film.InvalidTitleFilmException;
import me.dri.Catvie.domain.exceptions.notes.InvalidIdException;
import me.dri.Catvie.domain.exceptions.notes.InvalidNoteException;
import me.dri.Catvie.domain.models.dto.notes.NotesResponseDTO;
import me.dri.Catvie.domain.models.entities.NotesAudience;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperFilmDomainPort;
import me.dri.Catvie.domain.ports.interfaces.notes.NotesAudienceServicesPort;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.domain.ports.repositories.NotesAudiencesPort;

public class NotesAudienceServicesImpl implements NotesAudienceServicesPort {


    private final NotesAudiencesPort repositoryPort;

    private final MapperFilmDomainPort mapperFilmDomainPort;

    private final FilmRepositoryPort filmRepositoryPort;

    public NotesAudienceServicesImpl(NotesAudiencesPort repositoryPort, MapperFilmDomainPort mapperFilmDomainPort, FilmRepositoryPort filmRepositoryPort) {
        this.repositoryPort = repositoryPort;
        this.mapperFilmDomainPort = mapperFilmDomainPort;
        this.filmRepositoryPort = filmRepositoryPort;
    }
    @Override
    public NotesResponseDTO addNotesByFilmId(Double note, Long filmId, String emailUser) {
        this.validateInputId(filmId);
        this.validaInputNote(note);
        NotesAudience notesAudienceByInfraAdapter = this.repositoryPort.addNoteByFilmId(note, filmId, emailUser);
        return new NotesResponseDTO(notesAudienceByInfraAdapter.getId(), notesAudienceByInfraAdapter.getFilm().getTitle(), notesAudienceByInfraAdapter.getNote(), notesAudienceByInfraAdapter.getAverageNotesAudiences());
    }

    @Override
    public NotesResponseDTO addNotesByFilmTitle(Double note, String filmTitle, String emailUser) {
        this.validaInputNote(note);
        this.validateInputTitle(filmTitle);
        NotesAudience notesAudienceByInfraAdapter = this.repositoryPort.addNoteByFilmTitle(note, filmTitle, emailUser);
        return new NotesResponseDTO(notesAudienceByInfraAdapter.getId(), notesAudienceByInfraAdapter.getFilm().getTitle(), notesAudienceByInfraAdapter.getNote(), notesAudienceByInfraAdapter.getAverageNotesAudiences());
    }

    @Override
    public NotesResponseDTO changeNoteByFilmId(Double newNote, Long filmId, String emailUser, Long idUser) {
        this.validaInputNote(newNote);
        this.validateInputId(filmId);
        NotesAudience notesAudienceByInfraAdapter = this.repositoryPort.changeNoteByFilmId(newNote, filmId, emailUser, idUser);
        return new NotesResponseDTO(notesAudienceByInfraAdapter.getId(), notesAudienceByInfraAdapter.getFilm().getTitle(), notesAudienceByInfraAdapter.getNote(), notesAudienceByInfraAdapter.getAverageNotesAudiences());
    }


    private void validaInputNote(Double note) {
        if (note == null) {
            throw new InvalidNoteException("Content 'note' is invalid!!!");
        }
        if (!Double.isNaN(note) && !Double.isFinite(note)) {
            throw new InvalidNoteException("Content 'note' is invalid!!!");
        }
    }

    private void validateInputTitle(String title) {
        if (title == null) {
            throw new InvalidTitleFilmException("Content 'title' is null");
        }
    }


    private void validateInputId(Long id) {
        if (id == null) {
            throw new InvalidIdException("Content 'id' is invalid!!!");
        }
    }
}
