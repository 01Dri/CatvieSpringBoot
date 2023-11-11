package me.dri.Catvie.infra.ports;

import me.dri.Catvie.infra.entities.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepositoryJPA  extends JpaRepository<FilmEntity, Long> {

    @Query("SELECT f FROM FilmEntity f WHERE f.id = :id")
    FilmEntity findFilmById(@Param("id") Long id);

    @Query("SELECT f FROM FilmEntity f")
    List<FilmEntity> findAllFilms();

    @Query("SELECT f FROM FilmEntity f WHERE f.title = :title")
    FilmEntity findFilmByTitle(@Param("title") String title);
}
