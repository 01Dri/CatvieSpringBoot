package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.domain.exceptions.NotFoundDirector;
import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.ports.repositories.DirectorRepositoryPort;
import me.dri.Catvie.infra.entities.DirectorEntity;
import me.dri.Catvie.infra.jpa.DirectorRepositoryJPA;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DirectorAdapter implements DirectorRepositoryPort {

    private final DirectorRepositoryJPA repositoryJPA;

    private final ModelMapper modelMapper;


    @Autowired
    public DirectorAdapter(DirectorRepositoryJPA repositoryJPA, ModelMapper modelMapper) {
        this.repositoryJPA = repositoryJPA;
        this.modelMapper = modelMapper;
    }

    @Override
    public Director findById(Long id) {
        return null;
    }

    @Override
    public Director findByName(String name) {
        DirectorEntity director = this.repositoryJPA.findByName(name).orElseThrow(() -> new NotFoundDirector("Director not found"));
        return this.modelMapper.map(director, Director.class);
    }

    @Override
    public List<Director> findAll() {
        return null;
    }

    @Override
    public void create(Director FilmRequestDTO) {

    }

    @Override
    public void save(Director film) {

    }

    @Override
    public void delete(Director film) {

    }

    @Override
    public Director update(Director film) {
        return null;
    }
}
