package com.payment.seffaf.operation;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicLong;

/**
 * enbiya on 30.03.2019
 */
@Controller
public class DefaultOperationExecutionStrategy {

    final AtomicLong atomicLong = new AtomicLong();

    public synchronized Object execute(ISeffafOperation seffafOperation, HttpServletRequest httpRequest, String source, String operation, Object[] params) {
        final Long internalTrackingId = atomicLong.incrementAndGet();
        seffafOperation.init(params);
        return executeOperation(seffafOperation, internalTrackingId, source, operation, httpRequest);
    }


    protected Object executeOperation(ISeffafOperation seffafOperation, Long internalTrackingId, String source, String operation, HttpServletRequest httpRequest) {

        seffafOperation.validate();
        seffafOperation.operate();
//        seffafOperation.handleException(new Exception());

        // TODO: 30.03.2019 buradan devam et
        return seffafOperation.operate();
    }


}
