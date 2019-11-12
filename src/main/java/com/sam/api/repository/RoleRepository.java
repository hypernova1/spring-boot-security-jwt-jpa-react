package com.sam.api.repository;

import com.sam.api.model.Role;
import com.sam.api.model.RoleName;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository {

    Optional<Role> findByName(RoleName roleName);
}
