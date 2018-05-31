package com.learn.springcloudcontractclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LoanApplicationService {

    private RestTemplate restTemplate;
    private String port;

    @Autowired
    public LoanApplicationService(RestTemplate restTemplate){
        port = "6565";
        this.restTemplate = restTemplate;
    }

    public LoanApplicationVerificationResponse verifyLoanApplication(LoanApplication loanApplication) {

        FraudServiceRequest fraudServiceRequest = new FraudServiceRequest.Builder()
                .fromClientId("a23er456-3er4-drt5-1we34rfbghuy")
                .fromLoanAmount(loanApplication.getLoanAmount())
                .build();

        FraudServiceResponse fraudServiceResponse = fraudServiceResponseFor(fraudServiceRequest);

        return buildLoanApplicationVerificationResponseFrom(fraudServiceResponse);
    }

    private LoanApplicationVerificationResponse buildLoanApplicationVerificationResponseFrom(FraudServiceResponse fraudServiceResponse) {

        return new LoanApplicationVerificationResponse.Builder()
                .fromApplicationStatus(fraudServiceResponse.getFraudCheckStatus())
                .fromRejectionReason(fraudServiceResponse.getRejectionReason())
                .build();
    }

    private FraudServiceResponse fraudServiceResponseFor(FraudServiceRequest fraudServiceRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        ResponseEntity<FraudServiceResponse> response =
                restTemplate.exchange("http://localhost:" + port + "/fraudcheck", HttpMethod.PUT,
                        new HttpEntity<>(fraudServiceRequest, httpHeaders),
                        FraudServiceResponse.class);

        return response.getBody();
    }
}
