package com.payment.seffaf.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * enbiya on 01.04.2019
 */
@Entity
@Table(name = "t_api_log")
public class ApiLog {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "apilog_id")
    private UUID apilLogId;

    @Column(name = "rest_service")
    private String restService;

    @Column(name = "rest_method")
    private String restMethod;

    @Column(name = "request_uri")
    private String requestURI;

    @Column(name = "request_method")
    private String requestMethod;

    @Column(name = "source_ip")
    private String sourceIp;

    @Column(name = "request", length = 999)
    private String request;

    @Column(name = "response", length = 999)
    private String response;

    @Column(name = "response_code")
    private String responseCode;

    @Column(name = "exception", length = 999)
    private String exception;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startDate")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finishDate")
    private Date finishDate;

    public UUID getApilLogId() {
        return apilLogId;
    }

    public void setApilLogId(UUID apilLogId) {
        this.apilLogId = apilLogId;
    }

    public String getRestService() {
        return restService;
    }

    public void setRestService(String restService) {
        this.restService = restService;
    }

    public String getRestMethod() {
        return restMethod;
    }

    public void setRestMethod(String restMethod) {
        this.restMethod = restMethod;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}
