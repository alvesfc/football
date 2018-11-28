package br.org.ehandler.exception;

import br.org.ehandler.exception.message.Message;

import java.util.Set;

/**
 * @author alvesfc
 * @version 1.0
 */
public class BadRequestException extends ApplicationException {

    /**
     * Construtor que cria uma exceção a partir de uma mensagem de erro.
     *
     * @param message -  Mensagem de erro.
     */
    public BadRequestException(final Message message) {
        super(message);
    }

    /**
     * Construtor que cria uma exceção a partir de uma mensagem de erro.
     *
     * @param messages -  Lista de Mensagens de erro.
     */
    public BadRequestException(final Set<Message> messages) {
        super(messages);
    }
}
