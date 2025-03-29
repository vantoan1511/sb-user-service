/*
 * Address.java
 *
 * Copyright by shopbee-user-service, all rights reserved.
 * MIT License: https://mit-license.org
 */

package com.shopbee.user.entity;

import com.shopbee.user.exception.UserServiceException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Address.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopbee_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column
    private String street;

    @Column
    private String ward;

    @Column
    private String district;

    @Column
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "is_default")
    private boolean asDefault;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    /**
     * The enum Type.
     */
    public enum Type {
        HOME, WORK, OTHER;

        /**
         * From value type.
         *
         * @param value the value
         * @return the type
         */
        public static Type fromValue(String value) {
            return Arrays.stream(values())
                    .filter(type -> type.name().equalsIgnoreCase(value))
                    .findFirst()
                    .orElseThrow(() -> UserServiceException.badRequest("Invalid address type: " + value));
        }
    }
}
