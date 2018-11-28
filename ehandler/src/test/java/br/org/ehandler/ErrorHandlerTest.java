package br.org.ehandler;

import br.org.ehandler.exception.BadRequestException;
import br.org.ehandler.exception.NotFoundException;
import br.org.ehandler.exception.message.MessageDefault;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ErrorHandlerTest {

  @MockBean
  private HttpMessageNotReadableException httpMessageNotReadableException;
  @MockBean
  private MethodArgumentNotValidException methodArgumentNotValidException;
  @MockBean
  private BindingResult bindingResult;
  @MockBean
  private HttpHeaders headers;
  @MockBean
  private WebRequest request;
  @MockBean
  private MessageSource messageSource;

  @Test
  public void handleHttpMessageNotReadable() {

    when(httpMessageNotReadableException.getRootCause()).thenReturn(new BadRequestException(new MessageDefault("key", "arg1")));
    when(request.getLocale()).thenReturn(Locale.getDefault());

    ErrorHandler errorHandler = new ErrorHandler(this.messageSource);

    ResponseEntity<Object> responseEntity = errorHandler.handleHttpMessageNotReadable(httpMessageNotReadableException, headers, HttpStatus.BAD_REQUEST, request);

    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertNotNull(responseEntity.getBody());
  }

  @Test
  public void notFoundException() {

    when(request.getLocale()).thenReturn(Locale.getDefault());
    when(request.getHeader("content-type")).thenReturn(MediaType.APPLICATION_JSON_VALUE);

    ErrorHandler errorHandler = new ErrorHandler(this.messageSource);

    ResponseEntity<ErrorResponse> responseEntity = errorHandler.notFoundException(new NotFoundException(new MessageDefault("key", "arg1")), request);

    assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    assertNotNull(responseEntity.getBody());
  }

  @Test
  public void handleMethodArgumentNotValid() {

    List<FieldError> fieldErrors = new ArrayList<>();
    fieldErrors.add(new FieldError("value", "value","value"));

    when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
    when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
    when(request.getLocale()).thenReturn(Locale.getDefault());

    ErrorHandler errorHandler = new ErrorHandler(this.messageSource);

    ResponseEntity<Object> responseEntity = errorHandler.handleMethodArgumentNotValid(methodArgumentNotValidException, headers, HttpStatus.BAD_REQUEST, request);

    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertNotNull(responseEntity.getBody());
  }
}