package me.dri.Catvie.domain.adapters.services.notes;

import me.dri.Catvie.domain.exceptions.film.InvalidTitleFilmException;
import me.dri.Catvie.domain.exceptions.notes.InvalidNoteException;
import me.dri.Catvie.domain.models.core.NotesAudience;
import me.dri.Catvie.domain.models.dto.notes.NotesResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.notes.NotesAudienceServicesPort;
import me.dri.Catvie.domain.ports.repositories.NotesAudiencesPort;

import java.util.List;
import java.util.stream.Collectors;

public class NotesAudienceServicesImpl implements NotesAudienceServicesPort {


    private final NotesAudiencesPort repositoryPort;



    public NotesAudienceServicesImpl(NotesAudiencesPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }
    @Override
    public NotesResponseDTO addNotesByFilmId(Double note, Long filmId, String emailUser) {
        this.validaInputNote(note);
        this.validateInput(filmId);
        this.validateInput(emailUser);
        NotesAudience notesAudienceByInfraAdapter = this.repositoryPort.addNoteByFilmId(note, filmId, emailUser);
        return new NotesResponseDTO(notesAudienceByInfraAdapter.getId(), notesAudienceByInfraAdapter.getFilm().getTitle(), notesAudienceByInfraAdapter.getNote(), notesAudienceByInfraAdapter.getAverageNotesAudiences(), notesAudienceByInfraAdapter.getUser().getId());
    }

    @Override
    public NotesResponseDTO addNotesByFilmTitle(Double note, String filmTitle, String emailUser) {
        this.validaInputNote(note);
        this.validateInput(filmTitle);
        this.validateInput(emailUser);
        NotesAudience notesAudienceByInfraAdapter = this.repositoryPort.addNoteByFilmTitle(note, filmTitle, emailUser);
        return new NotesResponseDTO(notesAudienceByInfraAdapter.getId(), notesAudienceByInfraAdapter.getFilm().getTitle(), notesAudienceByInfraAdapter.getNote(), notesAudienceByInfraAdapter.getAverageNotesAudiences(), notesAudienceByInfraAdapter.getUser().getId());
    }

    @Override
    public NotesResponseDTO changeNoteByFilmId(Double newNote, Long filmId, String emailUser, Long idNote) {
        this.validaInputNote(newNote);
        this.validateInput(filmId);
        this.validateInput(emailUser);
        this.validateInput(idNote);
        NotesAudience notesAudienceByInfraAdapter = this.repositoryPort.changeNoteByFilmId(newNote, filmId, emailUser, idNote);
        return new NotesResponseDTO(notesAudienceByInfraAdapter.getId(), notesAudienceByInfraAdapter.getFilm().getTitle(), notesAudienceByInfraAdapter.getNote(), notesAudienceByInfraAdapter.getAverageNotesAudiences(), notesAudienceByInfraAdapter.getUser().getId());
    }

    @Override
    public List<NotesResponseDTO> findAllNotes() {
        List<NotesAudience> allNotesByInfraAdapter = this.repositoryPort.findAllNotes();
        return allNotesByInfraAdapter.stream().map(notes -> new NotesResponseDTO(
                notes.getId(), notes.getFilm().getTitle(), notes.getNote(), notes.getAverageNotesAudiences(), notes.getUser().getId())
        ).collect(Collectors.toList());
    }


    private void validaInputNote(Double note) {
        if (note == null) {
            throw new InvalidNoteException("Content 'note' is invalid!!!");
        }
        if (!Double.isNaN(note) && !Double.isFinite(note)) {
            throw new InvalidNoteException("Content 'note' is invalid!!!");
        }
    }


    private void validateInput(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Content is null");
        }
    }

}
