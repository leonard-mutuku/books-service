package com.abc;

import com.abc.model.Book;
import com.abc.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Base64;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookManagerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository br;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${spring.security.username}")
    private String username;
    @Value("${spring.security.password}")
    private String password;

    @BeforeEach
    public void setUp() {
        br.deleteAll();
    }

    @Test
    @WithMockUser(username = "${spring.security.username}", password = "{spring.security.password}", roles = "${spring.security.roles}")
    void testCreateMyEntity() throws Exception {
        Book book = new Book(null, "title", "author", 2025);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)));

        result.andExpect(status().isCreated());

        assertThat(br.count()).isEqualTo(1);
        assertThat(br.findById(1L)).isNotNull();
    }

    @Test
    @WithMockUser(username = "${spring.security.username}", password = "{spring.security.password}", roles = "${spring.security.roles}")
    void testValidateCreateMyEntity() throws Exception {
        Book book = new Book(null, "", "author", 2025);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "${spring.security.username}", password = "{spring.security.password}", roles = "${spring.security.roles}")
    public void testNotFoundEntity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/23"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSecuredEndpointWithoutAuthentication() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testSecuredEndpointWithBasicAuthHeader() throws Exception {
        String encodedCredentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedCredentials))
                .andExpect(status().isOk());
    }

}
