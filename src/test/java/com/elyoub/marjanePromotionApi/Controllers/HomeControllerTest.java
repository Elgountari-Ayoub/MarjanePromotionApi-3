package com.elyoub.marjanePromotionApi.Controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@ExtendWith(SpringExtension.class)
@WebMvcTest(HomeController.class)
class HomeControllerTest {
    @Autowired
    private MockMvc mvc;
    @Test
    void sayHello() throws Exception {
        RequestBuilder request = get("/");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("Hello, Ayoub!", result.getResponse().getContentAsString());
    }
    @Test
    public void testHelloWithName() throws Exception {
        mvc.perform(get("/?name=Azzden"))
                .andExpect(content().string("Hello, Azzden!"));
    }
}