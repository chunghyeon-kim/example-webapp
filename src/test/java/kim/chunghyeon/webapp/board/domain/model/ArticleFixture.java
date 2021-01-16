package kim.chunghyeon.webapp.board.domain.model;

import kim.chunghyeon.webapp.board.api.dto.ArticleRequestDtoFixture;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.ZonedDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleFixture {

    public static Article create() {
        return create(1L);
    }

    public static Article create(long id) {
        Article article = Article.builder().subject("subject").content("content").build();

        ReflectionTestUtils.setField(article, "id", id);
        ReflectionTestUtils.setField(article, "createdAt", ZonedDateTime.now());
        ReflectionTestUtils.setField(article, "updatedAt", ZonedDateTime.now());

        return article;
    }
}
