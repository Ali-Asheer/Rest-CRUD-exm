package com.example.Rest_CRUD_JPA;

import java.util.List;
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
        Channel channel=channelService.getChannelById(id);
        if (channel !=null){
            message.setChannel(channel);
            messageService.addMessage(message);
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Get a list of all messages by channel ID

    @GetMapping("{id}")
    public ResponseEntity<List<MessageContentDTO>> getMessagesByChannelId(@PathVariable Long id) {
        Channel channel = channelService.getChannelById(id);
        if (channel !=null){
            List<Message> messages = messageService.getMessagesByChannel(channel);
            List<MessageContentDTO> contentList = messages.stream().map(message -> new MessageContentDTO(
                            message.getId(), message.getName())).toList();
            return ResponseEntity.ok(contentList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
