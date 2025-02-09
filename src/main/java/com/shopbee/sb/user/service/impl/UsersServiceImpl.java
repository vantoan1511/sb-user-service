/*
 * UsersServiceImpl.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service.impl;

import com.shopbee.sb.user.service.UsersService;
import com.shopbee.sb.user.service.exception.UserServiceException;
import com.shopbee.sb.user.service.mapper.PhoneMapper;
import com.shopbee.sb.user.service.mapper.UserMapper;
import com.shopbee.sb.user.service.model.Gender;
import com.shopbee.sb.user.service.model.Phone;
import com.shopbee.sb.user.service.model.PhoneId;
import com.shopbee.sb.user.service.model.User;
import com.shopbee.sb.user.service.model.UserStatus;
import com.shopbee.sb.user.service.repository.PhoneRepository;
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
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.EnumUtils;

@ApplicationScoped
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PhoneRepository phoneRepository;
    private final UserMapper userMapper;
    private final PhoneMapper phoneMapper;

    /**
     * Instantiates a new Users service.
     *
     * @param usersRepository the users repository
     * @param phoneRepository the phone repository
     * @param userMapper      the user mapper
     * @param phoneMapper     the phone mapper
     */
    @Inject
    public UsersServiceImpl(UsersRepository usersRepository,
                            PhoneRepository phoneRepository,
                            UserMapper userMapper,
                            PhoneMapper phoneMapper) {
        this.usersRepository = usersRepository;
        this.phoneRepository = phoneRepository;
        this.userMapper = userMapper;
        this.phoneMapper = phoneMapper;
    }

    @Override
    @Transactional
    public CreateUser201Response createUser(CreateUserRequest createUserRequest) {
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
    public void deleteUserById(String userId) {
        if (!usersRepository.existedById(userId)) {
            throw createUserNotFoundException(userId);
        }

        usersRepository.deleteById(userId);
    }

    @Override
    public User getUserById(String userId) {
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
    public void patchUserById(String userId, PatchUserByIdRequest patchUserByIdRequest) {
        validateEmailChangeRequest(patchUserByIdRequest.getEmail(), userId);

        User existingUser = getUserById(userId);

        userMapper.patchUser(patchUserByIdRequest, existingUser);
    }

    @Override
    @Transactional
    public void updateUserById(String userId, UpdateUserByIdRequest updateUserByIdRequest) {
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
        validateGender(createUserRequest);
        validateStatus(createUserRequest);
        validateUsername(createUserRequest);
        validateEmail(createUserRequest);
        validatePhone(createUserRequest);
    }

    /**
     * Validate gender.
     *
     * @param createUserRequest the create user request
     */
    private static void validateGender(CreateUserRequest createUserRequest) {
        String requestGender = createUserRequest.getGender();
        Gender gender = EnumUtils.getEnum(Gender.class, requestGender);
        if (Objects.isNull(gender)) {
            throw UserServiceException.createBadRequest(String.format("Invalid gender request [%s].", requestGender));
        }
    }

    /**
     * Validate status.
     *
     * @param createUserRequest the create user request
     */
    private void validateStatus(CreateUserRequest createUserRequest) {
        String requestStatus = createUserRequest.getStatus();
        UserStatus status = EnumUtils.getEnum(UserStatus.class, requestStatus);
        if (Objects.isNull(status)) {
            throw UserServiceException.createBadRequest(String.format("Invalid user status request [%s].", requestStatus));
        }
    }

    /**
     * Validate phone.
     *
     * @param createUserRequest the create user request
     */
    private void validatePhone(CreateUserRequest createUserRequest) {
        Phone phone = phoneMapper.toPhone(createUserRequest.getPhone());
        if (Objects.isNull(phone)) {
            return;
        }

        PhoneId phoneId = phone.getId();
        if (phoneRepository.existedById(phoneId)) {
            throw UserServiceException.createConflict(String.format("Phone [%s%s] linked to another user.", phoneId.getCountryCode(), phoneId.getNumber()));
        }
    }

    /**
     * Validate email.
     *
     * @param createUserRequest the create user request
     */
    private void validateEmail(CreateUserRequest createUserRequest) {
        String email = createUserRequest.getEmail();
        if (usersRepository.existedByEmail(email)) {
            throw createEmailExistedException(email);
        }
    }

    /**
     * Validate username.
     *
     * @param createUserRequest the create user request
     */
    private void validateUsername(CreateUserRequest createUserRequest) {
        String username = createUserRequest.getUsername();
        if (usersRepository.existedByUsername(username)) {
            throw UserServiceException.createConflict(String.format("Username [%s] existed.", username));
        }
    }

    /**
     * Validate get paginated users request.
     *
     * @param offset the offset
     * @param limit  the limit
     */
    private void validateGetPaginatedUsersRequest(Integer offset, Integer limit) {
        if (Objects.isNull(offset) || offset < 0) {
            throw UserServiceException.createBadRequest("Offset must be greater than or equal 0.");
        }

        if (Objects.isNull(limit) || limit < 1) {
            throw UserServiceException.createBadRequest("Limit must be greater than 0.");
        }
    }

    /**
     * Create user not found exception user service exception.
     *
     * @param userId the user id
     * @return the user service exception
     */
    private UserServiceException createUserNotFoundException(String userId) {
        return UserServiceException.createNotFound(String.format("Cannot find user with id [%s].", userId));
    }

    /**
     * Create email existed exception user service exception.
     *
     * @param email the email
     * @return the user service exception
     */
    private UserServiceException createEmailExistedException(String email) {
        return UserServiceException.createConflict(String.format("Email [%s] existed.", email));
    }

}
