package ru.ntv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ntv.dto.request.admin.CreateThemeRequest;
import ru.ntv.dto.request.admin.DeleteThemeRequest;
import ru.ntv.dto.response.common.ThemesResponse;
import ru.ntv.entity.Theme;
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

    public void create(CreateThemeRequest req){
        var theme = new Theme();
        theme.setThemeName(req.getName());
        themeRepository.save(theme);
    }

    public void delete(DeleteThemeRequest req){
        themeRepository.deleteById(req.getId());
    }
}
