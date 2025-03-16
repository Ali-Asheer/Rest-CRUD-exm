package com.example.Rest_CRUD_JPA;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Message ID")
    private Long id;

    @NotBlank(message = "name cannot be blank")
    @Size(min = 2, max = 120, message = "name must be between 2 and 120 characters long")
    @JsonProperty("Message content")
    private String name;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    public Message() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name= name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

}
