package me.dri.Catvie.domain.adapters.services.notes;

import me.dri.Catvie.domain.exceptions.notes.InvalidIdException;
import me.dri.Catvie.domain.exceptions.notes.InvalidNoteException;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.entities.Film;
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
    public FilmResponseDTO addNotesByFilmId(Double note, Long filmId, Long idUser) {
        this.validateInputId(filmId);
        this.validaInputNote(note);
        Film filmByInfraAdapter = this.repositoryPort.addNoteByFilmId(note, filmId, idUser);
        return this.mapperFilmDomainPort.convertFilmToResponseDTO(filmByInfraAdapter);
    }


    private void validaInputNote(Double note) {
        if (note == null) {
            throw new InvalidNoteException("Content 'note' is invalid!!!");
        }
        if (!Double.isNaN(note) && !Double.isFinite(note)) {
            throw new InvalidNoteException("Content 'note' is invalid!!!");
        }
    }


    private void validateInputId(Long id) {
        if (id == null) {
            throw new InvalidIdException("Content 'id' is invalid!!!");
        }
    }
}
