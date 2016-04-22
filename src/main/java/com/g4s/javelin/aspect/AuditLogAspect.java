package com.g4s.javelin.aspect;

import com.g4s.javelin.annotation.Loggable;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.dto.core.post.PostDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.logging.Logger;

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
     * Get all methods annotated with @Loggable.
     */
    @Pointcut("@annotation(com.g4s.javelin.annotation.Loggable))")
    public void getLoggableMethods() {
    }

    /**
     * Capture save action from the customer location service and record to the audit log.
     *
     * @param joinPoint {@link com.g4s.javelin.service.CustomerLocationService#saveCustomerLocationDetails(CustomerLocationDTO)}
     */
    @SuppressWarnings("all")
    @Around("getLoggableMethods() && args(customerLocation)")
    public Object captureSaveCustomerLocationDetailsAction(final ProceedingJoinPoint joinPoint,
                                                         final CustomerLocationDTO customerLocation) throws Throwable {
        LOGGER.info("Inside " + joinPoint.getSignature().getName());

        final Loggable loggable = getLoggableMethodAnnotation(joinPoint);
        LOGGER.info(loggable.objectType().getCode());

        //TODO: Add call to task queue once it is available

        Object retVal = joinPoint.proceed();
        return retVal;
    }

    /**
     * Capture save action from the customer location service and record to the audit log.
     *
     * @param joinPoint {@link com.g4s.javelin.service.location.CustomerLocationService#updateCustomerLocationStatus(Long, String)}
     */
    @SuppressWarnings("all")
    @Around("getLoggableMethods() && args(id, status)")
    public Object captureUpdateCustomerLocationStatusAction(final ProceedingJoinPoint joinPoint,
                                                          final Long id, final String status) throws Throwable{
        LOGGER.info("Inside " + joinPoint.getSignature().getName());

        final Loggable loggable = getLoggableMethodAnnotation(joinPoint);
        LOGGER.info(loggable.objectType().getCode());

        //TODO: Add call to task queue once it is available

        Object retVal = joinPoint.proceed();
        return retVal;
    }

    /**
     * Capture save action from the post service and record to the audit log.
     *
     * @param joinPoint {@link com.g4s.javelin.service.post.impl.PostServiceImpl#savePostDetails(PostDTO)}
     */
    @SuppressWarnings("all")
    @Around("getLoggableMethods() && args(post)")
    public Object captureSavePostDetailsAction(final ProceedingJoinPoint joinPoint, final PostDTO post) throws Throwable{
        LOGGER.info("Inside " + joinPoint.getSignature().getName());

        final Loggable loggable = getLoggableMethodAnnotation(joinPoint);
        LOGGER.info(loggable.objectType().getCode());

        //TODO: Add call to task queue once it is available

        Object retVal = joinPoint.proceed();
        return retVal;
    }

    /**
     * Retrieve {@link com.g4s.javelin.annotation.Loggable} annotation from annotated method
     *
     * @param joinPoint Methods annotated with {@link com.g4s.javelin.annotation.Loggable}
     * @return {@link com.g4s.javelin.annotation.Loggable} object
     */
    private Loggable getLoggableMethodAnnotation(final ProceedingJoinPoint joinPoint) {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final String methodName = methodSignature.getMethod().getName();
        final Class<?>[] parameterTypes = methodSignature.getMethod().getParameterTypes();

        Loggable loggable = null;

        try {
            loggable = joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes)
                    .getAnnotation(Loggable.class);
        } catch (final NoSuchMethodException e) {
            LOGGER.severe(e.getMessage());
        }

        return loggable;
    }
}
