package ru.ntv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ntv.dto.response.common.ThemesResponse;
import ru.ntv.repo.ThemeRepository;

@Service
public class ThemesService {
    @Autowired
    private ThemeRepository themeRepository;

    public ThemesResponse getAllThemes(){
        final var response = new ThemesResponse();
        response.setThemes(themeRepository.findAll());

        return response;
    }
}
