package com.sparta.springadvanced_hh99homework.exception;

import lombok.Getter;

@Getter
public class HGPrivateException extends RuntimeException {
    private ErrorCode errorCode;

    public HGPrivateException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
