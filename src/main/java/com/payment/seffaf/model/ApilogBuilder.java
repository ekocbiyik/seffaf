package com.payment.seffaf.model;

import com.payment.seffaf.exceptions.SeffafException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * enbiya on 01.04.2019
 */
public class ApilogBuilder {

    private String restService;
    private String restMethod;
    private Exception exception;
    private HttpServletRequest httpRequest;
    private String requestBody;
    private String httpResponse;
    private Map resultMap;
    private Date startDate;
    private Date finishDate;

    public ApilogBuilder setRestService(String restService) {
        this.restService = restService;
        return this;
    }

    public ApilogBuilder setRestMethod(String restMethod) {
        this.restMethod = restMethod;
        return this;
    }

    public ApilogBuilder setException(Exception exception) {
        this.exception = exception;
        return this;
    }

    public ApilogBuilder setHttpRequest(HttpServletRequest httpRequest) {
        this.httpRequest = httpRequest;
        return this;
    }

    public ApilogBuilder setRequestBody(String requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public ApilogBuilder setHttpResponse(String httpResponse) {
        this.httpResponse = httpResponse;
        return this;
    }

    public ApilogBuilder setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public ApilogBuilder setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
        return this;
    }

    public ApilogBuilder setResultMap(Map resultMap) {
        this.resultMap = resultMap;
        return this;
    }

    public ApiLog build() {
        ApiLog apiLog = new ApiLog();
        apiLog.setRestService(restService);
        apiLog.setRestMethod(restMethod);
        apiLog.setResponse(httpResponse);
        apiLog.setStartDate(startDate);
        apiLog.setFinishDate(finishDate);

        if (httpRequest != null) {
            apiLog.setRequestURI(httpRequest.getRequestURI());
            apiLog.setRequestMethod(httpRequest.getMethod());
            apiLog.setSourceIp(httpRequest.getRemoteAddr());
            apiLog.setRequest(requestBody);
        }

        if (resultMap != null && resultMap.containsKey("responseCode")) {
            apiLog.setResponseCode(resultMap.get("responseCode").toString());
        }

        if (exception == null) {
        } else {
            apiLog.setException(SeffafException.parseExceptionMessage(exception, 999));
        }
        return apiLog;
    }

}
