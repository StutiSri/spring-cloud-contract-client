package com.learn.springcloudcontractclient;

public class LoanApplicationVerificationResponse {
    private LoanApplicationStatus status;
    private String rejectionReason;

    public LoanApplicationVerificationResponse(Builder builder) {
        this.status = builder.status;
        this.rejectionReason = builder.rejectionReason;
    }

    public LoanApplicationStatus getStatus() {
        return status;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public static class Builder {
        private LoanApplicationStatus status;
        private String rejectionReason;

        public Builder fromApplicationStatus(FraudCheckStatus fraudCheckStatus) {

            if (fraudCheckStatus == FraudCheckStatus.OK){
                this.status = LoanApplicationStatus.VERIFIED;
            }else{
                this.status = LoanApplicationStatus.REJECTED;
            }

            return this;
        }

        public Builder fromRejectionReason(String value) {
            this.rejectionReason = value;
            return this;
        }

        public LoanApplicationVerificationResponse build() {
            return new LoanApplicationVerificationResponse(this);
        }
    }

}
