package me.dri.Catvie.infra.repositories;

import me.dri.Catvie.entity.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepositoryJPA  extends JpaRepository<Film, Long> {



    @Query("SELECT f FROM Film f WHERE f.id = :id")
    Film findFilmById(@Param("id") Long id);

    @Query("SELECT * FROM Film")
    List<Film> findAllFilms();

    @Query("SELECT f FROM Film f WHERE f.title = :title")
    Film findFilmByTitle(@Param("title") String title);
}
