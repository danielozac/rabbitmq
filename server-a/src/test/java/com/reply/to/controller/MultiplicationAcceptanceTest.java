package com.reply.to.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MultiplicationAcceptanceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Ignore for now as additional setup to run with docker is required.
     * @throws Exception
     */
    @Ignore
    @Test
    public void acceptanceTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/multiplication?noOne=4&noTwo=4",
                String.class)).contains("Total is: 16");
    }
}
