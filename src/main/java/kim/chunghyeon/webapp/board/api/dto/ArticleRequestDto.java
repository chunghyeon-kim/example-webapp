package kim.chunghyeon.webapp.board.api.dto;

import kim.chunghyeon.webapp.board.domain.model.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleRequestDto {
    private String subject;
    private String content;

    public Article toEntity() {
        return Article.builder()
                .subject(subject)
                .content(content)
                .build();
    }
}
