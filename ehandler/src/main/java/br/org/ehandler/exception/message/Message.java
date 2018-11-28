package br.org.ehandler.exception.message;

import java.io.Serializable;

/**
 * Interface que define os métodos que representa uma mensagem de erro.
 *
 * @author alvesfc
 * @version 1.0
 */
public interface Message extends Serializable {

    /**
     * Metodo respnsável em obter o valor da mensagem.
     *
     * @return String contendo o valor da mensagem.
     */
    String getKey();

    /**
     * Metodo respnsável em obter os parâmetros da mensagem.
     *
     * @return lista de parametros da mensagem.
     */
    Object[] getArguments();
}