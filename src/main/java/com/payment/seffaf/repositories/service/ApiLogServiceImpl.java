package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.ApiLog;
import com.payment.seffaf.repositories.dao.IApiLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * enbiya on 01.04.2019
 */
@Service
public class ApiLogServiceImpl implements IApiLogService {

    @Autowired
    private IApiLogDao apiLogDao;

    @Transactional
    @Override
    public void save(ApiLog apiLog) {
        apiLogDao.save(apiLog);
    }
}
