package com.payment.seffaf.repositories.dao;

import com.payment.seffaf.model.ApiLog;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * enbiya on 01.04.2019
 */
public interface IApiLogDao extends CrudRepository<ApiLog, UUID> {
}
