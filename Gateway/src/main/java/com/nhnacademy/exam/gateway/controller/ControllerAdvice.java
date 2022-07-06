package com.nhnacademy.exam.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public String handleException(Exception ex, Model model) {
        log.error("exception 발생 : {}", ex.getMessage());
        model.addAttribute("msg", "잠시후 다시 시도해주세요");
        return "error/error";
    }
}
