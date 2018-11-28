package br.com.football.player.skill.listener;

import br.com.football.player.skill.CassandraSpringBoot;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.cassandraunit.CQLDataLoader;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class CustomAbstractCassandraUnitTestExecutionListener extends AbstractTestExecutionListener implements Ordered {

  private static final Logger LOGGER = LoggerFactory.getLogger(CassandraUnitTestExecutionListener.class);
  public static final String DB_MIGRATION = "db/migration/";

  private static boolean initialized = false;

  private static Set<String> scripts = new HashSet<>();

  private EmbeddedCassandra getEmbeddedCassandra(Class clazz) {

    EmbeddedCassandra embeddedCassandra = AnnotationUtils.findAnnotation(clazz, EmbeddedCassandra.class);

    if (embeddedCassandra == null) {
      if (clazz.getClass().getSuperclass() == CassandraSpringBoot.class) {
        embeddedCassandra = AnnotationUtils.findAnnotation(clazz.getClass().getSuperclass(), EmbeddedCassandra.class);
      } else {
        throw new IllegalArgumentException("Annotation EmbeddedCassandra not found!");
      }
    }

    if (embeddedCassandra == null) {
      throw new IllegalArgumentException("Annotation EmbeddedCassandra not found!");
    } else {
      return embeddedCassandra;
    }
  }

  protected void startServer(final TestContext testContext) throws Exception {
    final Class aClass = testContext.getTestClass();

    final EmbeddedCassandra embeddedCassandra = getEmbeddedCassandra(aClass);

    if (!initialized) {
      String yamlFile = Optional.ofNullable(embeddedCassandra.configuration()).get();
      long timeout = embeddedCassandra.timeout();
      EmbeddedCassandraServerHelper.startEmbeddedCassandra(yamlFile, timeout);
      initialized = true;
    }


    CassandraDataSet cassandraDataSet = AnnotationUtils
            .findAnnotation(testContext.getTestClass(), CassandraDataSet.class);

    if (cassandraDataSet != null) {
      String keyspace = cassandraDataSet.keyspace();
      CQLDataLoader cqlDataLoader = new CQLDataLoader(EmbeddedCassandraServerHelper.getSession());

      List<String> cqls = findMigrationCQL();
      cqls.forEach(cql -> command(keyspace, cqlDataLoader, cql));

      Set<String> dataset = dataSetLocations(testContext, cassandraDataSet);
      dataset.forEach(cql -> command(keyspace, cqlDataLoader, cql));
    }
  }

  private void command(String keyspace, CQLDataLoader cqlDataLoader, String next) {
    if (!scripts.contains(next)) {
      boolean dropAndCreateKeyspace = scripts.size() == 0;
      scripts.add(next);
      cqlDataLoader
              .load(new ClassPathCQLDataSet(next, dropAndCreateKeyspace, false, keyspace));
    }
  }

  private List<String> findMigrationCQL() {
    List<String> files;
    try {
      URL dirURL = this.getClass().getClassLoader().getResource(DB_MIGRATION);

      files = Files.walk(Paths.get(dirURL.toURI()))
              .filter(file -> file.toString().endsWith(".cql"))
              .map(Path::getFileName)
              .map(path -> DB_MIGRATION + path.toString())
              .sorted((o1, o2) -> {

                Pattern compile = Pattern.compile("(?<versao>(\\d\\.?)*\\d)+");
                Matcher matcher1 = compile.matcher(o1);
                matcher1.find();
                String c1 = matcher1.group("versao");

                Matcher matcher2 = compile.matcher(o2);
                matcher2.find();
                String c2 = matcher2.group("versao");

                DefaultArtifactVersion d1 = new DefaultArtifactVersion(c1);
                DefaultArtifactVersion d2 = new DefaultArtifactVersion(c2);


                return d1.compareTo(d2);
              }).collect(Collectors.toList());
    } catch (Exception ex) {
      LOGGER.warn("DB migration files not loaded!", ex);
      files = new ArrayList<>();
    }
    return files;
  }

  private Set<String> dataSetLocations(TestContext testContext, CassandraDataSet cassandraDataSet) {
    String[] dataset = cassandraDataSet.value();
    if (dataset.length == 0) {
      String alternativePath = alternativePath(testContext.getTestClass(), true, cassandraDataSet.type().name());
      if (testContext.getApplicationContext().getResource(alternativePath).exists()) {
        dataset = new String[]{alternativePath.replace(ResourceUtils.CLASSPATH_URL_PREFIX + "/", "")};
      } else {
        alternativePath = alternativePath(testContext.getTestClass(), false, cassandraDataSet.type().name());
        if (testContext.getApplicationContext().getResource(alternativePath).exists()) {
          dataset = new String[]{alternativePath.replace(ResourceUtils.CLASSPATH_URL_PREFIX + "/", "")};
        } else {
          LOGGER.info("No dataset will be loaded");
        }
      }
    }
    return Arrays.asList(dataset).stream().collect(Collectors.toSet());
  }

  protected void cleanServer() {
    EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
  }

  protected String alternativePath(Class<?> clazz, boolean includedPackageName, String extension) {
    if (includedPackageName) {
      return ResourceUtils.CLASSPATH_URL_PREFIX + "/" + ClassUtils.convertClassNameToResourcePath(clazz.getName())
              + "-dataset" + "." + extension;
    } else {
      return ResourceUtils.CLASSPATH_URL_PREFIX + "/" + clazz.getSimpleName() + "-dataset" + "." + extension;
    }
  }

  @Override
  public int getOrder() {
    // since spring 4.1 the default-order is LOWEST_PRECEDENCE. But we want to start EmbeddedCassandra even before
    // the springcontext such that beans can connect to the started cassandra
    return 0;
  }

}