/*
 * JsonParseExceptionMapper.java
 *
 * Copyright by shopbee-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.user.exception.common;

import com.fasterxml.jackson.core.JsonParseException;
import com.shopbee.user.exception.ErrorMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {

    @Override
    public Response toResponse(JsonParseException e) {
        String message = "Could not parse request body to object due to invalid JSON";
        String details = e.getMessage();
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessage(message, details)).build();
    }
}
