package com.itmo.lab4.backend.database;

import com.itmo.lab4.backend.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
