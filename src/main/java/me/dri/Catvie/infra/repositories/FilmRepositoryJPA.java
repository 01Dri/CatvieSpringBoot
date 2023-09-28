package me.dri.Catvie.infra.repositories;

import me.dri.Catvie.entity.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepositoryJPA  extends JpaRepository<Film, Long> {
}
