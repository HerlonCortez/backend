package com.herlon.backend.web.controlers;

import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //captura qualquer excessão do tipo ExceptionHandler(...)
public class ExceptionHandlerController {
    @ExceptionHandler(JobInstanceAlreadyCompleteException.class)
    private ResponseEntity<Object> handleFileAlreadyImported(
            JobInstanceAlreadyCompleteException exception
    ){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("O arquivo imformado já foi importado no sistema!");
    }
}
