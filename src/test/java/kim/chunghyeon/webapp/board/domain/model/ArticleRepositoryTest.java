package kim.chunghyeon.webapp.board.domain.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void save_validInput_validOutput() {
        // given
        Article article = ArticleFixture.create();
        ReflectionTestUtils.setField(article, "id", null);

        // when
        Article savedArticle = articleRepository.save(article);

        // then
        then(article.getId()).isNotNull();
        then(article.getCreatedAt()).isNotNull();
        then(article.getUpdatedAt()).isNotNull();
        then(article == savedArticle).isTrue();
    }

    @Test
    @Sql("/data/test-article-data.sql")
    void findAllByIdDesc_validInput_validOutput() {
        List<Article> articles = articleRepository.findAllByOrderByIdDesc();

        then(articles).extracting("id").containsExactly(2L, 1L);
    }

}