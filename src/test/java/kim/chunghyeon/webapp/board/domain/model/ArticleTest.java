package kim.chunghyeon.webapp.board.domain.model;

import org.junit.jupiter.api.Test;

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

}