package com.javacodewiz.exception;


import com.javacodewiz.constant.UserConstants;
import com.javacodewiz.dto.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGlobalException(Exception exception, WebRequest request)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            new ErrorMessage(request.getDescription(false),HttpStatus.INTERNAL_SERVER_ERROR, UserConstants.STATUS_500, LocalDateTime.now())
        );
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleToResourceNotFoundException(ResourceNotFoundException exception, WebRequest request)
    {
        ErrorMessage message = new ErrorMessage(
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                UserConstants.STATUS_404,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleToResourceAlreadyExistsException(ResourceAlreadyExistsException exception, WebRequest request)
    {
        ErrorMessage message = new ErrorMessage(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                UserConstants.STATUS_400,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }


    // logic to handle validation error and exception
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        Map<String ,String> map = new HashMap<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

        allErrors.forEach(error -> {
            String feildName = ((FieldError)error).getField();
            String description = error.getDefaultMessage();
            map.put(feildName,description);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

}