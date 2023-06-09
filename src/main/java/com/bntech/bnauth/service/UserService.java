package com.bntech.bnauth.service;

import com.bntech.bnauth.data.model.Role;
import com.bntech.bnauth.data.model.User;
import com.bntech.bnauth.data.object.AuthRequest;
import com.bntech.bnauth.data.object.AuthResponse;
import com.bntech.bnauth.data.object.UserDto;

import java.util.Set;

public interface UserService {
    /**
     * Update user full name
     *
     * @param fullName new full name
     * @param id       user id to add new full name
     * @return updated user
     */
    User updateUserFullName(String fullName, Long id);

    /**
     * Get user by id
     *
     * @param id user id
     * @return user
     */
    User getById(Long id);

    /**
     * Save user
     *
     * @param user user to save
     * @return User if saved
     */
    User save(User user);

    /**
     * Save user coming from a request
     *
     * @param user  user to save
     * @param roles roles user should have
     * @return User if saved and assigned roles
     */
    User save(UserDto user, Set<Role> roles);

    /**
     * Authenticate incoming user and password combination against database
     *
     * @param authenticationRequest User and password
     * @return AuthenticationResponse oauth2 token
     */
    AuthResponse authenticate(final AuthRequest authenticationRequest);
}
