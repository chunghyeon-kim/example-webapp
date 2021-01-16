package kim.chunghyeon.webapp.board.api.dto;


import kim.chunghyeon.webapp.board.domain.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.BDDAssertions.then;

class ArticleRequestDtoTest {

    @Test
    void toEntity_validInput_validOutput() {
        // given
        String subject = "subject";
        String content = "content";

        ArticleRequestDto request = new ArticleRequestDto();
        ReflectionTestUtils.setField(request, "subject", subject);
        ReflectionTestUtils.setField(request, "content", content);

        // when
        Article article = request.toEntity();

        // then
        then(article.getSubject()).isEqualTo(subject);
        then(article.getContent()).isEqualTo(content);
    }

}