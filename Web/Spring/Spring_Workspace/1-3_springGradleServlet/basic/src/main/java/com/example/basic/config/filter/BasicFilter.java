package com.example.basic.config.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

// Component : Spring이 Filter로 인식
// req, resp 둘다 처리하는 곳
@Component
@Slf4j
public class BasicFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("[BasicFilter][doFilter] Start!");
        
        // chain을 통해 req, resp를 묶어준다.
        chain.doFilter(request, response);
    }

}
