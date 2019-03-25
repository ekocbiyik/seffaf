package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.Address;
import com.payment.seffaf.model.Customer;
import com.payment.seffaf.repositories.dao.IAddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 25.03.2019
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private IAddressDao addressDao;

    @Transactional
    @Override
    public void save(Address address) {
        addressDao.save(address);
    }

    @Transactional
    @Override
    public void delete(Address address) {
        addressDao.delete(address);
    }

    @Transactional
    @Override
    public Address getAddressById(UUID id) {
        return addressDao.findAddressByAddressId(id);
    }

    @Transactional
    @Override
    public List<Address> getAllByCustomer(Customer customer) {
        return addressDao.findAllByCustomer(customer);
    }
}
