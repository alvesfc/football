package br.org.ehandler;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ErrorResponse {

    @JsonProperty("errors")
    private List<ErrorMessage> errorMessages;

    public ErrorResponse() {
    }

    public ErrorResponse(List<ErrorMessage> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
