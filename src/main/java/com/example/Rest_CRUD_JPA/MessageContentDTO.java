package com.example.Rest_CRUD_JPA;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageContentDTO {
    @JsonProperty("Message ID")
    private Long id;

    @JsonProperty("Message content")
    private String name;

    public MessageContentDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
