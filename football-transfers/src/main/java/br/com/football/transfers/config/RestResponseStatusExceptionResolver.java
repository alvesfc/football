package br.com.football.transfers.config;

import br.com.football.transfers.converter.GenericConverter;
import br.org.ehandler.exception.message.MessageDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
public class RestResponseStatusExceptionResolver {

    private GenericConverter genericConverter;

    @Autowired
    public RestResponseStatusExceptionResolver(GenericConverter genericConverter) {
        this.genericConverter = genericConverter;
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity handleWebClientResponseException(WebClientResponseException ex) {
        System.out.println(ex.getResponseBodyAsString());

        return ResponseEntity.badRequest()
                .body(ex.getResponseBodyAsString());
    }

}
