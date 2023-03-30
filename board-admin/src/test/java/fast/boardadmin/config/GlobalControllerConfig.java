package fast.boardadmin.config;

import fast.boardadmin.service.VisitCounterService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
@TestConfiguration
public class GlobalControllerConfig {

    @MockBean
    private VisitCounterService visitCounterService;

    @BeforeTestMethod
    public void securitySetup() {
        given(visitCounterService.visitCount()).willReturn(0L);
    }

}