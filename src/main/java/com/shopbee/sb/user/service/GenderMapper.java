/*
 * GenderMapper.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service;

import org.apache.commons.lang3.EnumUtils;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import static org.mapstruct.MappingConstants.ComponentModel.CDI;

@Mapper(componentModel = CDI, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GenderMapper {

    default User.Gender toGender(String gender) {
        return EnumUtils.getEnum(User.Gender.class, gender);
    }
}
