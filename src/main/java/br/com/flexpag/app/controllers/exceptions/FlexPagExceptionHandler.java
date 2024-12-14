package br.com.flexpag.app.controllers.exceptions;

import br.com.flexpag.app.dtos.response.FieldErrorDto;
import br.com.flexpag.app.dtos.response.StandardErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@ControllerAdvice
public class FlexPagExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorResponseDto> handleHibernateValidation(HttpServletRequest request, MethodArgumentNotValidException exception){
      Set<FieldErrorDto> errors = new HashSet<>();
      exception.getFieldErrors().forEach(x -> {
          errors.add(new FieldErrorDto(x.getField(), x.getDefaultMessage()));
      });
      StandardErrorResponseDto standardErrorResponseDto = new StandardErrorResponseDto(Instant.now(), HttpStatus.BAD_REQUEST.value(), "Hibernate Validation", "Please check the errors to validate the payload", request.getRequestURI(), errors);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(standardErrorResponseDto);
    }
}
