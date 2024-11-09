package com.noob.moviesapi.repositories;

import com.noob.moviesapi.entities.auth.Authority;
import com.noob.moviesapi.entities.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByAuthority(Authority authority); // findByAuthority(String name)>
}
