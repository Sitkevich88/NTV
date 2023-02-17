package ru.smart_transportation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.smart_transportation.entity.Article;
import ru.smart_transportation.entity.Role;
import ru.smart_transportation.entity.Theme;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Optional<Article> findByHeader(String header);

    @Query("SELECT a FROM Article a JOIN a.themes t WHERE t IN ?1 GROUP BY a HAVING COUNT(t) = ?2")
    List<Article> findByThemesContainingAll(List<Theme> themes, Long count);
    Optional<Article> findById(int id);

}
