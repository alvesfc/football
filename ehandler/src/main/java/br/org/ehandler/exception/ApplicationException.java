package br.org.ehandler.exception;

import br.org.ehandler.exception.message.Message;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * Classe que define os métodos que serão utilizados pelas exceções da aplicação.
 *
 * @author alvesfc
 * @version 1.0
 */
public abstract class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final transient Mono<Set<Message>> messages;

    /**
     * Construtor que cria uma exceção genérica a partir de uma mensagem de erro.
     *
     * @param message -  objeto do tipo {@link Message} com a mensagem de erro.
     */
    protected ApplicationException(final Mono<Set<Message>> message) {
        this.messages = message;
    }

    public Mono<Set<Message>> getMessages() {
        return messages;
    }
}
