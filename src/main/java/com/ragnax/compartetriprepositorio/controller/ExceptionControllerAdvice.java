package com.ragnax.compartetriprepositorio.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ragnax.compartetriprepositorio.controller.response.RagnaxError;
import com.ragnax.compartetriprepositorio.exception.LogicaImplException;

/**
 * Created by julito el mas lindo on 09-08-19.
 */
@ControllerAdvice
@ResponseBody
public class ExceptionControllerAdvice {

    private static final Logger LOGGER = LogManager.getLogger(ExceptionControllerAdvice.class);

    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ExceptionHandler(LogicaImplException.class)
    public ResponseEntity<RagnaxError> handlerException(LogicaImplException lie) {
        LOGGER.error("Error en politicacomercial: {} .", lie.getMessage());
//        return new ResponseEntity<>(new Response(e.getMessage(),null, HttpStatus.INTERNAL_SERVER_ERROR.value(), null), HttpStatus.INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<>(new PoliticaComercialError(new PoliticaComercialError(e.getMessage()),  HttpStatus.SERVICE_UNAVAILABLE.value(), null, null, null), HttpStatus.SERVICE_UNAVAILABLE);
        
        return new ResponseEntity<>(new RagnaxError(HttpStatus.ACCEPTED.value(), lie.getMessage()),
                HttpStatus.ACCEPTED);
        
    }
    
    @ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<RagnaxError> handlerException(NumberFormatException nfe) {
        LOGGER.error("Error en politicacomercial: {} .", nfe.getMessage());
//        return new ResponseEntity<>(new Response(e.getMessage(),null, HttpStatus.INTERNAL_SERVER_ERROR.value(), null), HttpStatus.INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<>(new Response(new PoliticaComercialError(e.getMessage()),  HttpStatus.ALREADY_REPORTED.value(), null, null, null), HttpStatus.ALREADY_REPORTED);
        return new ResponseEntity<>(new RagnaxError(HttpStatus.ALREADY_REPORTED.value(), nfe.getMessage()),
                HttpStatus.ALREADY_REPORTED);
    }
    
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RagnaxError> handlerException(Exception e) {
        LOGGER.error("Error en politicacomercial: {} .", e.getMessage());
//        return new ResponseEntity<>(new Response(e.getMessage(),null, HttpStatus.INTERNAL_SERVER_ERROR.value(), null), HttpStatus.INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<>(new Response(new PoliticaComercialError(e.getMessage()),  HttpStatus.SERVICE_UNAVAILABLE.value(), null, null, null), HttpStatus.SERVICE_UNAVAILABLE);
        return new ResponseEntity<>(new RagnaxError(HttpStatus.ALREADY_REPORTED.value(), e.getMessage()),
                HttpStatus.ALREADY_REPORTED);
    }
}