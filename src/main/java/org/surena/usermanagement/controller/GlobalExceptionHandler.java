package org.surena.usermanagement.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.surena.usermanagement.exception.InvalidPasswordException;

@Slf4j
@Hidden
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = OptimisticLockingFailureException.class)
    public ResponseEntity<String> optimisticExceptionHandler(OptimisticLockingFailureException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>("خطای همزمانی",
                HttpStatusCode.valueOf(555));
    }

    @ExceptionHandler(value = InvalidPasswordException.class)
    public ResponseEntity<String> passwordExceptionHandler(InvalidPasswordException e) {
        String message = e.getMessage();
        log.error(message);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<String> nullPointerExceptionHandler(NullPointerException exception) {
        String message = exception.getMessage();
        if (StringUtils.isEmpty(message)) message = "خطای مقدار خالی !!";
        log.error(message);
        return ResponseEntity.internalServerError().body(message);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> runtimeExceptionHandler(RuntimeException e) {
        String message;
        if (e.getCause() != null) {
            message = e.getCause().getMessage();
        } else {
            message = e.getMessage();
        }
        log.error(message);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> globalExceptionHandler(Exception e) {
        String message;
        try {
            message = e.getCause().getCause().getMessage();
        } catch (NullPointerException ignored) {
            message = e.getMessage();
        }
        log.error(message);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
