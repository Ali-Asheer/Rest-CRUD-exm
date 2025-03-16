package com.example.Rest_CRUD_JPA;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Channel ID")
    private Long id;

    @JsonProperty("Channel Name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    private List<Message> messages;

    public Channel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
