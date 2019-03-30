package com.payment.seffaf.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * enbiya on 24.03.2019
 */
@Entity
@Table(name = "t_address")
public class Address extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "address_id")
    private UUID addressId;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "address_title")
    private String addressTitle;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "detail")
    private String detail;

    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressTitle() {
        return addressTitle;
    }

    public void setAddressTitle(String addressTitle) {
        this.addressTitle = addressTitle;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
