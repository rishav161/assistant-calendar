package com.calendarassistant.repository;

import java.util.Optional;
import java.util.UUID;

import com.calendarassistant.model.User;

public interface UserRepository {
    void save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
}