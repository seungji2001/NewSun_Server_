package com.newsun.Newsun.exception;


import com.newsun.Newsun.dto.exception.ResponseDto;
import com.newsun.Newsun.type.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseDto<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error(
                "handleHttpMessageNotReadableException() in GlobalExceptionHandler throw Exception : {}",
                e.getMessage()
        );
        return ResponseDto.fail(new CustomException(ErrorCode.BAD_REQUEST_BINDING_JSON));
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseDto<?> handleDoesNotExistEndPointException(Exception e) {
        log.error(
                "handleDoesNotExistEndPointException() in GlobalExceptionHandler throw Exception : {}",
                e.getMessage()
        );
        return ResponseDto.fail(new CustomException(ErrorCode.NOT_FOUND_END_POINT));
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ResponseDto<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e ) {
        log.error(
                "handleMissingServletRequestParameterException() in GlobalExceptionHandler throw Exception : {}",
                e.getMessage()
        );
        return ResponseDto.fail(e);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseDto<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleArgumentNotValidException() in GlobalExceptionHandler throw Exception : {}", e.getMessage());
        return ResponseDto.fail(e);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseDto<?> handleArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error(
                "handleArgumentTypeMismatchException() in GlobalExceptionHandler throw Exception : {}",
                e.getMessage()
        );
        return ResponseDto.fail(e);
    }

    @ExceptionHandler(value = {CustomException.class})
    public ResponseDto<?> handleCustomException(CustomException e) {
        log.error("handleCustomException() in GlobalExceptionHandler throw Exception : {}", e.getMessage());
        return ResponseDto.fail(e);
    }

    // 서버, Database Exception
    @ExceptionHandler(value = {Exception.class})
    public ResponseDto<?> handleException(Exception e) {
        log.error("handleException() in GlobalExceptionHandler throw Exception : {}", e.getMessage());
        return ResponseDto.fail(new CustomException(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
