package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.domain.exceptions.NotFoundDirector;
import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.ports.repositories.DirectorRepositoryPort;
import me.dri.Catvie.infra.entities.DirectorEntity;
import me.dri.Catvie.infra.jpa.DirectorRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DirectorAdapter implements DirectorRepositoryPort {

    private final DirectorRepositoryJPA repositoryJPA;

    @Autowired
    public DirectorAdapter(DirectorRepositoryJPA repositoryJPA) {
        this.repositoryJPA = repositoryJPA;
    }

    @Override
    public Director findById(Long id) {
        return null;
    }

    @Override
    public List<Director> findAll() {
        return null;
    }

    @Override
    public Director findByName(String name) {
        System.out.println(name);
        try {
            DirectorEntity director = this.repositoryJPA.findByName(name).orElseThrow(() -> new NotFoundDirector("Director not found"));
            return new Director(director.getId(), director.getName());
        } catch (NullPointerException e) {
            throw new RuntimeException(e.getMessage());
        }
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
