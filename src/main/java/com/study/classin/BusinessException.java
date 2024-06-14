package com.study.classin;

import lombok.Data;

/**
 * @author wenqianqian
 */
@Data
public class BusinessException extends RuntimeException {
    private String errorCode;
    private String errorMsg;

    public BusinessException(String errorCode) {
        super("errorCode:" + errorCode);
        this.errorCode = errorCode;
    }

    public BusinessException(String errorCode, String errorMsg) {
        super("errorCode:" + errorCode + ";errorMsg:" + errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}
