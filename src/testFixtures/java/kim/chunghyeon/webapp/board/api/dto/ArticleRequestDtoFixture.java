package kim.chunghyeon.webapp.board.api.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.test.util.ReflectionTestUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleRequestDtoFixture {

    public static ArticleRequestDto create() {
        ArticleRequestDto req = new ArticleRequestDto();
        ReflectionTestUtils.setField(req, "subject", "subject");
        ReflectionTestUtils.setField(req, "content", "content");

        return req;
    }
}
