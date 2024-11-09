package com.noob.moviesapi.repositories;

import com.noob.moviesapi.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepo extends JpaRepository<Actor, Long> {
}
