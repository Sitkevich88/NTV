package ru.ntv.controllers.admin;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ntv.dto.request.admin.CreateThemeRequest;
import ru.ntv.dto.request.admin.DeleteThemeRequest;
import ru.ntv.service.ThemesService;

@RestController
@CrossOrigin("*")
@RequestMapping("admin")
@Validated
public class AdminThemeController {

    @Autowired
    private ThemesService themesService;


    @PostMapping("theme")
    ResponseEntity<?> create(@Valid @RequestBody CreateThemeRequest req){
        themesService.create(req);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("theme")
    ResponseEntity<?> delete(@Valid @RequestBody DeleteThemeRequest req){
        themesService.delete(req);
        return ResponseEntity.ok("OK");
    }
}
