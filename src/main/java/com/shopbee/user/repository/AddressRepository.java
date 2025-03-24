/*
 * UsersRepository.java
 *
 * Copyright by  shopbee-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.user.repository;

import com.shopbee.user.entity.Address;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddressRepository implements PanacheRepositoryBase<Address, String> {
}
