package com.reply.to.controller;


import com.reply.to.model.NumberToMultiply;
import com.reply.to.service.Multiplication;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(MultiplicationController.class)
public class MultiplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Multiplication multiplication;

    @Test
    public void getTotal_Test() throws Exception {
        NumberToMultiply numbersToMultiply = new NumberToMultiply(1,2);
        when(multiplication.calculate(12345,numbersToMultiply)).thenReturn(0);
        this.mockMvc.perform(get("/multiplication").param("noOne","1")
                .param("noTwo","2"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Total is: 0")));
    }


}
