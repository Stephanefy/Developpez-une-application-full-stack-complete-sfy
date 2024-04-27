package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.domains.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByUsername(String username);
}
