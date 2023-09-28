package me.dri.Catvie.infra.repositories;

import jakarta.persistence.Id;
import me.dri.Catvie.entity.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FilmRepositoryJPA  extends JpaRepository<Film, Long> {



    @Query("SELECT f FROM Film f WHERE f.id = :id")
    Film findFilmById(@Param("id") Long id);
}
