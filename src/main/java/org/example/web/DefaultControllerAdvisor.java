package org.example.web;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class DefaultControllerAdvisor {
    @Pointcut("within(com.globepoint.web..*)")
    public void requestMethods() {

    }
    @Pointcut("within(com.globepoint.api.controller..*)")
    public void requestApiMethods() {

    }

    @Around("requestMethods()")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        log.info("==> {} with args: {}", methodName, Arrays.toString(args));
        try {
            Object result = joinPoint.proceed();
            log.info("=== {} result: {}", methodName, result);
            return result;
        }
        finally {
            log.info("<== {}", methodName);
        }
    }

    @Around("requestApiMethods()")
    public Object logApiMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        log.info("==> {} with args: {}", methodName, Arrays.toString(args));
        try {
            Object result = joinPoint.proceed();
            log.info("=== {} result: {}", methodName, result);
            return result;
        }
        finally {
            log.info("<== {}", methodName);
        }
    }
}
