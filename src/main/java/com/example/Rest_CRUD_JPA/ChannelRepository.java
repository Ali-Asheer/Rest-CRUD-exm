package com.example.Rest_CRUD_JPA;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel,Long> {
}
