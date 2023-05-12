package com.learning.hadef.advice;

import com.learning.hadef.domain.value.FailureEnum;
import com.learning.hadef.exception.RestTemplateCustomException;
import io.micrometer.core.instrument.util.IOUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Getter
@Setter
@EqualsAndHashCode
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return (clientHttpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                || clientHttpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        String errorMessage = IOUtils.toString(clientHttpResponse.getBody());
        int errorCode = clientHttpResponse.getStatusCode().value();
        throw new RestTemplateCustomException(errorMessage, errorCode);
    }
}
