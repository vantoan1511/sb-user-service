/*
 * ErrorMessage.java
 *
 * Copyright by shopbee-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.user.exception;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an error message with optional details.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage implements Serializable {

    private String message;
    private Object details;

    /**
     * Constructs an ErrorMessage with the specified message.
     *
     * @param message the error message
     */
    public ErrorMessage(String message) {
        this.message = message;
    }
}
