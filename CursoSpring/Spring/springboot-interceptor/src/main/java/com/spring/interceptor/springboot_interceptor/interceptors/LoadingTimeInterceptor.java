package com.spring.interceptor.springboot_interceptor.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("loadingTimeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HandlerMethod controller = (HandlerMethod) handler;
        logger.info("LoadingTimeInterceptor: preHandle() start " + controller.getMethod().getName());

        long start = System.currentTimeMillis();
        request.setAttribute("start", start);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        HandlerMethod controller = (HandlerMethod) handler;
        long end = System.currentTimeMillis();
        long start = (long) request.getAttribute("start");
        long time = end - start;
        logger.info("Tiempo transcurrido: " + time);
        logger.info("LoadingTimeInterceptor: postHandle() finish " + controller.getMethod().getName());

    }

}
