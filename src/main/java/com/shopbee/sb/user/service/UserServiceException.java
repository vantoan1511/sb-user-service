/*
 * UserServiceException.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service;

public final class UserServiceException extends RuntimeException {

    private final Type type;

    /**
     * Prevent creation of a new User service exception instance.
     *
     * @param type    the type
     * @param message the message
     */
    private UserServiceException(Type type, String message) {
        super(message);
        this.type = type;
    }

    /**
     * Create user service exception.
     *
     * @param type    the type
     * @param message the message
     * @return the user service exception
     */
    public static UserServiceException create(Type type, String message) {
        return new UserServiceException(type, message);
    }

    /**
     * Create user service exception.
     *
     * @param message the message
     * @return the user service exception
     */
    public static UserServiceException create(String message) {
        return new UserServiceException(Type.INTERNAL_ERROR, message);
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    public enum Type {
        NOT_FOUND,
        INTERNAL_ERROR
    }
}
