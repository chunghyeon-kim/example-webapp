package kim.chunghyeon.webapp.board.domain.model;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

class ArticleTest {

    @Test
    void build_validInput_validOutput() {
        String subject = "subject";
        String content = "content";

        Article article = Article.builder().subject(subject).content(content).build();

        then(article).hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt");
        then(article.getSubject()).isEqualTo(subject);
        then(article.getContent()).isEqualTo(content);
    }

    @Test
    void update_validInput_validOutput() {
        // given
        Article a1 = ArticleFixture.create();
        Article a2 = ArticleFixture.create();

        ReflectionTestUtils.setField(a2, "subject", "new subject");
        ReflectionTestUtils.setField(a2, "content", "new content");

        assertThat(a1.getSubject()).isNotEqualTo(a2.getSubject());
        assertThat(a1.getContent()).isNotEqualTo(a2.getContent());

        // when
        a1.update(a2);

        // then
        then(a1.getSubject()).isEqualTo(a2.getSubject());
        then(a1.getContent()).isEqualTo(a2.getContent());

        then(a1.getSubject()).isEqualTo("new subject");
        then(a1.getContent()).isEqualTo("new content");
    }
}