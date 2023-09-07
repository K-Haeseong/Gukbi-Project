package com.uliieumi.customized.policy.web.dto;

import lombok.Builder;
import lombok.Data;


@Data
public class ErrorResult {

        private String message;
        private String code;

        public ErrorResult(String message, String code) {
            this.message = message;
            this.code = code;
        }
    }
