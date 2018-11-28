package br.com.football.players;

import br.org.ehandler.annotation.EnableEHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

@EntityScan(
        basePackageClasses = {FootballPlayersApplication.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
@EnableEHandler
public class FootballPlayersApplication {

  private static final Logger log = LoggerFactory.getLogger(FootballPlayersApplication.class);
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
      final SpringApplication app = new SpringApplication(FootballPlayersApplication.class);
      final Environment env = app.run(args).getEnvironment();

      log.info("Access URLs:\n----------------------------------------------------------\n\t"
                      + "External: \thttp://{}:{}\n----------------------------------------------",
              InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port") + " Profiles: " + Arrays
                      .toString(env.getActiveProfiles()));
    } catch (Exception e) {
      log.error("Start Error.", e);
    }
  }

}
