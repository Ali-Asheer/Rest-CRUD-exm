package com.example.Rest_CRUD_JPA.service;

import com.example.Rest_CRUD_JPA.model.Channel;
import com.example.Rest_CRUD_JPA.repository.ChannelRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public Channel createChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public Channel getChannelById(Long id) {
        return channelRepository.findById(id).orElse(null);
    }

    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    public boolean deleteChannel(Long id) {
        if (channelRepository.existsById(id)) {
            channelRepository.deleteById(id);
            return true;
        }
        return false;
    }
}