/*
 * UserServiceException.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public final class UserServiceException extends WebApplicationException {

    private UserServiceException(String message) {
        super(message);
    }

    private UserServiceException(Response.Status status, String message) {
        super(message, status);
    }

    public static UserServiceException create(String message) {
        return new UserServiceException(message);
    }

    public static UserServiceException create(Response.Status status, String message) {
        return new UserServiceException(status, message);
    }

}
