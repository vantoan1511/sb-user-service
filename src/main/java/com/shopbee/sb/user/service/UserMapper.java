/*
 * UserMapper.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service;

import com.shopbee.sb.user.service.spec.v1.dto.CreateUserRequest;
import com.shopbee.sb.user.service.spec.v1.dto.PatchUserByIdRequest;
import com.shopbee.sb.user.service.spec.v1.dto.UpdateUserByIdRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import static org.mapstruct.MappingConstants.ComponentModel.CDI;

@Mapper(
    componentModel = CDI,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    uses = GenderMapper.class
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toUser(CreateUserRequest createUserRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    void patchUser(PatchUserByIdRequest patchUserByIdRequest, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    void updateUser(UpdateUserByIdRequest updateUserByIdRequest, @MappingTarget User user);
}
