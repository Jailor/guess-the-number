package org.example.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to intercept the request before it is processed by the controller.
 * preHandle() is called before the handler controller is called.
 * postHandle() is called after the handler controller is called.
 * afterCompletion() is called after the response is rendered from the view.
 */
@Slf4j
public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("preHandle() is called. handler = {}", handler);
        log.debug("Request URL = {}", request.getRequestURL());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("postHandle() is called. handler = {}", handler);
        log.debug("Request URL = {}", request.getRequestURL());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("afterCompletion() is called. handler = {}", handler);
        log.debug("Request URL = {}", request.getRequestURL());
    }
}
