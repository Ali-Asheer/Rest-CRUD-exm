package com.example.Rest_CRUD_JPA.controller;

import com.example.Rest_CRUD_JPA.model.Channel;
import com.example.Rest_CRUD_JPA.service.ChannelService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @PostMapping
    public ResponseEntity<Channel> create(@RequestBody Channel channel) {
        Channel created = channelService.createChannel(channel);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Channel> getById(@PathVariable Long id) {
        Channel channel = channelService.getChannelById(id);
        return channel != null ? ResponseEntity.ok(channel) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Channel>> getAll() {
        return ResponseEntity.ok(channelService.getAllChannels());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return channelService.deleteChannel(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}