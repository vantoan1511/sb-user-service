/*
 * GenderMapper.java
 *
 * Copyright by  shopbee-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.user.mapper;

import com.shopbee.user.entity.Gender;
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
    default Gender toGender(String gender) {
        return EnumUtils.getEnum(Gender.class, gender);
    }
}
