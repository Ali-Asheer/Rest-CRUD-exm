package com.example.Rest_CRUD_JPA.repository;


import com.example.Rest_CRUD_JPA.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel,Long> {
}
