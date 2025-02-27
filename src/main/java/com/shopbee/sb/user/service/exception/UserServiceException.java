/*
 * UserServiceException.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public final class UserServiceException extends WebApplicationException {

    /**
     * Prevent creation of a new User service exception instance.
     *
     * @param message the message
     */
    private UserServiceException(String message) {
        super(message);
    }

    /**
     * Prevent creation of a new User service exception instance.
     *
     * @param status  the status
     * @param message the message
     */
    private UserServiceException(Response.Status status, String message) {
        super(message, status);
    }

    /**
     * Create user service exception.
     *
     * @param message the message
     * @return the user service exception
     */
    public static UserServiceException create(String message) {
        return new UserServiceException(message);
    }

    /**
     * Create user service exception.
     *
     * @param status  the status
     * @param message the message
     * @return the user service exception
     */
    public static UserServiceException create(Response.Status status, String message) {
        return new UserServiceException(status, message);
    }

    /**
     * Create bad request user service exception.
     *
     * @param message the message
     * @return the user service exception
     */
    public static UserServiceException createBadRequest(String message) {
        return create(Response.Status.BAD_REQUEST, message);
    }

    /**
     * Create not found user service exception.
     *
     * @param message the message
     * @return the user service exception
     */
    public static UserServiceException createNotFound(String message) {
        return create(Response.Status.NOT_FOUND, message);
    }

    /**
     * Create conflict user service exception.
     *
     * @param message the message
     * @return the user service exception
     */
    public static UserServiceException createConflict(String message) {
        return create(Response.Status.CONFLICT, message);
    }
}
