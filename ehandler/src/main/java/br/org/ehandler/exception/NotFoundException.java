package br.org.ehandler.exception;

import br.org.ehandler.exception.message.Message;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * @author alvesfc
 * @version 1.0
 */
public class NotFoundException extends ApplicationException {

    /**
     * Construtor que cria uma exceção a partir de uma mensagem de erro.
     *
     * @param message -  Mensagem de erro.
     */
    public NotFoundException(final Mono<Set<Message>> message) {
        super(message);
    }

}
