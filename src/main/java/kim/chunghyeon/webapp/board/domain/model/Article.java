package kim.chunghyeon.webapp.board.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class Article {
    private Long id;
    private String subject;
    private String content;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @Builder
    public Article(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }
}
