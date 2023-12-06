package me.dri.Catvie.domain.models.dto.film;

import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.dto.user.UserResponseFilmDTO;

import java.util.Date;
import java.util.Set;

public record FilmResponseDTO(Long id, String title, Set<GenreDTO> genres, String originalLanguage, Date releaseDate, Integer runtime,
                              String distributor, String writer,
                              String productionGo, Double averageRatingCritic,
                              Double averageRatingAudience, String posterUrl, UserResponseFilmDTO postedByUser) {
}

