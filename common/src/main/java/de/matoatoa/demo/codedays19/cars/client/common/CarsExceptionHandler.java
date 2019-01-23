package de.matoatoa.demo.codedays19.cars.client.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CarsExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Map<String, String>> handleException(Exception exception, HttpServletRequest request){
        final Map<String, String> message = new HashMap<>();
        message.put("Exception", exception.getLocalizedMessage());
        message.put("Request", request.getRequestURI());
        return new ResponseEntity<>(message, HttpStatus.BAD_GATEWAY);
    }
}
