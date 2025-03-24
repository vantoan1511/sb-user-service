/*
 * PhoneRepository.java
 *
 * Copyright by shopbee-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.user.repository;

import com.shopbee.user.entity.Phone;
import com.shopbee.user.entity.PhoneId;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PhoneRepository implements PanacheRepositoryBase<Phone, PhoneId> {

    /**
     * Existed by id boolean.
     *
     * @param phoneId the phone id
     * @return the boolean
     */
    public boolean existedById(PhoneId phoneId) {
        return findByIdOptional(phoneId).isPresent();
    }

}
