/*
 * GenderMapper.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service.mapper;

import com.shopbee.sb.user.service.model.User;
import org.apache.commons.lang3.EnumUtils;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GenderMapper {

    /**
     * Map a gender as string to gender as enum. Null if string gender does not match any enum.
     *
     * @param gender the gender as string
     * @return the enum gender or null
     */
    default User.Gender toGender(String gender) {
        return EnumUtils.getEnum(User.Gender.class, gender);
    }
}
