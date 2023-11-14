package me.dri.Catvie.domain.ports.interfaces.genre;

import me.dri.Catvie.domain.models.dto.genre.GenreDTO;

import java.util.Set;

public interface GenreServicesPort {

    GenreDTO findById(Long id);

    Set<GenreDTO> findAll();

    GenreDTO findByName(String title);

    void create(GenreDTO genre);

    void save(GenreDTO genre);

    void delete(GenreDTO genre);

    Set<GenreDTO> verifyExistingGenres(Set<GenreDTO> genreDTOS);


}
