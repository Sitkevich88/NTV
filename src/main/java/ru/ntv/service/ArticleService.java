package ru.ntv.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ntv.dto.request.admin.NewArticleRequest;
import ru.ntv.dto.response.common.ArticlesResponse;
import ru.ntv.entity.Article;
import ru.ntv.entity.Theme;
import ru.ntv.repo.ArticleRepository;
import ru.ntv.repo.ThemeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ThemeRepository themeRepository;

    public Optional<Article> findByHeader(String header){
        return articleRepository.findByHeader(header);
    }

    public List<Article> getArticlesByThemes(List<Theme> themes) {
        Long count = (long) themes.size();
        return articleRepository.findByThemesContainingAll(themes, count);
    }
    public Optional<Article> findById(int id){
        return articleRepository.findById(id);
    }

    public void createArticle(NewArticleRequest newArticleRequest) {
        articleRepository.save(
                convertNewArticleRequestToArticle(newArticleRequest)
        );
    }

    public ArticlesResponse getAll(){
        final var res = new ArticlesResponse();
        res.setArticles(articleRepository.findAll());

        return res;
    }

    private Article convertNewArticleRequestToArticle(NewArticleRequest newArticleRequest){
        final var article = new Article();
        final var themes = themeRepository.findAllById(newArticleRequest.getThemeIds());

        article.setThemes(themes);
        article.setHeader(newArticleRequest.getHeader());
        article.setSubheader(newArticleRequest.getSubheader());
        article.setText(newArticleRequest.getText());
        article.setPriority(newArticleRequest.getPriority());
        article.setPhoto(newArticleRequest.getPhotoURL());

        return article;
    }
}
