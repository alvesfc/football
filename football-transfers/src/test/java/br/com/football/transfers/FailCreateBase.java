package br.com.football.transfers;

import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

@AutoConfigureStubRunner(ids = { "br.com.football.teams:football-teams-stubs:9078",
        "br.com.football.players:football-players-stubs:9072" },
        repositoryRoot = "https://github.com/alvesfc/football/raw/master/repository",
        stubsMode = StubRunnerProperties.StubsMode.REMOTE,
        consumerName = "fail",
        stubsPerConsumer = true
)
public abstract class FailCreateBase extends BaseTests {

}
