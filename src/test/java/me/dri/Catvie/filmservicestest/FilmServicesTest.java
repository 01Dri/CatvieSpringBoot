package me.dri.Catvie.filmservicestest;

import me.dri.Catvie.entity.interfaces.FilmCrudInterface;
import me.dri.Catvie.entity.models.Film;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FilmServicesTest {


    @Mock
    private JpaRepository<Film, Long> jpaRepository;

    private FilmCrudInterface serviceCrud;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        serviceCrud = new FilmCrudServiceImpl(jpaRepository);
    }

    @Test
    void testCreate() {
        Film filmCreate = new Film();
        Film filmSaved = new Film();
        when(jpaRepository.save(filmCreate)).thenReturn(filmSaved.getId());

        Long result = this.serviceCrud.create(filmCreate);
        assertEquals(filmSaved.getId(), result);
        verify(this.jpaRepository, times(1)).save(filmCreate);
    }

}
