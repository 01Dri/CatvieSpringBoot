package me.dri.Catvie.domain.models.core;

import me.dri.Catvie.domain.enums.UserRole;
import me.dri.Catvie.utils.EntityModel;

import java.io.Serializable;
import java.util.Objects;

public class User  implements Serializable, EntityModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String token;
    private UserRole role;

    public User() {

    }

    public User(Long id, String firstName, String lastName, String email, String password, String token, UserRole role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.token = token;
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(token, user.token) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, token, role);
    }

    @Override
    public Object getDirectorObj() {
        return null;
    }

    @Override
    public Object getUserObj() {
        return this;
    }
}
