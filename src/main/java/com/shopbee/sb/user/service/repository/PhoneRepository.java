/*
 * PhoneRepository.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service.repository;

import com.shopbee.sb.user.service.model.Phone;
import com.shopbee.sb.user.service.model.PhoneId;
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
