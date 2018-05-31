package com.learn.springcloudcontractclient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FraudServiceResponse {
    private FraudCheckStatus fraudCheckStatus;

    @JsonProperty("rejection.reason")
    private String rejectionReason;

    public FraudCheckStatus getFraudCheckStatus() {
        return fraudCheckStatus;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }
}
