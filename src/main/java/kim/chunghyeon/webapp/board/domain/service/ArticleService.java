package kim.chunghyeon.webapp.board.domain.service;

import kim.chunghyeon.webapp.board.api.dto.ArticleRequestDto;
import kim.chunghyeon.webapp.board.api.dto.ArticleResponseDto;
import kim.chunghyeon.webapp.board.domain.exception.ArticleNotFoundException;
import kim.chunghyeon.webapp.board.domain.model.Article;
import kim.chunghyeon.webapp.board.domain.model.ArticleRepository;
import kim.chunghyeon.webapp.common.api.dto.CreationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public CreationResponseDto createArticle(ArticleRequestDto req) {
        return new CreationResponseDto(articleRepository.save(req.toEntity()).getId());
    }

    public void updateArticle(long id, ArticleRequestDto req) {
        Article article = getArticleEntity(id);

        article.update(req.toEntity());

        articleRepository.save(article);
    }

    public ArticleResponseDto getArticle(long id) {
        return ArticleResponseDto.from(getArticleEntity(id));
    }

    public Page<ArticleResponseDto> getArticlePage(Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    public void deleteArticle(long id) {
        throw new UnsupportedOperationException();
    }

    private Article getArticleEntity(long id) {
        return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
    }
}
