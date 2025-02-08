/*
 * UsersServiceImpl.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service.impl;

import com.shopbee.sb.user.service.UsersService;
import com.shopbee.sb.user.service.exception.UserServiceException;
import com.shopbee.sb.user.service.mapper.UserMapper;
import com.shopbee.sb.user.service.model.Gender;
import com.shopbee.sb.user.service.model.Phone;
import com.shopbee.sb.user.service.model.User;
import com.shopbee.sb.user.service.repository.UsersRepository;
import com.shopbee.sb.user.service.spec.v1.dto.CreateUser201Response;
import com.shopbee.sb.user.service.spec.v1.dto.CreateUserRequest;
import com.shopbee.sb.user.service.spec.v1.dto.GetUsers200Response;
import com.shopbee.sb.user.service.spec.v1.dto.PatchUserByIdRequest;
import com.shopbee.sb.user.service.spec.v1.dto.UpdateUserByIdRequest;
import com.shopbee.sb.user.service.utils.PaginatedUsersResponseBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.EnumUtils;

@ApplicationScoped
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    /**
     * Instantiates a new Users service.
     *
     * @param usersRepository the users repository
     * @param userMapper      the user mapper
     */
    @Inject
    public UsersServiceImpl(UsersRepository usersRepository,
                            UserMapper userMapper) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public CreateUser201Response createUser(CreateUserRequest createUserRequest) throws UserServiceException {
        validateCreateUserRequest(createUserRequest);

        User user = userMapper.toUser(createUserRequest);
        Phone phone = user.getPhone();
        if (Objects.nonNull(phone)) {
            phone.setUser(user);
        }

        usersRepository.persist(user);

        return new CreateUser201Response().id(user.getId());
    }

    @Override
    @Transactional
    public void deleteUserById(String userId) throws UserServiceException {
        if (!usersRepository.existedById(userId)) {
            throw createUserNotFoundException(userId);
        }

        usersRepository.deleteById(userId);
    }

    @Override
    public User getUserById(String userId) throws UserServiceException {
        return usersRepository.findByIdOptional(userId).orElseThrow(() -> createUserNotFoundException(userId));
    }

    @Override
    public List<User> getUsers(Integer offset, Integer limit) {
        return usersRepository.findAll().page(offset, limit).list();
    }

    @Override
    public GetUsers200Response getPaginatedUsers(Integer offset, Integer limit) {
        validateGetPaginatedUsersRequest(offset, limit);

        int totalUsers = Math.toIntExact(usersRepository.count());
        List<User> paginatedUsers = usersRepository.findAll().page(offset, limit).list();
        
        return PaginatedUsersResponseBuilder.builder()
            .offset(offset)
            .limit(limit)
            .totalItems(totalUsers)
            .items(userMapper.toUsers(paginatedUsers))
            .build();
    }

    @Override
    @Transactional
    public void patchUserById(String userId, PatchUserByIdRequest patchUserByIdRequest) throws UserServiceException {
        validateEmailChangeRequest(patchUserByIdRequest.getEmail(), userId);

        User existingUser = getUserById(userId);

        userMapper.patchUser(patchUserByIdRequest, existingUser);
    }

    @Override
    @Transactional
    public void updateUserById(String userId, UpdateUserByIdRequest updateUserByIdRequest) throws UserServiceException {
        validateEmailChangeRequest(updateUserByIdRequest.getEmail(), userId);

        User existingUser = getUserById(userId);

        userMapper.updateUser(updateUserByIdRequest, existingUser);
    }

    /**
     * Validate email change request.
     *
     * @param newEmail the new email
     * @param userId   the user id
     */
    private void validateEmailChangeRequest(String newEmail, String userId) {
        if (usersRepository.existedByEmailExcludedById(newEmail, userId)) {
            throw createEmailExistedException(newEmail);
        }
    }

    /**
     * Validate create user request.
     *
     * @param createUserRequest the create user request
     */
    private void validateCreateUserRequest(CreateUserRequest createUserRequest) {
        if (usersRepository.existedByUsername(createUserRequest.getUsername())) {
            throw UserServiceException.create(Response.Status.CONFLICT, String.format("Username [%s] existed.", createUserRequest.getUsername()));
        }

        if (usersRepository.existedByEmail(createUserRequest.getEmail())) {
            throw createEmailExistedException(createUserRequest.getEmail());
        }

        Gender gender = EnumUtils.getEnum(Gender.class, createUserRequest.getGender());
        if (Objects.isNull(gender)) {
            throw UserServiceException.create(Response.Status.BAD_REQUEST, String.format("Invalid gender request [%s].", createUserRequest.getGender()));
        }
    }

    /**
     * Validate get paginated users request.
     *
     * @param offset the offset
     * @param limit  the limit
     */
    private void validateGetPaginatedUsersRequest(Integer offset, Integer limit) {
        if (offset < 0) {
            throw UserServiceException.create(Response.Status.BAD_REQUEST, "Offset must be greater than or equal 0.");
        }

        if (limit < 1) {
            throw UserServiceException.create(Response.Status.BAD_REQUEST, "Limit must be greater than 0.");
        }
    }

    /**
     * Create user not found exception user service exception.
     *
     * @param userId the user id
     * @return the user service exception
     */
    private UserServiceException createUserNotFoundException(String userId) {
        String message = String.format("Cannot find user with id [%s].", userId);
        return UserServiceException.create(Response.Status.NOT_FOUND, message);
    }

    /**
     * Create email existed exception user service exception.
     *
     * @param email the email
     * @return the user service exception
     */
    private UserServiceException createEmailExistedException(String email) {
        return UserServiceException.create(Response.Status.CONFLICT, String.format("Email [%s] existed.", email));
    }

}
