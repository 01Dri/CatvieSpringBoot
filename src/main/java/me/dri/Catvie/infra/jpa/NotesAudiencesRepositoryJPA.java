package me.dri.Catvie.infra.jpa;


import me.dri.Catvie.infra.entities.NotesAudienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesAudiencesRepositoryJPA extends JpaRepository<NotesAudienceEntity, Long> {


//    @Query("SELECT n.note FROM NotesAudienceEntity n WHERE n.film WHERE = :id")
//    List<Double> findAllNotesByFilmId(@Param("filmId") Long filmId);
//
//    @Query("SELECT n.note FROM NotesAudienceEntity n JOIN FilmEntity f ON n.film = f.id WHERE f.title = :tile")
//    List<String> findNotesByFilmTitle(@Param("title") String title);
//

}
