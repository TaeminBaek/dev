package com.btem.dev.common.controller.adviser;

import com.btem.dev.common.model.JsonResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 같은 컨텍스트 내에 모든 컨트롤러에서 발생한 예외를 처리하는 리졸버.
 * 각각의 컨트롤러에서 @ExceptionHandler 가 붙은 메서드가 있으면 그것이 우선함.
 */
@ControllerAdvice
public class ExceptionResolver {
    /**
    @Autowired
    private MessageSourceAccessor messageSourceAccessor;
     */

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        String message = e.getMessage();
        String fieldName = "";

        /**
        if(e instanceof NoHandlerFoundException) //404
            message = messageSourceAccessor.getMessage("fail.find.page");
        */

        return JsonResponse.asFailure(fieldName, message);
    }
}
