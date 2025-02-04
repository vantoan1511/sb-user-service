/*
 * UsersRepository.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsersRepository implements PanacheRepositoryBase<User, String> {

    /**
     * Existed by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean existedById(String id) {
        return find("id", id).count() > 0;
    }

    /**
     * Existed by username boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean existedByUsername(String username) {
        return find("username", username).count() > 0;
    }

    /**
     * Existed by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean existedByEmail(String email) {
        return find("email", email).count() > 0;
    }

}
