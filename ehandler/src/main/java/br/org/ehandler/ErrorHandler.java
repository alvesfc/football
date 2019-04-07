package br.org.ehandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@ControllerAdvice
@RestController
public class ErrorHandler extends AbstractErrorWebExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @Override protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions
                .route(aPredicate, aHandler)
                .andRoute(anotherPredicate, anotherHandler);
    }
}
