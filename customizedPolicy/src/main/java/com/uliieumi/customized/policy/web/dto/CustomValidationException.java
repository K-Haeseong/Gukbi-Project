package com.uliieumi.customized.policy.web.dto;

import lombok.Getter;

// 정상적인 접근이 아닐 때 바인딩 에러가 나는 경우
@Getter
public class CustomValidationException extends RuntimeException{

    private ErrorResult errorResult;

    public CustomValidationException() {
        super();
    }

    public CustomValidationException(String message) {
        super(message);
    }

    public CustomValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomValidationException(Throwable cause) {
        super(cause);
    }

    public CustomValidationException(ErrorResult errorResult) {
        super(errorResult.getMessage());
        this.errorResult = errorResult;
    }
}
