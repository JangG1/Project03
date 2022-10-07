package com.FT.app.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FT.app.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
