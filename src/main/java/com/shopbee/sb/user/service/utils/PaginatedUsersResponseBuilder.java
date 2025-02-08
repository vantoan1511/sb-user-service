/*
 * PaginatedUsersResponseBuilder.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service.utils;

import com.shopbee.sb.user.service.spec.v1.dto.GetUsers200Response;
import com.shopbee.sb.user.service.spec.v1.dto.User;
import java.util.List;

public class PaginatedUsersResponseBuilder {

    private int totalItems;

    private int offset;

    private int limit;

    private List<User> users;

    /**
     * Prevent creation of a new Paginated users response builder instance.
     */
    private PaginatedUsersResponseBuilder() {
    }

    /**
     * Builder paginated users response builder.
     *
     * @return the paginated users response builder
     */
    public static PaginatedUsersResponseBuilder builder() {
        return new PaginatedUsersResponseBuilder();
    }

    /**
     * Offset paginated users response builder.
     *
     * @param offset the offset
     * @return the paginated users response builder
     */
    public PaginatedUsersResponseBuilder offset(int offset) {
        this.offset = offset;
        return this;
    }

    /**
     * Limit paginated users response builder.
     *
     * @param limit the limit
     * @return the paginated users response builder
     */
    public PaginatedUsersResponseBuilder limit(int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Total items paginated users response builder.
     *
     * @param totalItems the total items
     * @return the paginated users response builder
     */
    public PaginatedUsersResponseBuilder totalItems(int totalItems) {
        this.totalItems = totalItems;
        return this;
    }

    /**
     * Items paginated users response builder.
     *
     * @param users the users
     * @return the paginated users response builder
     */
    public PaginatedUsersResponseBuilder items(List<User> users) {
        this.users = users;
        return this;
    }

    /**
     * Build get users 200 response.
     *
     * @return the get users 200 response
     */
    public GetUsers200Response build() {
        return new GetUsers200Response()
            .totalItems(totalItems)
            .numberOfItems(users.size())
            .hasNext(hasNext())
            .offset(offset)
            .limit(limit)
            .items(users);
    }

    /**
     * Has next boolean.
     *
     * @return the boolean
     */
    private boolean hasNext() {
        return offset + 1 < Math.floor((float) totalItems / limit);
    }

}
