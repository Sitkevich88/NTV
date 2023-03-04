package ru.ntv.controllers.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ntv.dto.response.common.ThemesResponse;
import ru.ntv.service.ThemesService;

@RestController
@CrossOrigin("*")
@RequestMapping("theme")
@Validated
public class ThemesController {

    @Autowired
    private ThemesService themesService;

    @GetMapping
    ResponseEntity<ThemesResponse> getThemes(){
        final var response = themesService.getAllThemes();

        return ResponseEntity.ok(response);
    }

}
