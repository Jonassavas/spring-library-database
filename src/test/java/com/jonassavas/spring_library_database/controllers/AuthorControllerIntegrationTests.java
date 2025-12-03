package com.jonassavas.spring_library_database.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonassavas.spring_library_database.TestDataUtil;
import com.jonassavas.spring_library_database.domain.dto.AuthorDto;
import com.jonassavas.spring_library_database.domain.entities.AuthorEntity;
import com.jonassavas.spring_library_database.services.AuthorService;

import lombok.experimental.ExtensionMethod;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {
    
    private AuthorService authorService;
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, AuthorService authorService){
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.authorService = authorService;
    }

    @Test
    public void testThatCreateAuthorReturnsHttp201Create() throws Exception{
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorEntityA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);


        mockMvc.perform(
            MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJson)
        ).andExpect(
            MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorReturnsSavedAuthor() throws Exception{
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorEntityA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);


        mockMvc.perform(
            MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJson)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.name").value("Abigail Rose")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.age").value(80)
        );
    }

    @Test
    public void testThatListAuthorsReturnsHttpStatus200() throws Exception{
        mockMvc.perform(
            MockMvcRequestBuilders.get("/authors")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListAuthorsReturnsListOfAuthors() throws Exception{
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorEntityA();
        authorService.save(testAuthorEntityA);

        mockMvc.perform(
            MockMvcRequestBuilders.get("/authors")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].name").value("Abigail Rose")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].age").value(80)
        );
    }

    @Test
    public void testThatGetAuthorReturnsHttpStatus200WhenAuthorExists() throws Exception{
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorEntityA();
        authorService.save(testAuthorEntityA);

        mockMvc.perform(
            MockMvcRequestBuilders.get("/authors/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorReturnsHttpStatus404WhenNoAuthorExists() throws Exception{
        mockMvc.perform(
            MockMvcRequestBuilders.get("/authors/99")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetAuthorReturnsAuthorWhenAuthorExists() throws Exception{
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorEntityA();
        authorService.save(testAuthorEntityA);

        mockMvc.perform(
            MockMvcRequestBuilders.get("/authors/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.id").value(1)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.name").value("Abigail Rose")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.age").value(80)
        );
    }

    @Test
    public void testThatFullUpdateAuthorReturnsHttpStatus404WhenNoAuthorExists() throws Exception{
        AuthorDto testAuthorEntityA = TestDataUtil.createTestAuthorDtoA();
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorEntityA);

        mockMvc.perform(
            MockMvcRequestBuilders.put("/authors/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorDtoJson)
        ).andExpect(
            MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatFullUpdateAuthorReturnsHttpStatus200WhenAuthorExists() throws Exception{
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorEntityA();
        AuthorEntity savedAuthor = authorService.save(testAuthorEntityA);
        
        AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);


        mockMvc.perform(
            MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorDtoJson)
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFullUpdateUpdatesExistingAuthor() throws Exception{
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorEntityA();
        AuthorEntity savedAuthor = authorService.save(testAuthorEntityA);

        AuthorEntity testAuthorEntityB = TestDataUtil.createTestAuthorEntityB();
        testAuthorEntityB.setId(savedAuthor.getId());
        String authorDtoUpdateJson = objectMapper.writeValueAsString(testAuthorEntityB);

        mockMvc.perform(
            MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorDtoUpdateJson)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.name").value(testAuthorEntityB.getName())
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.age").value(testAuthorEntityB.getAge())
        );
    }
}
