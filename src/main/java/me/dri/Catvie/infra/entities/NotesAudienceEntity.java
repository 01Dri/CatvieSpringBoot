package me.dri.Catvie.infra.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "notes_audiences")
public class NotesAudienceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private FilmEntity film;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Double note;

    public NotesAudienceEntity() {

    }

    public NotesAudienceEntity(Long id, FilmEntity film, UserEntity user, Double note) {
        this.id = id;
        this.film = film;
        this.user = user;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FilmEntity getFilm() {
        return film;
    }

    public void setFilm(FilmEntity film) {
        this.film = film;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotesAudienceEntity that = (NotesAudienceEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(film, that.film) && Objects.equals(user, that.user) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, film, user, note);
    }

    @Override
    public String toString() {
        return "NotesAudienceEntity{" +
                "id=" + id +
                ", film=" + film +
                ", user=" + user +
                ", note=" + note +
                '}';
    }
}
