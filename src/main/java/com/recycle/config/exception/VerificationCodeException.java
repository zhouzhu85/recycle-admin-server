package com.recycle.config.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author zhouzhu
 * @date 2022/5/5
 */
public class VerificationCodeException extends AuthenticationException {

    public VerificationCodeException(String msg) {
        super(msg);
    }
}
