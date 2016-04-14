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
     * Save customer location service method pointcut
     */
    @Pointcut("execution(* com.g4s.javelin.service.location.CustomerLocationService.saveCustomerLocationDetails(..))")
    public void getSaveCustomerLocationDetailsPointcut() {
    }

    /**
     * Archive customer location service method pointcut
     */
    @Pointcut("execution(* com.g4s.javelin.service.location.CustomerLocationService.updateCustomerLocationStatus(..))")
    public void getUpdateCustomerLocationStatusPointcut() {
    }

    /**
     * Save post service method pointcut
     */
    @Pointcut("execution(* com.g4s.javelin.service.post.PostService.savePostDetails(..))")
    public void getSavePostDetailsPointcut() {
    }


    /**
     * Capture save action from the customer location service and record to the audit log.
     *
     * @param joinPoint CustomerDTO passed to the customer location service
     */
    @Before("getSaveCustomerLocationDetailsPointcut()")
    public void captureSaveCustomerLocationDetailsAction(final JoinPoint joinPoint) {
        //TODO: Replace with audit log implementation
        LOGGER.info("Hijacked save customer location details action: " + joinPoint.getArgs()[0].toString());
    }

    /**
     * Capture save action from the customer location service and record to the audit log.
     *
     * @param joinPoint CustomerDTO passed to the customer location service
     */
    @Before("getUpdateCustomerLocationStatusPointcut()")
    public void captureUpdateCustomerLocationStatusAction(final JoinPoint joinPoint) {
        //TODO: Replace with audit log implementation
        LOGGER.info("Hijacked archive customer location status action: " + joinPoint.getArgs()[0].toString());
    }

    /**
     * Capture save action from the post service and record to the audit log.
     *
     * @param joinPoint PostDTO passed to the customer location service
     */
    @Before("getSavePostDetailsPointcut()")
    public void captureSavePostDetailsAction(final JoinPoint joinPoint) {
        //TODO: Replace with audit log implementation
        LOGGER.info("Hijacked save post details action: " + joinPoint.getArgs()[0].toString());
    }
}
