package com.example.basic.config.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BasicInterceptor implements HandlerInterceptor{
    // preHandle : 전처리 작업
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                log.info("[BasicInterceptor][preHandle] Pre");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    // postHandle : 추가작업을 할 경우
    // postHandle > ModelAndView : 처리가 다 끝난 model과 view를 넘겨준다.
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
                log.info("[BasicInterceptor][postHandle] Post");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    // afterCompletion > Exception : 오류처리 -> 오류페이지 띄우기
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception {
                log.info("[BasicInterceptor][afterCompletion] After");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
        
}
