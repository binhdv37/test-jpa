package com.example.testjpa.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProfilerAspect {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value = "execution(public * com.example.testjpa.service.UserService+.*(..))")
    public void monitor() {}

    @Around("monitor()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        logger.warn("Enter " + pjp.getSignature());
        Object output = null;

        try {
            output = pjp.proceed();
        } catch (Throwable e) {
            logger.error("------- loi ------"); // ko bao gio di vao day ???
            throw e;
        } finally {
            logger.error("--- finaly ----");
            long elapsedTime = System.currentTimeMillis() - start;
            logger.warn(": Execution time: " + elapsedTime + " ms. ("+ elapsedTime/60000 + " minutes)");
        }

        return output;
    }
}
