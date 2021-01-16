package kim.chunghyeon.webapp.board.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kim.chunghyeon.webapp.board.api.dto.ArticleRequestDto;
import kim.chunghyeon.webapp.board.api.dto.ArticleRequestDtoFixture;
import kim.chunghyeon.webapp.board.domain.exception.ArticleNotFoundException;
import kim.chunghyeon.webapp.board.domain.service.ArticleService;
import kim.chunghyeon.webapp.common.api.dto.CreationResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleController.class)
@AutoConfigureRestDocs
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ArticleService articleService;

    @Test
    void create_validInput_validOutput() throws Exception {
        ArticleRequestDto req = ArticleRequestDtoFixture.create();
        CreationResponseDto res = new CreationResponseDto(1L);

        given(articleService.createArticle(argThat(dto -> {
            assertThat(dto.getSubject()).isEqualTo(req.getSubject());
            assertThat(dto.getContent()).isEqualTo(req.getContent());
            return true;
        }))).willReturn(res);

        mockMvc.perform(post("/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(res.getId()))
                .andDo(document("articles/create",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("subject").description("아티클 제목"),
                                fieldWithPath("content").description("아티클 내용")
                        ),
                        responseFields(
                                fieldWithPath("id").description("생성한 아티클 ID")
                        )
                ));
    }

    @Test
    void get_nonExistentArticle_notFound() throws Exception {
        given(articleService.getArticle(anyLong())).willThrow(new ArticleNotFoundException(1L));

        mockMvc.perform(get("/articles/{id}", 1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp").isString())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("article #1 has not been found."));
    }
}
