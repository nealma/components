package com.nealma.framework.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by nealpc on 11/17/15.
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.nealma.account.rest.*.*(..))")
    public void logAround() {

    }

    @Around("logAround()")
    public Object logAroundBiz(ProceedingJoinPoint pjp) throws Throwable {
        debug("-------------------in-------------------");
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (Object object : pjp.getArgs()) {
            sb.append(object);
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        debug("[" + pjp.getTarget().getClass() + "] " + pjp.getSignature().getName() + "(" + sb + ")");

        Object result = pjp.proceed();
        debug("\n\n" + result + "\n");
        long end = System.currentTimeMillis();
        debug("[Cost Time] " + (end - start) + " ms");
        debug("-------------------out-------------------");
        return result;
    }

    private void debug(Object object) {
        LOGGER.debug("{}", object);
    }
}
