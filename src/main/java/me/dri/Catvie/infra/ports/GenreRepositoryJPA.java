package me.dri.Catvie.infra.ports;

import me.dri.Catvie.domain.enums.Genres;
import me.dri.Catvie.infra.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface GenreRepositoryJPA extends JpaRepository<GenreEntity, Long> {

    Optional<GenreEntity> findBygenreName(Genres genreName);
    @Query("SELECT g FROM GenreEntity g")
    Optional<Set<GenreEntity>> findAllSet();
}
