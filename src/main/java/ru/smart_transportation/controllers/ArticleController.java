package ru.smart_transportation.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.smart_transportation.dto.request.GetByArticleHeaderRequest;
import ru.smart_transportation.dto.request.GetByArticleIdRequest;
import ru.smart_transportation.dto.request.GetByArticleThemesRequest;
import ru.smart_transportation.dto.response.ArticleListResponse;
import ru.smart_transportation.dto.response.ArticleResponse;
import ru.smart_transportation.entity.Article;
import ru.smart_transportation.repo.ArticleRepository;
import ru.smart_transportation.service.ArticleService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("articleByArticleHeader")
    ResponseEntity<ArticleResponse> articleByArticleHeader(@RequestBody GetByArticleHeaderRequest req){
      Optional<Article> optionalArticle = articleService.findByHeader(req.getHeader());

      if (optionalArticle.isEmpty()){
        return ResponseEntity
                .badRequest()
                .body(null);
      }

      return ResponseEntity.ok(new ArticleResponse(optionalArticle.get()));
    }

    @GetMapping("articleByArticleThemes")
    ResponseEntity<ArticleListResponse> articlesByArticleThemes(@RequestBody GetByArticleThemesRequest req){
        List<Article> optionalArticleList = articleService.getArticlesByThemes(req.getThemes());
        return ResponseEntity.ok(new ArticleListResponse(optionalArticleList));
    }


    @GetMapping("articleByArticleIdHeader")
    ResponseEntity<ArticleResponse> articleByArticleId(@RequestBody GetByArticleIdRequest req){
        Optional<Article> optionalArticle = articleService.findById(req.getId());

        if (optionalArticle.isEmpty()){
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }

        return ResponseEntity.ok(new ArticleResponse(optionalArticle.get()));
    }

}
