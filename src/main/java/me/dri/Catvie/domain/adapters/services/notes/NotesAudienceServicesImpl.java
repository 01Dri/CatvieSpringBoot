package me.dri.Catvie.domain.adapters.services.notes;

import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.ports.interfaces.notes.NotesAudienceServicesPort;
import me.dri.Catvie.domain.ports.repositories.NotesAudiencesPort;

public class NotesAudienceServicesImpl implements NotesAudienceServicesPort {

    private final NotesAudiencesPort repositoryPort;

    public NotesAudienceServicesImpl(NotesAudiencesPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }


    @Override
    public FilmDTO addNotesByFilmId(Double note, Long filmId) {

        return null;
    }
}
