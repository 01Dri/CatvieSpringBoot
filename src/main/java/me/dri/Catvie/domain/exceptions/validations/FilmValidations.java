package me.dri.Catvie.domain.exceptions.validations;

import me.dri.Catvie.domain.exceptions.film.InvalidReleaseDateFilmException;
import me.dri.Catvie.domain.exceptions.film.InvalidRuntimeFilmException;
import me.dri.Catvie.domain.exceptions.validations.options.ValidationsOptions;
import me.dri.Catvie.domain.models.dto.film.FilmRequestDTO;

public class FilmValidations {


    public static void validateFilmRequestDto(FilmRequestDTO filmRequestDTO) throws IllegalAccessException {
        ValidationsOptions.validateStringInputs(filmRequestDTO);
        validateFilmRequestDtoRuntime(filmRequestDTO);
        validateFilmRequestReleaseDate(filmRequestDTO);

    }

    private static void validateFilmRequestDtoRuntime(FilmRequestDTO filmRequestDTO) {
        if (filmRequestDTO.getRuntime() == null) {
            throw new InvalidRuntimeFilmException("Content runtime is null");
        }
    }

    private static void validateFilmRequestReleaseDate(FilmRequestDTO filmRequestDTO) {
        if (filmRequestDTO.getReleaseDate() == null)
            throw new InvalidReleaseDateFilmException("Content releaseDate is null");
    }
}
