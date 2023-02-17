package ru.smart_transportation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.smart_transportation.entity.Article;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListResponse implements Serializable {
    private List<Article> articles;
}
