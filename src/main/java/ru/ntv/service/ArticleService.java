package ru.ntv.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ntv.entity.Article;
import ru.ntv.entity.Theme;
import ru.ntv.repo.ArticleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

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
}
