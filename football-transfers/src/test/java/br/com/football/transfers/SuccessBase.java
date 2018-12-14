package br.com.football.transfers;

import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.TestPropertySource;

@AutoConfigureStubRunner(ids = { "br.com.football.teams:football-teams-stubs:9077" },
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        consumerName = "success",
        stubsPerConsumer = true
)
@TestPropertySource(properties = { "endpoint.teams = http://localhost:9077"})
public abstract class SuccessBase extends BaseTests {

}
