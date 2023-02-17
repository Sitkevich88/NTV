package ru.smart_transportation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.smart_transportation.entity.Theme;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByArticleThemesRequest implements Serializable {
    private List<Theme> themes;
}
