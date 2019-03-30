package com.payment.seffaf.utils;

import com.payment.seffaf.model.Customer;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Customer> {
    @Override
    public Optional<Customer> getCurrentAuditor() {
        return Optional.empty();
//        return ((Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}