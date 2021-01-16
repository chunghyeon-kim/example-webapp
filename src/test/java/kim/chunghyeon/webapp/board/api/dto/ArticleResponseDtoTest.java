package kim.chunghyeon.webapp.board.api.dto;

import kim.chunghyeon.webapp.board.domain.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.ZonedDateTime;

import static org.assertj.core.api.BDDAssertions.then;

class ArticleResponseDtoTest {

    @Test
    void from_validInput_validOutput() {
        // given
        Long id = 1L;
        String subject = "subject";
        String content = "content";
        ZonedDateTime now = ZonedDateTime.now();

        Article article = Article.builder().subject(subject).content(content).build();
        ReflectionTestUtils.setField(article, "id", id);
        ReflectionTestUtils.setField(article, "createdAt", now);
        ReflectionTestUtils.setField(article, "updatedAt", now);

        // when
        ArticleResponseDto response = ArticleResponseDto.from(article);

        // then
        then(response).isNotNull();
        then(response.getId()).isEqualTo(article.getId());
        then(response.getSubject()).isEqualTo(subject);
        then(response.getContent()).isEqualTo(content);
        then(response.getCreatedAt()).isEqualTo(article.getCreatedAt());
        then(response.getUpdatedAt()).isEqualTo(article.getUpdatedAt());
    }
}