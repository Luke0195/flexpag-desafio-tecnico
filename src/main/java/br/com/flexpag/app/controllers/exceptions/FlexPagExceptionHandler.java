package br.com.flexpag.app.controllers.exceptions;

import br.com.flexpag.app.dtos.response.FieldErrorDto;
import br.com.flexpag.app.dtos.response.StandardErrorResponseDto;
import br.com.flexpag.app.services.appointment.exceptions.BusinessException;
import br.com.flexpag.app.services.appointment.exceptions.EntityNotFoundException;
import jakarta.persistence.EntityExistsException;
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

    private static final Integer BAD_REQUEST_STATUS_CODE = HttpStatus.BAD_REQUEST.value();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorResponseDto> handleHibernateValidation(HttpServletRequest request, MethodArgumentNotValidException exception){
      Set<FieldErrorDto> errors = new HashSet<>();
      exception.getFieldErrors().forEach(x -> {
          errors.add(new FieldErrorDto(x.getField(), x.getDefaultMessage()));
      });
      StandardErrorResponseDto standardErrorResponseDto = new StandardErrorResponseDto(Instant.now(), BAD_REQUEST_STATUS_CODE, "Hibernate Validation", "Please check the errors to validate the payload", request.getRequestURI(), errors);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(standardErrorResponseDto);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardErrorResponseDto> handleBusinessException(HttpServletRequest request, BusinessException exception ){
      StandardErrorResponseDto standardErrorResponseDto = makeStandardResponseDto(BAD_REQUEST_STATUS_CODE, "Invalid bunisses logic", exception.getMessage(),request.getRequestURI(), new HashSet<>());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(standardErrorResponseDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardErrorResponseDto> handleEntityNotFound(HttpServletRequest request, EntityNotFoundException exception){
        StandardErrorResponseDto standardErrorResponseDto = makeStandardResponseDto(HttpStatus.NOT_FOUND.value(), "Entity not found", exception.getMessage(), request.getRequestURI(), new HashSet<>());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(standardErrorResponseDto);
    }

    private static StandardErrorResponseDto makeStandardResponseDto(Integer status, String error, String message, String path, Set<FieldErrorDto> errors){
        return new StandardErrorResponseDto(Instant.now(),status, error, message, path, errors);
    }


}
