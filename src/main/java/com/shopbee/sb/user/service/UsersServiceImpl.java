/*
 * UsersServiceImpl.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service;

import com.shopbee.sb.user.service.spec.v1.dto.CreateUserRequest;
import com.shopbee.sb.user.service.spec.v1.dto.PatchUserByIdRequest;
import com.shopbee.sb.user.service.spec.v1.dto.UpdateUserByIdRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Inject
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    @Transactional
    public String createUser(CreateUserRequest createUserRequest) throws UserServiceException {
        User user = UserMapper.INSTANCE.map(createUserRequest);
        usersRepository.persist(user);
        return user.getId();
    }

    @Override
    @Transactional
    public void deleteUserById(String userId) throws UserServiceException {
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
    @Transactional
    public void patchUserById(String userId, PatchUserByIdRequest patchUserByIdRequest) throws UserServiceException {

    }

    @Override
    @Transactional
    public void updateUserById(String userId, UpdateUserByIdRequest updateUserByIdRequest) throws UserServiceException {
        User user = getUserById(userId);
        user.setEmail(updateUserByIdRequest.getEmail()); //TODO: Validate new email is linked to another user or not
        user.setFirstName(updateUserByIdRequest.getFirstName());
        user.setLastName(updateUserByIdRequest.getLastName());
        user.setBirthDate(updateUserByIdRequest.getBirthDate());
        user.setPhone(updateUserByIdRequest.getPhone());
        user.setGender(getGender(updateUserByIdRequest));
    }

    private UserServiceException createUserNotFoundException(String userId) {
        String message = String.format("Cannot find user with id %s.", userId);
        return UserServiceException.create(UserServiceException.Type.NOT_FOUND, message);
    }

    private String getGender(UpdateUserByIdRequest updateUserByIdRequest) {
        return Optional.ofNullable(updateUserByIdRequest)
            .map(UpdateUserByIdRequest::getGender)
            .map(UpdateUserByIdRequest.GenderEnum::value)
            .orElse(null);
    }

}
