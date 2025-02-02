/*
 * ErrorMessage.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service;

import java.io.Serializable;

public class ErrorMessage implements Serializable {

    private final String details;

    private ErrorMessage(String details) {
        this.details = details;
    }

    public static ErrorMessage create(String details) {
        return new ErrorMessage(details);
    }

    public String getDetails() {
        return details;
    }

}
