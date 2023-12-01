package me.dri.Catvie.domain.models.dto.film;

import me.dri.Catvie.domain.models.dto.director.DirectorCreateDTO;
import me.dri.Catvie.domain.models.dto.director.DirectorDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.dto.user.UserDTO;
import me.dri.Catvie.domain.models.dto.user.UserResponseDTO;

import java.util.Date;
import java.util.Set;

public record FilmDTO(String title, Set<GenreDTO> genres,
                      String originalLanguage, DirectorDTO director,
                      String writer, Date releaseDate,
                      Integer runtime, String distributor,
                      String productionCo, Double averageRatingCritic, Double averageRatingAudience,
                      String posterUrl, UserDTO postedBy) {
}

