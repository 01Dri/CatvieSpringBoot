package me.dri.Catvie.infra.ports.mappers;

import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.infra.entities.DirectorEntity;
import org.springframework.stereotype.Component;

@Component
public interface MapperDirectorInfraPort {

    DirectorEntity convertyDirectorToDirectorEntity(Director director);

}
