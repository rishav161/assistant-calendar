package com.calendarassistant.model;

import java.util.Objects;
import java.util.UUID;

public class User {
    private UUID id;
    private String email;
    private String name;

    public User() {
        this.id = UUID.randomUUID();
    }

    public User(String email, String name) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.name = name;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) || Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}