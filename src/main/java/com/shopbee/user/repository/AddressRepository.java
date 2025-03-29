/*
 * UsersRepository.java
 *
 * Copyright by shopbee-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.user.repository;

import com.shopbee.user.entity.Address;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class AddressRepository implements PanacheRepositoryBase<Address, String> {

    public List<Address> findByUserId(String userId, int offset, int limit) {
        return find("user.id", userId).page(offset, limit).list();
    }

    public Address findByIdAndUserId(String id, String userId) {
        return find("id = ?1 and user.id = ?2", id, userId).firstResult();
    }

}
