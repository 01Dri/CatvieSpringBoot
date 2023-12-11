package me.dri.Catvie.infra.jpa;


import me.dri.Catvie.infra.entities.NotesAudienceEntity;
import me.dri.Catvie.infra.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotesAudiencesRepositoryJPA extends JpaRepository<NotesAudienceEntity, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM NotesAudienceEntity n WHERE n.film.id = :filmId")
    void deleteByFilmId(@Param("filmId") Long id);

    @Query("SELECT n.user FROM NotesAudienceEntity n WHERE n.user.id = :userId  AND  n.film.id = :filmId")
    Optional<UserEntity> findUserAlreadyRatedFilm(@Param("userId") Long userId, @Param("filmId") Long filmId);

    @Modifying
    @Transactional
    @Query("SELECT n.note FROM NotesAudienceEntity n WHERE n.film.id = :idFilm" )
    List<Double> findAllNotesByFilmId(@Param("idFilm") Long idFilm);

    @Query("SELECT n FROM NotesAudienceEntity n WHERE n.user.id = :userId")
    Optional<NotesAudienceEntity> findByUserId(@Param("userId") Long userId);

    @Query("SELECT n FROM NotesAudienceEntity n")
    List<NotesAudienceEntity> findAllNotes();
}
