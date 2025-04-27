package com.example.Rest_CRUD_JPA.service;

import com.example.Rest_CRUD_JPA.model.Channel;
import com.example.Rest_CRUD_JPA.repository.ChannelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ChannelServiceTest {

    private ChannelRepository mockRepository;
    private ChannelService channelService;

    @BeforeEach
    void setUp() {
        mockRepository = mock(ChannelRepository.class);
        channelService = new ChannelService(mockRepository);
    }

    @Test
    @DisplayName("Should save a new channel successfully")
    void testCreateChannel_ShouldReturnSavedChannel() {
        // Arrange
        Channel channel = new Channel();
        channel.setName("TestChannel");

        when(mockRepository.save(channel)).thenReturn(channel);

        // Act
        Channel saved = channelService.createChannel(channel);

        // Assert
        assertNotNull(saved);
        assertEquals("TestChannel", saved.getName());
        verify(mockRepository, times(1)).save(channel);
    }

    @Test
    @DisplayName("Should return channel by ID when it exists")
    void testGetChannelById_ShouldReturnChannelIfExists() {
        // Arrange
        Long channelId = 1L;
        Channel channel = new Channel();
        channel.setId(channelId);
        channel.setName("News");

        when(mockRepository.findById(channelId)).thenReturn(Optional.of(channel));

        // Act
        Channel result = channelService.getChannelById(channelId);

        // Assert
        assertNotNull(result);
        assertEquals("News", result.getName());
        verify(mockRepository).findById(channelId);
    }
}
