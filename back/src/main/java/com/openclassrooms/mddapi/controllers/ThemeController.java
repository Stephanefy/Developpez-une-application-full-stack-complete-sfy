package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.domain.dtos.theme.CreateThemeDTO;
import com.openclassrooms.mddapi.domain.dtos.theme.ThemeDTO;
import com.openclassrooms.mddapi.domain.models.Theme;
import com.openclassrooms.mddapi.exceptions.NotFoundException;
import com.openclassrooms.mddapi.services.ThemeService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/themes")
@Log4j2
@CrossOrigin(origins = "http://localhost:4200")
public class ThemeController {

    private ModelMapper modelMapper;


    @Autowired
    private ThemeService themeService;

    public ThemeController() {
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("")
    public ResponseEntity<List<ThemeDTO>> getAllThemes() {
        List<Theme> themes = themeService.getAllThemes();

        List<ThemeDTO> themeDtos = themes.stream()
                .map(theme -> modelMapper.map(theme, ThemeDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(themeDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThemeDTO> getThemeById(@PathVariable String id) {
        Optional<Theme> optionalTheme = themeService.getThemeById(id);
        Theme theme = optionalTheme.orElseThrow(() -> new NotFoundException("Theme not found"));


        return ResponseEntity.ok().body(modelMapper.map(theme, ThemeDTO.class));
    }

    @PostMapping()
    public ResponseEntity<ThemeDTO> createTheme(@Valid @RequestBody CreateThemeDTO themeDto) {
        log.info(themeDto);

        Theme themeRequest = modelMapper.map(themeDto, Theme.class);
        Theme theme = themeService.createTheme(themeRequest);

        ThemeDTO responseBody = modelMapper.map(theme, ThemeDTO.class);
        return ResponseEntity.ok().body(responseBody);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThemeDTO> updateTheme(@PathVariable String id, @Valid @RequestBody ThemeDTO themeDto) {
        Theme themeRequest = modelMapper.map(themeDto, Theme.class);
        Theme updatedTheme = themeService.updateTheme(id, themeRequest);

        if (updatedTheme == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(modelMapper.map(updatedTheme, ThemeDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTheme(@PathVariable String id) {
        boolean isDeleted = themeService.deleteTheme(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

}
