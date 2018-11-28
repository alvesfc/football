package br.com.football.teams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

@SpringBootApplication
public class FootballTeamsApplication {

  private static final Logger log = LoggerFactory.getLogger(FootballTeamsApplication.class);
  private static ApplicationContext context;

  /**
   * Inicia a aplicacao
   *
   * @param args - Lista de parametros da aplicacao
   * @throws UnknownHostException - Caso nao consiga obter as informacoes do Host
   */
  public static void main(final String[] args) {
    try {
      System.setProperty("spring.devtools.restart.enabled", "false");
      final SpringApplication app = new SpringApplication(FootballTeamsApplication.class);
      final SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
      addDefaultProfile(app, source);
      final Environment env = app.run(args).getEnvironment();

      log.info("Access URLs:\n----------------------------------------------------------\n\t"
                      + "External: \thttp://{}:{}\n----------------------------------------------",
              InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port") + " Profiles: " + Arrays
                      .toString(env.getActiveProfiles()));
    } catch (Exception e) {
      log.error("Start Error.", e);
    }
  }

  private static void addDefaultProfile(final SpringApplication app, final SimpleCommandLinePropertySource source) {
    if (!source.containsProperty("spring.profiles.active")
            && !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {
      app.setAdditionalProfiles("local");
    }
  }

}
