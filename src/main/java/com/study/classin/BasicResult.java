package com.study.classin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wenqianqian
 */
@Data
public final class BasicResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String SUCCESS_CODE = "000";

    public static final String SUCCESS_MSG = "success";

    public static final String FAIL_CODE = "999";

    public static final String FAIL_MSG = "fail";

    public static final String PARAMETER_ERROR_CODE = "400";

    public static final String PARAMETER_ERROR_NAME = "参数校验异常";

    private String resultCode;
    private String resultMsg;
    private Object data;

    public BasicResult() {
    }

    public BasicResult(String code) {
        this.resultCode = code;
    }

    public BasicResult(String code, String resultMsg) {
        this.resultCode = code;
        this.resultMsg = resultMsg;
    }

    public BasicResult(String code, String resultMsg, Object data) {
        this.resultCode = code;
        this.resultMsg = resultMsg;
        this.data = data;
    }

//
//    public T checkResult() throws BusinessException {
//        if (!"TCM-000".equals(this.resultCode)) {
//            BusinessException BusinessException = null;
//
//            try {
//                BusinessException e = (BusinessException)JacksonUtils.json2pojo(this.resultMsg, BusinessException.class);
//                if (!StringUtils.isEmpty(e.getErrorCode())) {
//                    BusinessException = new BusinessException(e.getErrorCode(), e.getParams(), e.getErrorMsg());
//                }
//            } catch (Exception var3) {
//                log.error(var3.getMessage(), var3);
//            }
//
//            if (BusinessException == null) {
//                BusinessException = new BusinessException(this.resultCode, this.resultMsg);
//            }
//
//            throw BusinessException;
//        } else {
//            return this.data;
//        }
//    }

    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(this.resultCode);
    }


}
