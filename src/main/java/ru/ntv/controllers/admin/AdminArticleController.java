package ru.ntv.controllers.admin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ntv.dto.request.admin.NewArticleRequest;
import ru.ntv.dto.response.admin.NewArticleResponse;
import ru.ntv.service.ArticleService;

@RestController
@CrossOrigin("*")
@RequestMapping("admin")
@Validated
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("article")
    ResponseEntity<NewArticleResponse> createArticle(@Valid @RequestBody NewArticleRequest newArticleRequest){
        articleService.createArticle(newArticleRequest);

        return ResponseEntity.ok(new NewArticleResponse("", "OK"));
    }

}
