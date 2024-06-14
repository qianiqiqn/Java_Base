package com.study.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.classin.BasicResult;
import com.study.classin.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回值封装和全局异常捕获
 *
 * @author wenqianqian
 */
@RestControllerAdvice
@Slf4j
public class BasicResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private static ObjectMapper mapper = new ObjectMapper();
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return !returnType.getParameterType().equals(BasicResult.class)
            && !returnType.getDeclaringClass().getName().contains("springfox");
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType mediaType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        BasicResult<Object> result = new BasicResult<>(BasicResult.SUCCESS_CODE, BasicResult.SUCCESS_MSG, body);
        if(body instanceof String){
            try {
                return mapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @ExceptionHandler(BusinessException.class)
    public BasicResult<Object> exceptionHandler(BusinessException e) {
        log.error(e.getErrorCode(), e);
        return new BasicResult<>(e.getErrorCode(), e.getErrorMsg(), e.getCause());
    }

    @ExceptionHandler(Exception.class)
    public BasicResult<Object> exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return new BasicResult<>(BasicResult.FAIL_CODE, BasicResult.FAIL_MSG, e.getCause());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BasicResult<Object> exceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error ->{
            String fiedName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fiedName,errorMessage);
        });
        return new BasicResult<>(BasicResult.PARAMETER_ERROR_CODE, BasicResult.PARAMETER_ERROR_NAME, errors);
    }

    @ExceptionHandler(BindException.class)
    public BasicResult<Object> exceptionHandler(BindException e) {
        log.error(e.getMessage(), e);
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error ->{
            String fiedName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fiedName,errorMessage);
        });
        return new BasicResult<>(BasicResult.PARAMETER_ERROR_CODE, BasicResult.PARAMETER_ERROR_NAME, errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public BasicResult<Object> exceptionHandler(ConstraintViolationException  e) {
        log.error(e.getMessage(), e);
        Map<String,String> errors = new HashMap<>();
        e.getConstraintViolations().forEach(error ->{
            String fiedName = ((FieldError)error).getField();
            String errorMessage = error.getMessage();
            errors.put(fiedName,errorMessage);
        });
        return new BasicResult<>(BasicResult.PARAMETER_ERROR_CODE, BasicResult.PARAMETER_ERROR_NAME, errors);
    }

}
