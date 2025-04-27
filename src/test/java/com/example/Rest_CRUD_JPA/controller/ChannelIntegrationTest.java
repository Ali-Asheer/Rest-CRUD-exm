package com.example.Rest_CRUD_JPA.controller;


import com.example.Rest_CRUD_JPA.model.Channel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChannelIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateAndGetChannelIdByHttp(){
        Channel channel = new Channel(null, "Radio");

        ResponseEntity<Channel> postResponse =
                restTemplate.postForEntity("http://localhost:"+port+"/channels",channel, Channel.class);

        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());

        Long channelId = postResponse.getBody().getId();

        ResponseEntity<Channel> getResponse =
                restTemplate.getForEntity("http://localhost:"+port+"/channels/"+ channelId, Channel.class);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Radio", getResponse.getBody().getName());

    }
}