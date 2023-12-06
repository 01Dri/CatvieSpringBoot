package me.dri.Catvie.domain.models.entities;

import java.io.Serializable;
import java.util.Objects;

public class NotesAudience implements Serializable {


    private Long id;

    private Film film;

    private User user;
    private Double note;

    private Double averageNotesAudiences;
    public NotesAudience() {

    }

    public NotesAudience(Long id, Film film, User user, Double note, Double averageNotesAudiences) {
        this.id = id;
        this.film = film;
        this.user = user;
        this.note = note;
        this.averageNotesAudiences = averageNotesAudiences;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Double getAverageNotesAudiences() {
        return averageNotesAudiences;
    }

    public void setAverageNotesAudiences(Double averageNotesAudiences) {
        this.averageNotesAudiences = averageNotesAudiences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotesAudience that = (NotesAudience) o;
        return Objects.equals(id, that.id) && Objects.equals(film, that.film) && Objects.equals(user, that.user) && Objects.equals(note, that.note) && Objects.equals(averageNotesAudiences, that.averageNotesAudiences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, film, user, note, averageNotesAudiences);
    }

    @Override
    public String toString() {
        return "NotesAudience{" +
                "id=" + id +
                ", film=" + film +
                ", user=" + user +
                ", note=" + note +
                ", averageNotesAudiences=" + averageNotesAudiences +
                '}';
    }
}
