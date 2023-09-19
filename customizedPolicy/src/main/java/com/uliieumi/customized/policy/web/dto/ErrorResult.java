package com.uliieumi.customized.policy.web.dto;

import lombok.Data;


@Data
public class ErrorResult {

        private int statusCode;
        private String message;
        private String errorCode;
        private String field;

        public ErrorResult(int statusCode, String message, String errorCode, String field) {
            this.statusCode = statusCode;
            this.message = message;
            this.errorCode = errorCode;
            this.field = field;
        }
}
