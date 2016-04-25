package com.g4s.javelin.aspect;

import java.io.IOException;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.annotation.Loggable;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.dto.core.audit.AuditLogDTO;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.dto.core.post.PostDTO;
import com.g4s.javelin.service.location.CustomerLocationService;
import com.g4s.javelin.service.post.PostService;
import com.g4s.javelin.taskqueue.worker.AuditLogTaskWorker;
import com.g4s.javelin.util.AuditLogUtil;

/**
 * @author Jordan Duabe
 * @since 04/12/2016
 *        <p/>
 *        AOP implementation for interception of service methods used in the
 *        creation of audit logs.
 */
// CSOFF: IllegalCatch
@Aspect
public class AuditLogAspect {

    private static final Logger LOGGER = Logger.getLogger(AuditLogAspect.class
            .getName());

    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.CUSTOMER_LOCATION_SERVICE)
    private CustomerLocationService customerLocationService;

    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.POST_SERVICE)
    private PostService postService;

    private AuditLogTaskWorker auditLogTaskWorker;

    public AuditLogAspect() {
        auditLogTaskWorker = new AuditLogTaskWorker();
    }

    /**
     * Get all methods annotated with @Loggable.
     */
    @Pointcut("@annotation(com.g4s.javelin.annotation.Loggable))")
    public void getLoggableMethods() {
    }

    /**
     * Capture save action from the customer location service and record to the
     * audit log.
     *
     * @param joinPoint
     *            {@link com.g4s.javelin.service.CustomerLocationService#saveCustomerLocationDetails(CustomerLocationDTO)}
     * @throws IOException
     */
    @SuppressWarnings("all")
    @Around("getLoggableMethods() && args(customerLocation)")
    public Object captureSaveCustomerLocationDetailsAction(
            final ProceedingJoinPoint joinPoint,
            final CustomerLocationDTO customerLocation) throws IOException {
        LOGGER.info("Inside " + joinPoint.getSignature().getName());

        final Loggable loggable = getLoggableMethodAnnotation(joinPoint);

        CustomerLocationDTO newCustomerLocation = null;
        CustomerLocationDTO oldCustomerLocation = null;

        if (customerLocation.getId() != null) {
            oldCustomerLocation = customerLocationService
                    .getCustomerLocationDetails(customerLocation.getId());
        }

        try {
            newCustomerLocation = (CustomerLocationDTO) joinPoint.proceed();
        } catch (final Throwable e) {
            LOGGER.severe(e.getMessage());
        }

        final AuditLogDTO auditLog = AuditLogUtil.getOldAndNewValue(oldCustomerLocation, newCustomerLocation);
        auditLog.setObjectType(loggable.objectType().getCode());
        auditLog.setAction(loggable.action().getCode());
        auditLog.setReason(customerLocation.getReasonForChange());
        auditLog.setObject_id(String.valueOf(newCustomerLocation.getId()));
        auditLog.setIp_address(customerLocation.getIpAddress());

        auditLogTaskWorker.saveLog(auditLog);


        return newCustomerLocation;
    }

    /**
     * Capture save action from the customer location service and record to the
     * audit log.
     *
     * @param joinPoint
     *    {@link com.g4s.javelin.service.location.CustomerLocationService#updateCustomerLocationStatus(Long, String, String, String)}
     */
    @SuppressWarnings("all")
    @Around("getLoggableMethods() && args(id, status, reasonForChange, ipAddress)")
    public Object captureUpdateCustomerLocationStatusAction(
            final ProceedingJoinPoint joinPoint, final Long id,
            final String status, final String reasonForChange, final String ipAddress) {
        LOGGER.info("Inside " + joinPoint.getSignature().getName());

        final Loggable loggable = getLoggableMethodAnnotation(joinPoint);

        final CustomerLocationDTO oldCustomerLocation = customerLocationService
                .getCustomerLocationDetails(id);
        CustomerLocationDTO newCustomerLocation = null;

        try {
            newCustomerLocation = (CustomerLocationDTO) joinPoint.proceed();
            LOGGER.info(oldCustomerLocation.getStatusStr() + " OLD < > NEW "
                    + newCustomerLocation.getStatusStr());
            final AuditLogDTO auditLog = AuditLogUtil.getOldAndNewValue(
                    oldCustomerLocation, newCustomerLocation);
            auditLog.setReason(reasonForChange);
            auditLog.setIp_address(ipAddress);
            auditLog.setObject_id(String.valueOf(newCustomerLocation.getId()));
            auditLog.setAction(loggable.action().getCode());
            auditLog.setObjectType(loggable.objectType().getCode());

            auditLogTaskWorker.saveLog(auditLog);
        } catch (final Throwable e) {
            LOGGER.severe(e.getClass() + " " + e.getMessage());
        }

        return newCustomerLocation;
    }

    /**
     * Capture save action from the post service and record to the audit log.
     *
     * @param joinPoint
     *            {@link com.g4s.javelin.service.post.impl.PostServiceImpl#savePostDetails(PostDTO)}
     * @throws IOException
     */
    @SuppressWarnings("all")
    @Around("getLoggableMethods() && args(post)")
    public Object captureSavePostDetailsAction(
            final ProceedingJoinPoint joinPoint, final PostDTO post)
            throws IOException {
        LOGGER.info("Inside " + joinPoint.getSignature().getName());
        final Loggable loggable = getLoggableMethodAnnotation(joinPoint);

        PostDTO newPost = null;
        PostDTO oldPost = null;
        if (post.getId() != null) {
            LOGGER.info("ID " + post.getId());
            oldPost = postService.getPostDetails(post.getId());
        }

        try {
            newPost = (PostDTO) joinPoint.proceed();
            LOGGER.info("NEW ID" + newPost.getId());
        } catch (final Throwable e) {
            LOGGER.severe(e.getMessage());
        }

        final AuditLogDTO auditLog = AuditLogUtil.getOldAndNewValue(oldPost, newPost);
        auditLog.setObjectType(loggable.objectType().getCode());
        auditLog.setAction(loggable.action().getCode());
        auditLog.setReason(post.getReasonForChange());
        auditLog.setObject_id(String.valueOf(newPost.getId()));
        auditLog.setIp_address(post.getIpAddress());

        auditLogTaskWorker.saveLog(auditLog);

        return newPost;
    }

    /**
     * Retrieve {@link com.g4s.javelin.annotation.Loggable} annotation from
     * annotated method
     *
     * @param joinPoint
     *            Methods annotated with
     *            {@link com.g4s.javelin.annotation.Loggable}
     * @return {@link com.g4s.javelin.annotation.Loggable} object
     */
    private Loggable getLoggableMethodAnnotation(
            final ProceedingJoinPoint joinPoint) {
        final MethodSignature methodSignature = (MethodSignature) joinPoint
                .getSignature();
        final String methodName = methodSignature.getMethod().getName();
        final Class<?>[] parameterTypes = methodSignature.getMethod()
                .getParameterTypes();

        Loggable loggable = null;

        try {
            loggable = joinPoint.getTarget().getClass()
                    .getMethod(methodName, parameterTypes)
                    .getAnnotation(Loggable.class);
        } catch (final NoSuchMethodException e) {
            LOGGER.severe(e.getMessage());
        }

        return loggable;
    }
}
// CSON: IllegalCatch
