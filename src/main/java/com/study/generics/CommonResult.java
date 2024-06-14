package com.study.generics;

/**
 * @author wenqianqian
 */
public class CommonResult<T> {
    private Long resultCode;
    private String resultMsg;
    private T data;

    CommonResult(T data){
        this.data = data;
    }
    CommonResult(Long resultCode,String resultMsg,T data){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }
    CommonResult(Long resultCode,String resultMsg){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }


    public Long getResultCode() {
        return resultCode;
    }

    public void setResultCode(Long resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(200L,"操作成功!",data);
    }
    public static <T> CommonResult<T> fail(T data) {
        return new CommonResult<T>(500L,"操作失败!",data);
    }

}
