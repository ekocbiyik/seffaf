package com.payment.seffaf.operation;

import com.payment.seffaf.utils.UtilsForSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * enbiya on 30.03.2019
 */
@Controller
public class SeffafBaseOperationExecutor implements ISeffafOperationExecutor {

    @Autowired
    private DefaultOperationExecutionStrategy executionStrategy;

    @Override
    public Object execute(String source, String operation, Object... params) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return executeInternal(sra.getRequest(), source, operation, params);
    }

    private Object executeInternal(HttpServletRequest httpRequest, String source, String operation, Object... params) {
        Class operationClass = SeffafOperationRegistry.get(source, operation);
        ISeffafOperation seffafOperation = (ISeffafOperation) UtilsForSpring.getSingleBeanOfType(operationClass);
        return executionStrategy.execute(seffafOperation, httpRequest, source, operation, params);
    }
}
