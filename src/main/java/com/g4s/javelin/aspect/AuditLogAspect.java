package com.g4s.javelin.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Jordan Duabe
 * @since 04/12/2016
 * <p/>
 * AOP implementation for interception of service methods used in the creation of audit logs.
 */
@Aspect
public class AuditLogAspect {

    private static final Logger LOGGER = Logger.getLogger(AuditLogAspect.class.getName());

    /**
     * Customer location service pointcut
     */
    @Pointcut("execution(* com.g4s.javelin.service.location.CustomerLocationService.saveCustomerLocationDetails(..))")
    public void getCustomerLocationServicePointcut() {
    }

    /**
     * Post service pointcut
     */
    @Pointcut("execution(* com.g4s.javelin.service.post.PostService.savePostDetails(..))")
    public void getPostServicePointcut() {
    }

    /**
     * Capture actions from the customer location service and record to the audit log.
     *
     * @param joinPoint CustomerDTO passed to the customer location service
     */
    @Before("getCustomerLocationServicePointcut()")
    public void captureCustomerLocationAction(final JoinPoint joinPoint) {
        //TODO: Replace with audit log implementation
        LOGGER.info("Hijacked customer location action: " + joinPoint.getArgs()[0].toString());
    }

    /**
     * Capture actions from the post service and record to the audit log.
     *
     * @param joinPoint PostDTO passed to the customer location service
     */
    @Before("getPostServicePointcut()")
    public void capturePostAction(final JoinPoint joinPoint) {
        //TODO: Replace with audit log implementation
        LOGGER.info("Hijacked post action: " + joinPoint.getArgs()[0].toString());
    }
}
