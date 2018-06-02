package com.learn.springcloudcontractclient;

import groovyjarjarantlr.build.Tool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.learn.springcloudcontractclient.LoanApplicationStatus.REJECTED;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"com.learn:spring-cloud-contract-server:+:stubs:6565"},
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
mappingsOutputFolder = "target/outputmappings/")
@DirtiesContext
public class LoanApplicationServiceTests {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @Test
    public void shouldBeRejectedDueToAbnormalLoanAmount() {
        LoanApplication loanApplication = new LoanApplication("loan-application-number", 99999);
        LoanApplicationVerificationResponse loanApplicationVerificationResponse = loanApplicationService.verifyLoanApplication(loanApplication);
        assertThat(loanApplicationVerificationResponse.getStatus()).isEqualTo(REJECTED);
        assertThat(loanApplicationVerificationResponse.getRejectionReason()).isEqualTo("Loan amount too high.");
    }
}
