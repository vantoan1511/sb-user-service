/*
 * AddressMapper.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service.mapper;

import com.shopbee.sb.user.service.model.Address;
import com.shopbee.sb.user.service.spec.v1.dto.UserAddress;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {

    /**
     * To address.
     *
     * @param address the address
     * @return the address
     */
    Address toAddress(UserAddress address);

}
