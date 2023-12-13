package me.dri.Catvie.domain.exceptions.validations;

import me.dri.Catvie.domain.exceptions.film.InvalidReleaseDateFilmException;
import me.dri.Catvie.domain.exceptions.film.InvalidRuntimeFilmException;
import me.dri.Catvie.domain.exceptions.validations.options.ValidationsOptions;
import me.dri.Catvie.domain.models.dto.film.FilmRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilmValidations {
    private static final Logger logger = LoggerFactory.getLogger(ValidationsOptions.class);

    public static void validateFilmRequestDto(FilmRequestDTO filmRequestDTO) throws IllegalAccessException {
        ValidationsOptions.validateStringInputs(filmRequestDTO);
        validateFilmRequestDtoRuntime(filmRequestDTO);
        validateFilmRequestReleaseDate(filmRequestDTO);

    }

    private static void validateFilmRequestDtoRuntime(FilmRequestDTO filmRequestDTO) {
        if (filmRequestDTO.getRuntime() == null) {
            logger.error("Input validation error in auth services");
            throw new InvalidRuntimeFilmException("Content runtime is null");
        }
    }

    private static void validateFilmRequestReleaseDate(FilmRequestDTO filmRequestDTO) {
        if (filmRequestDTO.getReleaseDate() == null) {
            logger.error("Input validation error in auth services");
            throw new InvalidReleaseDateFilmException("Content releaseDate is null");
        }
    }
}
