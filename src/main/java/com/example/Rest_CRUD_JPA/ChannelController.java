package com.example.Rest_CRUD_JPA;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channels/")
public class ChannelController {
    private ChannelService channelService;
    private MessageService messageService;

    public ChannelController(
            ChannelService channelService, MessageService messageService) {
        this.channelService = channelService;
        this.messageService = messageService;
    }

    // Get a list of all channels

    @GetMapping
    public ResponseEntity<List<Channel>> getAllChannels() {
        return ResponseEntity.ok(channelService.getAllChannels());
    }


    // Create a new channel

    @PostMapping
    public ResponseEntity<Channel> createChannel(@RequestBody Channel channel) {
        return ResponseEntity.ok(channelService.createChannel(channel));
    }


    // Delete a channel by channel Id

    @DeleteMapping("{id}")
    public void deleteChannelById(@PathVariable Long id) {
        channelService.deleteChannel(id);
    }


    //  Create a new message in a channel

    @PutMapping("{id}")
    public ResponseEntity<Message> addMessageToChannel(@PathVariable Long id, @RequestBody Message message) {
        return channelService.getChannelById(id)
                .map(channel -> {
                    message.setChannel(channel);
                    Message savedMessage = messageService.addMessage(message);
                    return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // Get a list of all messages

    @GetMapping("{id}")
    public ResponseEntity<List<MessageContentDTO>> getMessagesByChannelId(@PathVariable Long id) {
        Channel channel = channelService.getChannelById(id).orElseThrow(() -> new ChannelNotFoundException("Channel not found for ID " + id));
        List<Message> messages = messageService.getMessagesByChannel(channel);
        List<MessageContentDTO> contentList = messages.stream().map(message -> new MessageContentDTO(
                                message.getId(), message.getName()))
                        .collect(Collectors.toList());
        return ResponseEntity.ok(contentList);
    }
}
