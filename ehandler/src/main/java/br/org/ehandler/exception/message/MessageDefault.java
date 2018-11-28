package br.org.ehandler.exception.message;

import java.util.Objects;

/**
 * Implementação default da interface {@link Message}
 *
 * @author alvesfc
 * @version 1.0
 */
public class MessageDefault implements Message {

    private static final long serialVersionUID = 1L;

    private final String key;

    private final transient Object[] arguments;

    public MessageDefault(String key, Object... args) {
        this.key = Objects.requireNonNull(key);
        this.arguments = args;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object[] getArguments() {
        return this.arguments;
    }
}