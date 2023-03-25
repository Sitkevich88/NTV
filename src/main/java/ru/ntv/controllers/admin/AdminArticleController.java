package ru.ntv.controllers.admin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ntv.dto.request.admin.NewArticleRequest;
import ru.ntv.entity.Article;
import ru.ntv.service.ArticleService;

@RestController
@RequestMapping("admin/articles")
@Validated
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;


    @PostMapping
    ResponseEntity<Article> createArticle(@Valid @RequestBody NewArticleRequest newArticleRequest){
        final var article = articleService.createArticle(newArticleRequest);

        return ResponseEntity.ok(article);
    }

    @DeleteMapping(params = "id")
    ResponseEntity<?> deleteArticle( @RequestParam int id){
        articleService.delete(id);

        return ResponseEntity.ok("OK");
    }


    @PutMapping(params = "id")
    ResponseEntity<?> updateArticle(
            @RequestParam int id,
            @Valid @RequestBody NewArticleRequest req
    ){
        return articleService.update(id, req);
    }
}