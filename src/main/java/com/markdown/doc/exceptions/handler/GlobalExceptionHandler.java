package com.markdown.doc.exceptions.handler;

import com.markdown.doc.exceptions.UserNotAllowedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotAllowedException.class})
    public ResponseEntity<Object>  handleUserNotAllowedException(final UserNotAllowedException e, final WebRequest webRequest){

        return handleExceptionInternal(e,"You are not allowed to execute this action",new HttpHeaders(), HttpStatus.FORBIDDEN,webRequest);
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<Object>  handleNoSuchElementException(final UserNotAllowedException e, final WebRequest webRequest){

        return handleExceptionInternal(e,"You are not allowed to execute this action",new HttpHeaders(), HttpStatus.FORBIDDEN,webRequest);
    }

}
