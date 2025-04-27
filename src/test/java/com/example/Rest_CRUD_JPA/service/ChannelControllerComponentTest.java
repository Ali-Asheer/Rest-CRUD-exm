package com.example.Rest_CRUD_JPA.service;

import com.example.Rest_CRUD_JPA.controller.ChannelController;
import com.example.Rest_CRUD_JPA.model.Channel;
import com.example.Rest_CRUD_JPA.repository.ChannelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ChannelControllerComponentTest {

    private MockMvc mockMvc;
    private ChannelRepository mockRepository;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockRepository = mock(ChannelRepository.class);
        ChannelService service = new ChannelService(mockRepository);
        ChannelController controller = new ChannelController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Should return 201 CREATED when creating a new channel")
    void testCreateChannel_Returns201AndBody() throws Exception {
        Channel input = new Channel(null, "Testkanal");
        Channel saved = new Channel(1L, "Testkanal");

        when(mockRepository.save(any(Channel.class))).thenReturn(saved);

        mockMvc.perform(post("/channels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Testkanal"));
    }

    @Test
    @DisplayName("Should return 200 OK with channel details when channel exists")
    void testGetChannelById_Returns200() throws Exception {
        Channel found = new Channel(2L, "Nyheter");

        when(mockRepository.findById(2L)).thenReturn(Optional.of(found));

        mockMvc.perform(get("/channels/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("Nyheter"));
    }

    @Test
    @DisplayName("Should return 404 NOT FOUND when channel does not exist")
    void testGetChannelById_Returns404IfNotFound() throws Exception {
        when(mockRepository.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/channels/99"))
                .andExpect(status().isNotFound());
    }
}


