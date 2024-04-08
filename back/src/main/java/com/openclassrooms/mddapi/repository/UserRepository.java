package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}