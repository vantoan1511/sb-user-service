/*
 * UsersServiceImpl.java
 *
 * Copyright by shopbee-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.user.service.impl;

import com.shopbee.user.exception.UserServiceException;
import com.shopbee.user.mapper.AddressMapper;
import com.shopbee.user.mapper.PhoneMapper;
import com.shopbee.user.mapper.UserMapper;
import com.shopbee.user.repository.AddressRepository;
import com.shopbee.user.repository.PhoneRepository;
import com.shopbee.user.repository.UsersRepository;
import com.shopbee.user.service.UsersService;
import com.shopbee.user.v1.dto.Address;
import com.shopbee.user.v1.dto.CreateUserAddressRequest;
import com.shopbee.user.v1.dto.CreateUserRequest;
import com.shopbee.user.v1.dto.PatchUserAddressRequest;
import com.shopbee.user.v1.dto.PatchUserByIdRequest;
import com.shopbee.user.v1.dto.UpdateUserByIdRequest;
import com.shopbee.user.v1.dto.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PhoneRepository phoneRepository;
    private final AddressRepository addressRepository;
    private final UserMapper userMapper;
    private final PhoneMapper phoneMapper;
    private final AddressMapper addressMapper;

    /**
     * Instantiates a new Users service.
     *
     * @param usersRepository   the users repository
     * @param phoneRepository   the phone repository
     * @param addressRepository the address repository
     * @param userMapper        the user mapper
     * @param phoneMapper       the phone mapper
     * @param addressMapper     the address mapper
     */
    @Inject
    public UsersServiceImpl(UsersRepository usersRepository,
                            PhoneRepository phoneRepository,
                            AddressRepository addressRepository,
                            UserMapper userMapper,
                            PhoneMapper phoneMapper,
                            AddressMapper addressMapper) {
        this.usersRepository = usersRepository;
        this.phoneRepository = phoneRepository;
        this.addressRepository = addressRepository;
        this.userMapper = userMapper;
        this.phoneMapper = phoneMapper;
        this.addressMapper = addressMapper;
    }

    /**
     * Retrieves a list of users with pagination.
     *
     * @param offset the starting point of the list
     * @param limit  the maximum number of users to return
     * @return a list of users
     */
    @Override
    public List<User> getUsers(Integer offset, Integer limit) {
        Integer pageIndex = Optional.ofNullable(offset).orElse(0);
        Integer pageSize = Optional.ofNullable(limit).orElse(20);

        List<com.shopbee.user.entity.User> users = usersRepository.findAll()
                .page(pageIndex, pageSize)
                .list();

        return userMapper.toUsers(users);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user
     * @return the user with the specified ID
     */
    @Override
    public User getUserById(String userId) {
        return usersRepository.findByIdOptional(userId)
                .map(userMapper::toUser)
                .orElseThrow(this::getUserNotFound);
    }

    /**
     * Creates a new user.
     *
     * @param createUserRequest the request object containing user details
     * @return the ID of the created user
     */
    @Override
    @Transactional
    public String createUser(CreateUserRequest createUserRequest) {
        validateCreateUserRequest(createUserRequest);

        com.shopbee.user.entity.User user = userMapper.toUser(createUserRequest);

        usersRepository.persist(user);

        return user.getId();
    }

    /**
     * Updates a user by their ID.
     *
     * @param userId                the ID of the user to update
     * @param updateUserByIdRequest the request object containing updated user details
     */
    @Override
    @Transactional
    public void updateUserById(String userId, UpdateUserByIdRequest updateUserByIdRequest) {
        validateUpdateEmail(userId, updateUserByIdRequest.getEmail());

        com.shopbee.user.entity.User user = usersRepository.findByIdOptional(userId)
                .orElseThrow(this::getUserNotFound);

        userMapper.updateUser(updateUserByIdRequest, user);
    }

    /**
     * Partially updates a user by their ID.
     *
     * @param userId               the ID of the user to update
     * @param patchUserByIdRequest the request object containing partial user details
     */
    @Override
    @Transactional
    public void patchUserById(String userId, PatchUserByIdRequest patchUserByIdRequest) {
        validateUpdateEmail(userId, patchUserByIdRequest.getEmail());

        com.shopbee.user.entity.User user = usersRepository.findByIdOptional(userId)
                .orElseThrow(this::getUserNotFound);

        userMapper.patchUser(patchUserByIdRequest, user);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete
     */
    @Override
    @Transactional
    public void deleteUserById(String userId) {
        usersRepository.deleteById(userId);
    }

    /**
     * Retrieves a list of addresses for a user with pagination.
     *
     * @param userId the ID of the user
     * @param offset the starting point of the list
     * @param limit  the maximum number of addresses to return
     * @return a list of addresses for the user
     */
    @Override
    public List<Address> getUserAddresses(String userId, Integer offset, Integer limit) {
        int pageIndex = Optional.ofNullable(offset).orElse(0);
        int pageSize = Optional.ofNullable(limit).orElse(20);
        return addressMapper.toAddresses(addressRepository.findByUserId(userId, pageIndex, pageSize));
    }

    /**
     * Creates a new address for a user.
     *
     * @param userId                   the ID of the user
     * @param createUserAddressRequest the request object containing address details
     * @return the ID of the created address
     */
    @Override
    @Transactional
    public String createUserAddress(String userId, CreateUserAddressRequest createUserAddressRequest) {
        com.shopbee.user.entity.User user = usersRepository.findByIdOptional(userId).orElseThrow(this::getUserNotFound);

        com.shopbee.user.entity.Address address = addressMapper.toAddress(createUserAddressRequest);

        addressRepository.persist(address);

        user.addAddress(address);

        return address.getId();
    }

    /**
     * Updates an address for a user.
     *
     * @param userId                   the ID of the user
     * @param addressId                the ID of the address to update
     * @param createUserAddressRequest the request object containing updated address details
     */
    @Override
    @Transactional
    public void updateUserAddress(String userId, String addressId, CreateUserAddressRequest createUserAddressRequest) {
        com.shopbee.user.entity.Address address = addressRepository.findByIdAndUserId(addressId, userId);

        if (Objects.isNull(address)) {
            throw UserServiceException.notFound("Address not found");
        }

        addressMapper.updateAddress(createUserAddressRequest, address);
    }

    /**
     * Partially updates an address for a user.
     *
     * @param userId                  the ID of the user
     * @param addressId               the ID of the address to update
     * @param patchUserAddressRequest the request object containing partial address details
     */
    @Override
    @Transactional
    public void patchUserAddress(String userId, String addressId, PatchUserAddressRequest patchUserAddressRequest) {
        com.shopbee.user.entity.Address address = addressRepository.findByIdAndUserId(addressId, userId);

        if (Objects.isNull(address)) {
            throw UserServiceException.notFound("Address not found");
        }

        addressMapper.patchAddress(patchUserAddressRequest, address);
    }

    /**
     * Deletes an address for a user.
     *
     * @param userId    the ID of the user
     * @param addressId the ID of the address to delete
     */
    @Override
    @Transactional
    public void deleteUserAddress(String userId, String addressId) {
        addressRepository.deleteById(addressId);
    }

    /**
     * Validates the create user request.
     *
     * @param createUserRequest the create user request
     */
    private void validateCreateUserRequest(CreateUserRequest createUserRequest) {
        validateUniqueEmail(createUserRequest);
        validateUniqueUsername(createUserRequest);
    }

    /**
     * Validates the uniqueness of the username.
     *
     * @param createUserRequest the create user request
     */
    private void validateUniqueUsername(CreateUserRequest createUserRequest) {
        if (usersRepository.existedByUsername(createUserRequest.getUsername())) {
            throw UserServiceException.conflict("User with username already exists");
        }
    }

    /**
     * Validates the uniqueness of the email.
     *
     * @param createUserRequest the create user request
     */
    private void validateUniqueEmail(CreateUserRequest createUserRequest) {
        if (usersRepository.existedByEmail(createUserRequest.getEmail())) {
            throw UserServiceException.conflict("User with email already exists");
        }
    }

    /**
     * Validates the email update.
     *
     * @param userId the ID of the user
     * @param email  the email to validate
     */
    private void validateUpdateEmail(String userId, String email) {
        if (usersRepository.existedByEmailExcludedById(email, userId)) {
            throw UserServiceException.conflict("User with email already exists");
        }
    }

    /**
     * Gets the user not found exception.
     *
     * @return the user not found exception
     */
    private UserServiceException getUserNotFound() {
        return UserServiceException.notFound("User not found");
    }
}
