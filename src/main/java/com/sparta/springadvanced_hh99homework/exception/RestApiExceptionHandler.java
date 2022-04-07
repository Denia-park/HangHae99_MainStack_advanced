package com.sparta.springadvanced_hh99homework.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(value = { HGPrivateException.class })
    public ResponseEntity<Object> handleApiRequestException(HGPrivateException ex) {
        String errCode = ex.getErrorCode().getErrorCode();
        String errMSG = ex.getErrorCode().getErrorMessage();
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.setErrCode(errCode);
        exceptionResponseDto.setErrorMessage(errMSG);

        System.out.println("ERR :" + errCode + " , " + errMSG);  //Log용

        return new ResponseEntity(
                exceptionResponseDto,
                ex.getErrorCode().getHttpStatus()
        );
    }
}
