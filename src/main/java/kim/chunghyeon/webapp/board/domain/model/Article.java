package kim.chunghyeon.webapp.board.domain.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Getter
@Entity
public class Article {
    @Id
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
