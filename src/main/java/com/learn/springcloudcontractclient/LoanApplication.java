package com.learn.springcloudcontractclient;

public class LoanApplication {
    private String loanNumber;
    private int loanAmount;

    public LoanApplication(String loanNumber, int loanAmount) {
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

}
