/*
 * UsersRepository.java
 *
 * Copyright by  shopbee-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.user.repository;

import com.shopbee.user.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsersRepository implements PanacheRepositoryBase<User, String> {

    /**
     * Existed by email excluded by id boolean.
     *
     * @param email the email
     * @param id    the id
     * @return the boolean
     */
    public boolean existedByEmailExcludedById(String email, String id) {
        return count("email = ?1 AND id != ?2", email, id) > 0;
    }

    /**
     * Existed by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean existedById(String id) {
        return count("id", id) > 0;
    }

    /**
     * Existed by username boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean existedByUsername(String username) {
        return count("username", username) > 0;
    }

    /**
     * Existed by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean existedByEmail(String email) {
        return count("email", email) > 0;
    }

}
