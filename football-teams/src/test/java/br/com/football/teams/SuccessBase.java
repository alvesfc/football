package br.com.football.teams;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SuccessBase extends BaseTests {

  @Autowired
  protected MongoTemplate mongoTemplate;

  private static MongodProcess mongodProcess;

  @BeforeClass
  public static void setupMongo() throws Exception {
    if (mongodProcess == null || !mongodProcess.isProcessRunning()) {
      IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.V3_6)
              .net(new Net("localhost", 27019, Network.localhostIsIPv6()))
              .build();

      MongodStarter starter = MongodStarter.getDefaultInstance();
      MongodExecutable mongodExecutable = starter.prepare(mongodConfig);
      mongodProcess = mongodExecutable.start();
    }
  }


  public SuccessBase() {
    super(SuccessBase.shouldCreatePlayerFields());
  }

  public SuccessBase(Map<String, List<FieldDescriptor>> fieldDescriptors) {
    super(fieldDescriptors);
  }

  private static Map<String, List<FieldDescriptor>> shouldCreatePlayerFields() {

    Map<String, List<FieldDescriptor>> values = new HashMap<>();

    values.put("request_validate_shouldCreateTeam", TeamCreateDescriptors.builder()
            .withName()
            .withFullname()
            .withAcronym()
            .withCountry()
            .build());

    values.put("response_validate_shouldCreateTeam", TeamCreateDescriptors.builder()
            .withCode()
            .build());

    return values;
  }

}
