/*
 * ErrorMessage.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service.exception;

import java.io.Serializable;

public final class ErrorMessage implements Serializable {

    private final String details;

    /**
     * Prevent creation of a new Error message instance.
     *
     * @param details the details
     */
    private ErrorMessage(String details) {
        this.details = details;
    }

    /**
     * Create error message.
     *
     * @param details the details
     * @return the error message
     */
    public static ErrorMessage create(String details) {
        return new ErrorMessage(details);
    }

    /**
     * Gets details.
     *
     * @return the details
     */
    public String getDetails() {
        return details;
    }

}
