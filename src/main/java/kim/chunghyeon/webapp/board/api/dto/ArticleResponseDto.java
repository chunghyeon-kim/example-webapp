package kim.chunghyeon.webapp.board.api.dto;

import kim.chunghyeon.webapp.board.domain.model.Article;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class ArticleResponseDto {
    private long id;
    private String subject;
    private String content;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @Builder(access = AccessLevel.PRIVATE)
    private ArticleResponseDto(
            long id,
            String subject,
            String content,
            ZonedDateTime createdAt,
            ZonedDateTime updatedAt
    ) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ArticleResponseDto from(Article article) {
        return ArticleResponseDto.builder()
                .id(article.getId())
                .subject(article.getSubject())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }
}
