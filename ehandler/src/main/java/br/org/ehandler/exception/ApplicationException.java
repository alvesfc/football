package br.org.ehandler.exception;

import br.org.ehandler.exception.message.Message;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe que define os métodos que serão utilizados pelas exceções da aplicação.
 *
 * @author alvesfc
 * @version 1.0
 */
public abstract class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final transient Set<Message> messages;

    /**
     * Construtor que cria uma exceção genérica a partir de uma mensagem de erro.
     *
     * @param message -  objeto do tipo {@link Message} com a mensagem de erro.
     */
    protected ApplicationException(final Message message) {
        this.messages = new HashSet<>();
        this.messages.add(message);
    }

    /**
     * Construtor que cria uma exceção genérica a partir de uma mensagem de erro.
     *
     * @param messages -  objeto do tipo {@link Message} com a mensagem de erro.
     */
    protected ApplicationException(final Set<Message> messages) {
        this.messages = new HashSet<>();

        this.messages.addAll(messages);
    }

    /**
     * Recupera a lista das mensagens de erro.
     *
     * @return Lista das mensagens de erro que não pode ser modificada.
     */
    public Set<Message> getMessages() {
        return Collections.unmodifiableSet(messages);
    }
}
