package phieulong.api.handler;

import phieulong.api.resources.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Resource<Object>> handleException(final Exception e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }

//    @ExceptionHandler(ApplicationException.class)
//    public ResponseEntity<Resource<Object>> handleApplicationException(final ApplicationException e) {
//        return error(e.getCode(), e.getMessage(), e);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Resource<Object>> handleApplicationException(final MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        if (fieldError != null) {
            final String message = format("%s: %s - %s", fieldError.getField(),
                    fieldError.getRejectedValue(),
                    fieldError.getDefaultMessage());
            return error(HttpStatus.BAD_REQUEST, message, e);
        } else {
            return error(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Resource<Object>> handleException(final HttpMessageNotReadableException e) {
        return error(HttpStatus.BAD_REQUEST, e.getMessage(), e);
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Resource<Object>> handleUnauthorizedException(Exception e) {
        return error(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
    }

    private ResponseEntity<Resource<Object>> error(HttpStatus status, final String message, final Exception e) {
        log.error(message, e);
        return new ResponseEntity<>(new Resource<>(status.value(), message), status);
    }

//    private ResponseEntity<Resource<Object>> error(int code, final String message, final ApplicationException e) {
//        log.error(message, e);
//        final HttpStatus httpStatus = buildHttpStatus(e);
//        return new ResponseEntity<>(new Resource<>(code, message), httpStatus);
//    }
//
//    private HttpStatus buildHttpStatus(final ApplicationException e) {
//        return ofNullable(HttpStatus.resolve(e.getHttpCode())).orElseGet(() -> {
//            log.error("Cannot resolve the HttpStatus of exception (code [{}], httpCode[{}], message [{}])",
//                    e.getCode(), e.getHttpCode(), e.getMessage());
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        });
//    }
}
