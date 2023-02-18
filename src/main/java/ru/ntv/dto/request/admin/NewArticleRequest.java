package ru.ntv.dto.request.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewArticleRequest {
    private List<Integer> themeIds;
    private String header;
    private String subheader;
    private String text;
    private Integer priority;
    private String photoURL;
}
