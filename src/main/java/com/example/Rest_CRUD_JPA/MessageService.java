package com.example.Rest_CRUD_JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @PersistenceContext
    private EntityManager entityManager;

    MessageRepository repo;

    public MessageService(MessageRepository repo) {
        this.repo = repo;
    }


    // Add new message

    public Message addMessage(Message message) {
       // entityManager.clear();
        return repo.save(message);
    }


    // Get a list of all messages

    public List<Message> getAllMessages() {
        return repo.findAll();
    }


    // Get all messages by channel

    public List<Message> getMessagesByChannel(Channel channel) {
        return repo.findByChannel(channel);
    }


    // Get all messages by ID

    public Message getMessageById(Long id) {
        return repo.findById(id).orElse(null);
    }


    // Save messages

    public Message saveMessage(Message message) {
        entityManager.clear();
        return repo.save(message);
    }


    // Delete message by ID

    public boolean deleteMessageById(Long id) {

        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

}
