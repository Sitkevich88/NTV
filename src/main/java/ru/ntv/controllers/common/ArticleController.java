package ru.ntv.controllers.common;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ntv.dto.request.common.GetByArticleHeaderRequest;
import ru.ntv.dto.request.common.GetByArticleIdRequest;
import ru.ntv.dto.request.common.GetByArticleThemesRequest;
import ru.ntv.dto.response.common.ArticleListResponse;
import ru.ntv.dto.response.common.ArticleResponse;
import ru.ntv.dto.response.common.ArticlesResponse;
import ru.ntv.entity.Article;
import ru.ntv.service.ArticleService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("common")
@Validated
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("article/byArticleHeader")
    ResponseEntity<ArticleResponse> articleByArticleHeader(@Valid @RequestBody GetByArticleHeaderRequest req){
        final var res = new ArticleResponse();

        Optional<Article> optionalArticle = articleService.findByHeader(req.getHeader());
        optionalArticle.ifPresent(res::setArticle);

        return ResponseEntity.ok(res);
    }

    @GetMapping("article/byArticleThemes")
    ResponseEntity<ArticleListResponse> articlesByArticleThemes( @RequestBody GetByArticleThemesRequest req){
        List<Article> optionalArticleList = articleService.getArticlesByThemes(req.getThemes());

        return ResponseEntity.ok(new ArticleListResponse(optionalArticleList));
    }

    @GetMapping("article/byId")
    ResponseEntity<ArticleResponse> articleByArticleId(@RequestBody GetByArticleIdRequest req){
        final var res = new ArticleResponse();

        Optional<Article> optionalArticle = articleService.findById(req.getId());
        optionalArticle.ifPresent(res::setArticle);

        return ResponseEntity.ok(res);
    }

    @GetMapping("articles")
    ResponseEntity<ArticlesResponse> getAllArticles(){
        final var articlesResponse = articleService.getAll();

        return ResponseEntity.ok(articlesResponse);
    }
}
