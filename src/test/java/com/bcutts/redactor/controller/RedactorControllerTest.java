package com.bcutts.redactor.controller;

import com.bcutts.redactor.config.ConfigProperties;
import com.bcutts.redactor.service.RedactorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RedactorController.class)
@AutoConfigureMockMvc
public class RedactorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RedactorService redactorService;

    @MockitoBean
    private ConfigProperties configProperties;

    @Test
    public void testShouldReturnRedactedString() throws Exception {
        String json = """
            {
              The yellow small cat jumped over the angry lazy dog.
            }
        """;

        when(redactorService.redact("Mock string")).thenReturn("Hello, Mock");
        this.mockMvc.perform(post("/redact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }
}
