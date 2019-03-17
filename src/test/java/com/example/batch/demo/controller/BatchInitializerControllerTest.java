package com.example.batch.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
public class BatchInitializerControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private BatchInitializerController target;

    @Before
    public void setUp(){

        mockMvc = MockMvcBuilders
                .standaloneSetup(target)
                .build();

    }

    @Test
    public void testBatchInit() throws Exception {

        JobRequest request = new JobRequest();
        request.setFilename("testfile");
        mockMvc.perform(post("/api/initbatch").contentType(MediaType.APPLICATION_JSON).content(getJsonString(request)))
                .andExpect(status().isOk());

    }

    private static String getJsonString(final Object obj) {

        try {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
