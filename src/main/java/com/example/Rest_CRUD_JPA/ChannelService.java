package com.example.Rest_CRUD_JPA;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {

    private ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }


    // Create Channel

    public Channel createChannel(Channel channel) {
        return channelRepository.save(channel);
    }


    // Get all Channels

    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }


    // Delete Channels

    public void deleteChannel(long id) {
        channelRepository.deleteById(id);
    }


    // Get Channel by Id

    public Channel getChannelById(Long id) {
        return channelRepository.findById(id).orElse(null);

    }
}