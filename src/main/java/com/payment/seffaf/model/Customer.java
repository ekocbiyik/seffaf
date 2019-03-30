package com.payment.seffaf.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * enbiya on 24.03.2019
 */
@Entity
@Table(name = "t_customer")
public class Customer extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "customer_id", nullable = false, unique = true)
    private UUID customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private boolean isActive;

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
