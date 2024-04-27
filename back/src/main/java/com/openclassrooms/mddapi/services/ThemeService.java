package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.domains.models.Theme;

import java.util.List;
import java.util.Optional;

public interface ThemeService {
    Theme createTheme(Theme theme);
    List<Theme> getAllThemes();
    Optional<Theme> getThemeById(String id);
    Theme updateTheme(String id, Theme theme);

    void subscribe(Long themeId, Long userId);

    void unsubscribe(Long userId, Long themeId);
    boolean deleteTheme(String id);
}
