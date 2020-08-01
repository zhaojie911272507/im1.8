package com.imooc3.exception;

import com.imooc3.utils.IMOOCJSONResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice/*因为是助手类*/
public class CustomExceptionHandler {//自定义异常助手类，这里捕获异常

    // 上传文件超过500k，捕获异常：MaxUploadSizeExceededException
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public IMOOCJSONResult handlerMaxUploadFile(MaxUploadSizeExceededException ex) {
        return IMOOCJSONResult.errorMsg("文件上传大小不能超过500k，请压缩图片或者降低图片质量再上传！");
    }
}
