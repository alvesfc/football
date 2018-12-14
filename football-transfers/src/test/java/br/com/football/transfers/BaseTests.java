package br.com.football.transfers;

import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = FootballTransfersApplication.class,
        properties = "server.port=0")
@ActiveProfiles("contract-test")
//@AutoConfigureMockMvc
//@DirtiesContext
//@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
public abstract class BaseTests {

    @LocalServerPort
    int port;

    protected MockMvc mockMvc;

    @Before
    public void setup() {

        RestAssured.baseURI = "http://localhost:" + port;
        RestAssuredMockMvc.mockMvc(this.mockMvc);
    }

}
