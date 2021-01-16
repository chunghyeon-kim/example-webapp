package kim.chunghyeon.webapp.board.domain.service;

import kim.chunghyeon.webapp.board.api.dto.ArticleRequestDto;
import kim.chunghyeon.webapp.board.domain.exception.ArticleNotFoundException;
import kim.chunghyeon.webapp.board.domain.model.Article;
import kim.chunghyeon.webapp.board.domain.model.ArticleRepository;
import kim.chunghyeon.webapp.common.api.dto.CreationResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public CreationResponseDto createArticle(ArticleRequestDto req) {
        return new CreationResponseDto(articleRepository.save(req.toEntity()).getId());
    }

    public void updateArticle(long id, ArticleRequestDto req) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));

        article.update(req.toEntity());

        articleRepository.save(article);
    }
}
