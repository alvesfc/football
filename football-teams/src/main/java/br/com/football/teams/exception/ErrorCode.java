package br.com.football.teams.exception;

import br.org.ehandler.exception.message.ErrorCodeDefault;

/**
 *Class with error codes that may occur in the application.
 *
 * @author alvesfc
 * @version 1.0
 */
public final class ErrorCode extends ErrorCodeDefault {


  public static final String TEAM_NOT_FOUND = "TEAM_NOT_FOUND";

  private ErrorCode() {
  }
}
