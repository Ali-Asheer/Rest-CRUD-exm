package com.example.Rest_CRUD_JPA;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/messages/")
@RestController
public class MessageController {
    MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Get a list of all messages

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }


    // Delete message by ID

    @DeleteMapping("{id}")
    public ResponseEntity<Message> deleteMessage(@PathVariable Long id) {
        boolean isRemoved = messageService.deleteMessageById(id);
        if (isRemoved) {

            return ResponseEntity.noContent().build();
        } else {

            return ResponseEntity.notFound().build();
        }
    }


    // Update a message by ID

    @PutMapping("{id}")
    public ResponseEntity<Message> updateMessage(
            @PathVariable Long id, @RequestBody Message updatedMessage) {
        Message message = messageService.getMessageById(id);

        if (message == null) {
           return ResponseEntity.notFound().build();
        }
        message.setName(updatedMessage.getName());
        Message savedMessage = messageService.saveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }
}
