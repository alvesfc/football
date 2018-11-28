package br.com.football.teams;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.templates.TemplateFormats;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = FootballTeamsApplication.class)
@ActiveProfiles("contract-test")
@AutoConfigureMockMvc
@DirtiesContext
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
public abstract class BaseTests {

  @Autowired
  private WebApplicationContext context;

  @Autowired
  protected ObjectMapper objectMapper;

  @Rule
  public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

  protected MockMvc mockMvc;

  private Map<String, List<FieldDescriptor>> fieldDescriptors;

  public BaseTests(Map<String, List<FieldDescriptor>> fieldDescriptors) {
    this.fieldDescriptors = fieldDescriptors;
  }

  @Autowired
  protected MongoTemplate mongoTemplate;

  @Before
  public void setup() {

    String methodName = restDocumentation.beforeOperation().getTestMethodName();

    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
            .apply(documentationConfiguration(this.restDocumentation)
                    .uris()
                    .withScheme("https")
                    .withHost("football.com")
                    .withPort(443)
                    .and()
                    .snippets().withEncoding("UTF-8")
                    .withTemplateFormat(TemplateFormats.asciidoctor())
                    .and()
                    .operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint()))
            .alwaysDo(buildResultHandler(methodName))
            .build();

    RestAssuredMockMvc.mockMvc(this.mockMvc);
  }


  private ResultHandler buildResultHandler(final String methodName) {

    if (fieldDescriptors.get("request_" + methodName) != null && fieldDescriptors.get("response_" + methodName) != null) {
      return MockMvcRestDocumentation.document(
              restDocumentation.beforeOperation().getTestMethodName(),
              requestFields(fieldDescriptors.get("request_" + methodName)),
              responseFields(fieldDescriptors.get("response_" + methodName)));
    } else if (fieldDescriptors.get("request_" + methodName) != null && fieldDescriptors.get("response_" + methodName) == null) {
      return MockMvcRestDocumentation.document(
              restDocumentation.beforeOperation().getTestMethodName(),
              requestFields(fieldDescriptors.get("request_" + methodName)));
    } else if (fieldDescriptors.get("response_" + methodName) != null && fieldDescriptors.get("request_" + methodName) == null) {
      return MockMvcRestDocumentation.document(
              restDocumentation.beforeOperation().getTestMethodName(),
              responseFields(fieldDescriptors.get("response_" + methodName)));
    } else {
      return MockMvcRestDocumentation.document(
              restDocumentation.beforeOperation().getTestMethodName());
    }
  }
}
