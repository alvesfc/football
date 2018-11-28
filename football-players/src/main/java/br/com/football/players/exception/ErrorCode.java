package br.com.football.players.exception;

import br.org.ehandler.exception.message.ErrorCodeDefault;

/**
 * Classe com os códigos de erros que podem ocorrer na aplicação.
 *
 * @author alvesfc
 * @version 1.0
 */
public final class ErrorCode extends ErrorCodeDefault {


  public static final String PLAYER_NOT_FOUND = "PLAYER_NOT_FOUND";
  public static final String VALUE_GREATER_EQUAL_THAN_ONE = "VALUE_GREATER_EQUAL_THAN_ONE";

  private ErrorCode() {
  }
}
