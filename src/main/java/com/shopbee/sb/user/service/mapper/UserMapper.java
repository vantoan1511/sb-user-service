/*
 * UserMapper.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service.mapper;

import com.shopbee.sb.user.service.model.User;
import com.shopbee.sb.user.service.spec.v1.dto.CreateUserRequest;
import com.shopbee.sb.user.service.spec.v1.dto.PatchUserByIdRequest;
import com.shopbee.sb.user.service.spec.v1.dto.UpdateUserByIdRequest;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.CDI,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    uses = {GenderMapper.class, AddressMapper.class, PhoneMapper.class}
)
public interface UserMapper {

    /**
     * Map CreateUserRequest to User.
     *
     * @param createUserRequest the create user request
     * @return the user
     */
    @Mapping(target = "id", ignore = true)
    User toUser(CreateUserRequest createUserRequest);

    /**
     * To user.
     *
     * @param user the user
     * @return the user
     */
    @Mapping(target = "phone.countryCode", source = "phone.id.countryCode")
    @Mapping(target = "phone.number", source = "phone.id.number")
    com.shopbee.sb.user.service.spec.v1.dto.User toUser(User user);

    /**
     * Patch user.
     *
     * @param patchUserByIdRequest the patch user by id request
     * @param user                 the user
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    void patchUser(PatchUserByIdRequest patchUserByIdRequest, @MappingTarget User user);

    /**
     * Update user.
     *
     * @param updateUserByIdRequest the update user by id request
     * @param user                  the user
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    void updateUser(UpdateUserByIdRequest updateUserByIdRequest, @MappingTarget User user);

    /**
     * To users list.
     *
     * @param users the users
     * @return the list
     */
    List<com.shopbee.sb.user.service.spec.v1.dto.User> toUsers(List<User> users);
}
