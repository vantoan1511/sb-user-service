/*
 * User.java
 *
 * Copyright by sb-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.sb.user.service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "sb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column
    private String phone;

    /**
     * Username user.
     *
     * @param username the username
     * @return the user
     */
    public User username(String username) {
        this.username = username;
        return this;
    }

    /**
     * Email user.
     *
     * @param email the email
     * @return the user
     */
    public User email(String email) {
        this.email = email;
        return this;
    }

    /**
     * First name user.
     *
     * @param firstName the first name
     * @return the user
     */
    public User firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Last name user.
     *
     * @param lastName the last name
     * @return the user
     */
    public User lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Gender user.
     *
     * @param gender the gender
     * @return the user
     */
    public User gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    /**
     * Birthdate user.
     *
     * @param birthDate the birthdate
     * @return the user
     */
    public User birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Phone user.
     *
     * @param phone the phone
     * @return the user
     */
    public User phone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Gets birthdate.
     *
     * @return the birthdate
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Sets birthdate.
     *
     * @param birthDate the birthdate
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public enum Gender {
        MALE, FEMALE, OTHER
    }
}
