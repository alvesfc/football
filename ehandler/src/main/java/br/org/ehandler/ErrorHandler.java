package br.org.ehandler;

import br.org.ehandler.exception.ApplicationException;
import br.org.ehandler.exception.BadRequestException;
import br.org.ehandler.exception.NotFoundException;
import br.org.ehandler.exception.message.ErrorCodeDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

  private final MessageSource messageSource;

  @Autowired
  public ErrorHandler(final MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    if (ex.getRootCause() instanceof BadRequestException) {
      BadRequestException exception = (BadRequestException) ex.getRootCause();

      ErrorResponse errorResponse = new ErrorResponse(exception.getMessages().stream()
              .map(message -> ErrorMessage.builder()
                      .code(message.getKey())
                      .message(messageSource.getMessage(
                              message.getKey(),
                              message.getArguments(),
                              request.getLocale()))
                      .build())
              .collect(Collectors.toList()));

      return new ResponseEntity(errorResponse, headers, HttpStatus.BAD_REQUEST);
    }

    List<ErrorMessage> errorMessages = new ArrayList<>();
    errorMessages.add(ErrorMessage.builder()
            .code(ErrorCodeDefault.INTERNAL_SERVER_ERROR).build());

    return handleExceptionInternal(ex, new ErrorResponse(errorMessages), headers, status, request);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> notFoundException(final NotFoundException exception, WebRequest request) {


    try {
      ErrorResponse errorResponse = new ErrorResponse(exception.getMessages().stream()
              .map(message -> ErrorMessage.builder()
                      .code(message.getKey())
                      .message(messageSource.getMessage(
                              message.getKey(),
                              message.getArguments(),
                              request.getLocale()))
                      .build())
              .collect(Collectors.toList()));

      return new ResponseEntity(errorResponse, getHttpHeaders(request), HttpStatus.NOT_FOUND);
    } catch (Exception ex) {
      log.error("m=notFoundException", ex);

      List<ErrorMessage> errorMessages = new ArrayList<>();
      errorMessages.add(ErrorMessage.builder()
              .code(ErrorCodeDefault.INTERNAL_SERVER_ERROR).build());

      return new ResponseEntity(errorMessages, getHttpHeaders(request), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    List<ErrorMessage> errorMessages = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(m -> new ErrorMessage(m.getDefaultMessage(), messageSource.getMessage(
                    m.getDefaultMessage(),
                    new Object[]{m.getField(), m.getRejectedValue()},
                    request.getLocale())))
            .collect(Collectors.toList());

    ErrorResponse errorResponse = new ErrorResponse(errorMessages);

    return new ResponseEntity(errorResponse, headers, HttpStatus.BAD_REQUEST);

  }

  private ResponseEntity<ErrorResponse> buildErrorResponse(final ApplicationException exception, final ServerWebExchange serverWebExchange) {
    log.debug("Application error: " + exception.getMessage() + "", exception);
    final HttpHeaders httpHeaders = getHttpHeaders(serverWebExchange.getRequest().getHeaders());

    ErrorResponse errorResponse = new ErrorResponse(exception.getMessages().stream()
            .map(message -> ErrorMessage.builder()
                    .code(message.getKey())
                    .message(messageSource.getMessage(
                            message.getKey(),
                            message.getArguments(),
                            getLocale(serverWebExchange)))
                    .build())
            .collect(Collectors.toList()));

    return new ResponseEntity(errorResponse, httpHeaders, HttpStatus.BAD_REQUEST);

  }

  private HttpHeaders getHttpHeaders(final HttpHeaders headers) {
    final HttpHeaders httpHeaders = new HttpHeaders();
    String contentType = MediaType.APPLICATION_JSON_VALUE;

    if (!headers.isEmpty() && headers.get("content-type") != null) {
      contentType = headers.get("content-type").get(0);
      if (contentType == null) {
        contentType = MediaType.APPLICATION_JSON_VALUE;
      }
    }

    httpHeaders.setContentType(MediaType.parseMediaType(contentType));
    return httpHeaders;
  }

  private HttpHeaders getHttpHeaders(final WebRequest request) {
    final HttpHeaders httpHeaders = new HttpHeaders();
    String contentType = MediaType.APPLICATION_JSON_VALUE;

    if (!StringUtils.isEmpty(request.getHeader("content-type"))) {
      contentType = request.getHeader("content-type");
    }

    httpHeaders.setContentType(MediaType.parseMediaType(contentType));
    return httpHeaders;
  }

  private Locale getLocale(final ServerWebExchange webExchange) {
    if (webExchange.getLocaleContext().getLocale() != null) {
      return webExchange.getLocaleContext().getLocale();
    } else {
      return Locale.getDefault();
    }
  }

}
