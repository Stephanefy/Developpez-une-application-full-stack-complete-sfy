package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.domains.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
