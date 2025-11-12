//package com.abc;
//
//import com.abc.model.Book;
//import com.abc.repository.BookRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import static org.assertj.core.api.Assertions.assertThat;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class BookManagerApplicationTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private TestEntityManager em;
//
//    @Autowired
//    private BookRepository br;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        br.deleteAll();
//    }
//
//    @Test
//    void testCreateMyEntity() throws Exception {
//        Book book = new Book(22L, "title", "author", 2025);
//
//        ResultActions result = mockMvc.perform(post("/books")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(book)));
//
//        result.andExpect(status().isCreated());
//
//        // Verify the entity was saved in the database
//        assertThat(br.count()).isEqualTo(1);
//        assertThat(br.findById(22L)).isNotNull();
//    }
//
//}
