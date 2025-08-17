package com.eleuthera.mcsv_boarding.dtos;

import java.util.UUID;

import com.eleuthera.mcsv_boarding.models.User;

/**
 * Data Transfer Object para la entidad User.
 */
public class UserDTO {

    private UUID id;
    private String username;
    private String email;

    /**
     * Constructor vacío.
     */
    public UserDTO() {
    }

    /**
     * Constructor que mapea una entidad User a un DTO.
     * @param user La entidad User de la que se obtendrán los datos.
     */
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getName();
        this.email = user.getEmail();
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}