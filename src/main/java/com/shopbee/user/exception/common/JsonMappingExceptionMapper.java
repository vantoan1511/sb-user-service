package com.shopbee.user.exception.common;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.shopbee.user.exception.ErrorMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {

    @Override
    public Response toResponse(JsonMappingException e) {
        String message = "Could not map request body to object due to invalid JSON";
        String details = e.getMessage();
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessage(message, details)).build();
    }
}
