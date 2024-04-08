package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.domain.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
