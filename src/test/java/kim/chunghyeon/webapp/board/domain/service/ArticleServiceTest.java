package kim.chunghyeon.webapp.board.domain.service;

import kim.chunghyeon.webapp.board.api.dto.ArticleRequestDto;
import kim.chunghyeon.webapp.board.api.dto.ArticleRequestDtoFixture;
import kim.chunghyeon.webapp.board.domain.model.Article;
import kim.chunghyeon.webapp.board.domain.model.ArticleFixture;
import kim.chunghyeon.webapp.board.domain.model.ArticleRepository;
import kim.chunghyeon.webapp.common.api.dto.CreationResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    private ArticleService articleService;

    @BeforeEach
    void setUp() {
        articleService = new ArticleService(articleRepository);
    }

    @Test
    void createArticle_validInput_validOutput() {
        long id = 1L;

        ArticleRequestDto req = ArticleRequestDtoFixture.create();

        Article article = ArticleFixture.create();
        ReflectionTestUtils.setField(article, "id", id);
        given(articleRepository.save(
                argThat(a -> a.getSubject().equals(req.getSubject()) && a.getContent().equals(req.getContent())))
        ).willReturn(article);

        CreationResponseDto res = articleService.createArticle(req);

        then(res).isNotNull();
        then(res.getId()).isEqualTo(id);
    }
}