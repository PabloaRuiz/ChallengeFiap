package com.fiap.challengefiap.exception;

import com.fiap.challengefiap.dto.DefaultError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExcepcionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handlerNullPointerException(Exception e) {

        DefaultError defaultError = new DefaultError(HttpStatus.BAD_REQUEST.value(),
                "Cliente não encontrado, verifique se as informações estão corretas");

        return new ResponseEntity(defaultError, HttpStatus.BAD_REQUEST);
    }

}
