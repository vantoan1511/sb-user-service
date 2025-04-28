/*
 * LoggingFilter.java
 *
 * Copyright by shopbee-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.user.logging;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.MDC;

@Provider
public class LoggingFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String tenantId = CollectionUtils.emptyIfNull(containerRequestContext.getHeaders().get("tenantId")).stream().findFirst().orElse(null);
        MDC.put("logInformation", tenantId);
    }
}
