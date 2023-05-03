package com.bntech.qrekru.service;

import com.bntech.qrekru.data.model.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleService {
    /**
     * Used to update roles that user might have and are not yet in the database
     *
     * @param roles roles of user
     * @return new roles
     */
    Set<Role> upsertRoles(Set<Role> roles);
}
