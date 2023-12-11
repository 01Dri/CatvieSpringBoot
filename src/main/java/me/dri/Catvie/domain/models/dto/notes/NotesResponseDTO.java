package me.dri.Catvie.domain.models.dto.notes;

public record NotesResponseDTO(Long idNote, String nameFilm,
                               Double noteAdded, Double averageRatingAudienceFilm, Long idUserNote) {
}
