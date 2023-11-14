package me.dri.Catvie.infra.ports;

import me.dri.Catvie.domain.enums.Genres;
import me.dri.Catvie.infra.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepositoryJPA extends JpaRepository<GenreEntity, Long> {

    GenreEntity findBygenreName(Genres genreName);
}
