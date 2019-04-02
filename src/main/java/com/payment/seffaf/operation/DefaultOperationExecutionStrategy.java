package com.payment.seffaf.operation;

import com.payment.seffaf.model.ApilogBuilder;
import com.payment.seffaf.repositories.service.IApiLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * enbiya on 30.03.2019
 */
@Controller
public class DefaultOperationExecutionStrategy {

    @Autowired
    private IApiLogService apiLogService;

    public synchronized Object execute(ISeffafOperation seffafOperation, HttpServletRequest httpRequest, String source, String operation, Object[] params) {
        return executeOperation(seffafOperation, source, operation, httpRequest, params);
    }

    private Object executeOperation(ISeffafOperation seffafOperation, String source, String operation, HttpServletRequest httpRequest, Object[] params) {

        Object result = null;
        ApilogBuilder builder = new ApilogBuilder();
        builder
                .setStartDate(new Date())
                .setRestService(source)
                .setHttpRequest(httpRequest)
                .setRestMethod(operation);
        try {
            Object requestBody = seffafOperation.init(params);
            builder.setRequestBody(requestBody.toString());

            seffafOperation.validate();
            result = seffafOperation.operate();
        } catch (Exception e) {
            e.printStackTrace();
            builder.setException(e);
            result = seffafOperation.handleException(e);
        } finally {
            builder
                    .setHttpResponse(result.toString())
                    .setResultMap((Map) result)
                    .setFinishDate(new Date());
            apiLogService.save(builder.build());
            return result;
        }
    }

}
