package com.learn.springcloudcontractclient;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FraudServiceRequest {
    @JsonProperty(value = "client.id")
    private int clientId;

    private int loanAmount;


    public FraudServiceRequest(Builder builder) {
        loanAmount = builder.getLoanAmount();
        clientId = builder.getClientId();
    }

    public static class Builder {
        private int clientId;
        private int loanAmount;

        public Builder fromLoanAmount(int value) {
            this.loanAmount = value;
            return this;
        }

        public Builder fromClientId(int value) {
            this.clientId = value;
            return this;
        }

        public FraudServiceRequest build() {
            return new FraudServiceRequest(this);
        }

        public int getClientId() {
            return clientId;
        }

        public int getLoanAmount() {
            return loanAmount;
        }
    }
}
