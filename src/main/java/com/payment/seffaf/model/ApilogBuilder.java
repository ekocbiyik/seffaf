package com.payment.seffaf.model;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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

        if (exception == null) {
        } else {
            apiLog.setException(exception.getMessage());
            // TODO: 01.04.2019
//            apiLog.setResponseCode(String.valueOf(exception.getCode()));
        }
        return apiLog;
    }

}
