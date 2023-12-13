package me.dri.Catvie.infra.repositoriesjpa;

import me.dri.Catvie.infra.entities.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepositoryJPA  extends JpaRepository<FilmEntity, Long> {

    @Query("SELECT f FROM FilmEntity f WHERE f.id = :id")
    Optional<FilmEntity> findFilmById(@Param("id") Long id);

    @Query("SELECT f FROM FilmEntity f")
    Optional<List<FilmEntity>> findAllFilms();

    @Query("SELECT f FROM FilmEntity f WHERE f.title = :title")
    Optional<FilmEntity> findFilmByTitle(@Param("title") String title);



}
