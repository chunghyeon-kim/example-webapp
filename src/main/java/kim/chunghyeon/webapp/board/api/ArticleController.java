package kim.chunghyeon.webapp.board.api;

import kim.chunghyeon.webapp.board.api.dto.ArticleRequestDto;
import kim.chunghyeon.webapp.board.api.dto.ArticleResponseDto;
import kim.chunghyeon.webapp.board.domain.service.ArticleService;
import kim.chunghyeon.webapp.common.api.dto.CreationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public CreationResponseDto createArticle(@RequestBody @Validated ArticleRequestDto req) {
        return articleService.createArticle(req);
    }

    @PutMapping(value = "/{id}")
    public void updateArticle(
            @PathVariable long id,
            @Validated @RequestBody ArticleRequestDto req
    ) {
        articleService.updateArticle(id, req);
    }

    @GetMapping(value = "/{id}")
    public ArticleResponseDto articleResponseDto(@PathVariable long id) {
        return articleService.getArticle(id);
    }
}
