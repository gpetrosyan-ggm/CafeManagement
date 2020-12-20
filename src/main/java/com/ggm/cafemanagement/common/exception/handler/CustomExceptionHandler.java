package com.ggm.cafemanagement.common.exception.handler;

import com.ggm.cafemanagement.common.exception.CafeManagementException;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CafeManagementException.class)
    public ModelAndView handleCafeManagementException(CafeManagementException ex) {
        log.error("Exception has been thrown: {}", ex.getLogMsg(), ex);
        ModelAndView model = new ModelAndView("error");
        model.addObject("exceptionMessage", ex.getMessage());
        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception ex) {
        log.error("Exception has been thrown: {}", ex.getMessage(), ex);
        ModelAndView model = new ModelAndView("error");
        model.addObject("exceptionMessage", "Internal server error.");
        return model;
    }

}