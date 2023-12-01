package me.dri.Catvie.infra.jpa;

import me.dri.Catvie.infra.entities.DirectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepositoryJPA  extends JpaRepository<DirectorEntity, Long> {

    @Query("SELECT d FROM DirectorEntity d WHERE d.name = :name")
    Optional<DirectorEntity> findByName(@Param("name") String name);
}
