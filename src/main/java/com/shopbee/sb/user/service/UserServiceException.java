/*
 * UserServiceException.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service;

public class UserServiceException extends RuntimeException {

    private final Type type;

    private UserServiceException(Type type, String message) {
        super(message);
        this.type = type;
    }

    public static UserServiceException create(Type type, String message) {
        return new UserServiceException(type, message);
    }

    public static UserServiceException create(String message) {
        return new UserServiceException(Type.INTERNAL_ERROR, message);
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        NOT_FOUND,
        INTERNAL_ERROR
    }
}
