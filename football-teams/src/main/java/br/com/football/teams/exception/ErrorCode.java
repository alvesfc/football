package br.com.football.teams.exception;

import br.org.ehandler.exception.message.ErrorCodeDefault;

/**
 * Classe com os códigos de erros que podem ocorrer na aplicação.
 *
 * @author alvesfc
 * @version 1.0
 */
public final class ErrorCode extends ErrorCodeDefault {


  public static final String TEAM_NOT_FOUND = "TEAM_NOT_FOUND";

  private ErrorCode() {
  }
}
