package com.task.databaseinspector.exception.handler;

import com.task.databaseinspector.busobj.response.GeneralResponse;
import com.task.databaseinspector.exception.ConnectionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<GeneralResponse> handleConflict(DataIntegrityViolationException ex, WebRequest request) {
        String errorMessage = "";

        org.hibernate.exception.ConstraintViolationException exDetail =
                (org.hibernate.exception.ConstraintViolationException) ex.getCause();
        errorMessage = messageSource.getMessage(exDetail.getConstraintName().replaceAll("_", "."), null, LocaleContextHolder.getLocale());

        GeneralResponse generalResponse = new GeneralResponse(false, errorMessage);
        return ResponseEntity.badRequest().body(generalResponse);
    }

    @ExceptionHandler(value = {ConnectionNotFoundException.class})
    protected ResponseEntity<GeneralResponse> handleConflict(ConnectionNotFoundException ex, WebRequest request) {
        GeneralResponse generalResponse = new GeneralResponse(false, messageSource.getMessage("connection.not.found", new Object[] {ex.getConnectionId()}, LocaleContextHolder.getLocale()));
        return ResponseEntity.badRequest().body(generalResponse);
    }


    @Override
    protected ResponseEntity<Object> handleBindException(BindException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMsg = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exception.getMessage());

        GeneralResponse generalResponse = new GeneralResponse(false, errorMsg);

        return ResponseEntity.badRequest().body(generalResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final GeneralResponse generalResponse = new GeneralResponse(false, ex.getLocalizedMessage());
        return ResponseEntity.badRequest().body(generalResponse);
    }

}
