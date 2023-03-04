package ru.ntv.controllers.admin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ntv.dto.request.admin.NewArticleRequest;
import ru.ntv.dto.request.admin.UpdateRequest;
import ru.ntv.dto.request.common.GetByArticleIdRequest;
import ru.ntv.entity.Theme;
import ru.ntv.exception.ArticleNotFoundException;
import ru.ntv.dto.response.admin.NewArticleResponse;
import ru.ntv.service.ArticleService;

import java.util.List;

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

    @DeleteMapping("article")
    ResponseEntity<?> delete(@Valid @RequestBody GetByArticleIdRequest req){
        articleService.delete(req);

        return ResponseEntity.ok("OK");
    }


    @PutMapping("article")
    ResponseEntity<?> updateArticle(@Valid @RequestBody UpdateRequest req) throws ArticleNotFoundException {
        List<Theme> list = articleService.update(req);

        return ResponseEntity.ok(list);
    }



}
