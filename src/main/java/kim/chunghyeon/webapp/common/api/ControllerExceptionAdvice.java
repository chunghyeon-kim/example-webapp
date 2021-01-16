package kim.chunghyeon.webapp.common.api;

import kim.chunghyeon.webapp.common.api.dto.ApiErrorDto;
import kim.chunghyeon.webapp.common.domain.exception.DomainNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(DomainNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiErrorDto handleDomainException(RuntimeException e) {
        return ApiErrorDto.builder()
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now())
                .build();
    }
}
