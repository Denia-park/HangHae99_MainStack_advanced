package com.sparta.springadvanced_hh99homework.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestApiException {
    private String errorMessage;
    private String errCode;
}
