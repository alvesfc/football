package br.com.football.players;

import br.org.ehandler.ErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExHandler extends ErrorHandler {

  @Autowired
  public ExHandler(MessageSource messageSource) {
    super(messageSource);
  }
}
