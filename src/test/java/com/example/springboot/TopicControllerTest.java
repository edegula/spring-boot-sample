package com.example.springboot;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class TopicControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getTopic() throws Exception {
        // post a topic
        mvc.perform(MockMvcRequestBuilders.post("/topics")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"java\",\"name\":\"Core Java\",\"description\":\"Core Java Description\"}"));
        // get a topic
        mvc.perform(MockMvcRequestBuilders.get("/topics/java").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":\"java\",\"name\":\"Core Java\",\"description\":\"Core Java Description\"}")));
    }

    @Test
    public void postTopic() throws Exception {
        // post one topic
        mvc.perform(MockMvcRequestBuilders.post("/topics")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"java\",\"name\":\"Core Java\",\"description\":\"Core Java Description\"}"))
                .andExpect(status().isCreated());
    }

}
