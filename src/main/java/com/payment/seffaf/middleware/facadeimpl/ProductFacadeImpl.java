package com.payment.seffaf.middleware.facadeimpl;

import com.payment.seffaf.exceptions.*;
import com.payment.seffaf.middleware.facade.IProductFacade;
import com.payment.seffaf.model.Address;
import com.payment.seffaf.model.BankAccount;
import com.payment.seffaf.model.Customer;
import com.payment.seffaf.model.Product;
import com.payment.seffaf.repositories.service.IAddressService;
import com.payment.seffaf.repositories.service.IBankAccountService;
import com.payment.seffaf.repositories.service.ICustomerService;
import com.payment.seffaf.repositories.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.UUID;

/**
 * ekocbiyik on 4/13/19
 */
@Controller
public class ProductFacadeImpl implements IProductFacade {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private IProductService productService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private IBankAccountService bankAccountService;

    @Override
    public Product createProduct(Product product) throws SeffafException {

        Customer customerById = customerService.getCustomerById(product.getCustomerId());
        if (customerById == null) {
            throw new CustomerException(SeffafExceptionCode.CUSTOMER_NOT_FOUND, product.getCustomerId().toString());
        }

        Address addressById = addressService.getAddressById(product.getAddressId());
        if (addressById == null) {
            throw new AddressException(SeffafExceptionCode.ADDRESS_NOT_FOUND, product.getAddressId().toString());
        }

        // banka bilgileri sonra
//        BankAccount bankAccountById = bankAccountService.getBankAccountById(product.getAccountId());
//        if (bankAccountById == null) {
//            throw new BankAccountException(SeffafExceptionCode.BANK_ACCOUNT_NOT_FOUND, product.getAccountId().toString());
//        }

        productService.save(product);
        logger.info("product created! {}", product.getProductId());

        return product;
    }

    @Override
    public Product getProduct(UUID productId) throws SeffafException {
        Product product = productService.getByProductId(productId);
        if (product == null) {
            throw new ProductException(SeffafExceptionCode.PRODUCT_NOT_FOUND, String.format("PRODUCT_NOT_FOUND: %s", productId.toString()));
        }

        if (product.getAccountId() == null) {
            throw new ProductException(SeffafExceptionCode.PRODUCT_NOT_ACTIVATED, String.format("PRODUCT_NOT_ACTIVATED: %s", productId.toString()));
        }

        logger.info("product found! {}", product.getProductId());
        return product;
    }

}
