/*
 * UserServiceException.java
 *
 * Copyright by  shopbee-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.user.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public final class UserServiceException extends WebApplicationException {


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
     * @param status  the status
     * @param message the message
     * @return the user service exception
     */
    public static UserServiceException create(Response.Status status, String message) {
        return new UserServiceException(status, message);
    }

    /**
     * Create user service exception.
     *
     * @param message the message
     * @return the user service exception
     */
    public static UserServiceException error(String message) {
        return new UserServiceException(Response.Status.INTERNAL_SERVER_ERROR, message);
    }

    /**
     * Create bad request user service exception.
     *
     * @param message the message
     * @return the user service exception
     */
    public static UserServiceException badRequest(String message) {
        return create(Response.Status.BAD_REQUEST, message);
    }

    /**
     * Create not found user service exception.
     *
     * @param message the message
     * @return the user service exception
     */
    public static UserServiceException notFound(String message) {
        return create(Response.Status.NOT_FOUND, message);
    }

    /**
     * Create conflict user service exception.
     *
     * @param message the message
     * @return the user service exception
     */
    public static UserServiceException conflict(String message) {
        return create(Response.Status.CONFLICT, message);
    }

    public static UserServiceException notImplemented(String message) {
        return create(Response.Status.NOT_IMPLEMENTED, message);
    }
}
