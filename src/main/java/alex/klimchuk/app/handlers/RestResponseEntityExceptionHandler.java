package alex.klimchuk.app.handlers;

import alex.klimchuk.app.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex,
                                                   final HttpHeaders headers, final WebRequest request) {
        final String responseBody = "Constraint violation exception";
        return handleExceptionInternal(ex, responseBody, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex,
                                                   final HttpHeaders headers, final WebRequest request) {
        final String responseBody = "Data integrity violation exception";
        return handleExceptionInternal(ex, responseBody, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(final ResourceNotFoundException ex,
                                                                  final WebRequest request) {
        final String responseBody = "Resource not found exception";
        return new ResponseEntity<Object>(responseBody, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<Object> handleMethodNotAllowedException(final MethodNotAllowedException ex,
                                                                  final HttpHeaders headers, final WebRequest request) {
        final String responseBody = "Method not allowed Exception";
        return handleExceptionInternal(ex, responseBody, headers, HttpStatus.METHOD_NOT_ALLOWED, request);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLException(final SQLException ex,
                                                     final HttpHeaders headers, final HttpServletRequest httpRequest) {
        log.error("SQLException Occurred :: URL=" + httpRequest.getRequestURL());
        WebRequest request = (WebRequest) httpRequest;
        final String responseBody = "Problems with DataBase";
        return handleExceptionInternal(ex, responseBody, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleConflict(final RuntimeException ex,
                                                 final HttpHeaders headers, final WebRequest request) {
        log.warn("409 Status Code: ", ex);
        final String responseBody = "Data access exception";
        return handleExceptionInternal(ex, responseBody, headers, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleInternalServerError(final Exception ex,
                                                            final HttpHeaders headers, final WebRequest request) {
        log.error("500 Status Code: Internal server error", ex);
        final String responseBody = "Internal server error";
        return handleExceptionInternal(ex, responseBody, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
