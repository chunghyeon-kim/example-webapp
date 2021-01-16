package kim.chunghyeon.webapp.board.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kim.chunghyeon.webapp.board.api.dto.ArticleRequestDto;
import kim.chunghyeon.webapp.board.api.dto.ArticleRequestDtoFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

@SpringBootTest
@Sql("/data/test-article-data.sql")
@Transactional
@AutoConfigureMockMvc
class ArticleControllerIntTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create_validInput_validOutput() throws Exception {
        ArticleRequestDto req = ArticleRequestDtoFixture.create();

        mockMvc.perform(MockMvcRequestBuilders.post("/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber());
    }

    @Test
    void get_nonExistentArticle_notFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/articles/{id}", 3))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Not Found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("article #3 has not been found."));
    }
}
