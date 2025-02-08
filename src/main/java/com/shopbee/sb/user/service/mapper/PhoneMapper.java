/*
 * PhoneMapper.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service.mapper;

import com.shopbee.sb.user.service.model.Phone;
import com.shopbee.sb.user.service.spec.v1.dto.UserPhone;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhoneMapper {

    /**
     * To phone.
     *
     * @param phone the phone
     * @return the phone
     */
    Phone toPhone(UserPhone phone);

}
