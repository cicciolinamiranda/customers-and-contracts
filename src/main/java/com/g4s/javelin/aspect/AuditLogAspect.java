package com.g4s.javelin.aspect;

import com.g4s.javelin.annotation.Loggable;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.dto.core.audit.AuditLogDTO;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.dto.core.post.PostDTO;
import com.g4s.javelin.service.location.CustomerLocationService;
import com.g4s.javelin.service.post.PostService;
import com.g4s.javelin.util.AuditLogUtil;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.repackaged.org.codehaus.jackson.map.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Jordan Duabe
 * @since 04/12/2016
 * <p/>
 * AOP implementation for interception of service methods used in the creation of audit logs.
 */
//CSOFF: IllegalCatch
@Aspect
public class AuditLogAspect {

    private static final Logger LOGGER = Logger.getLogger(AuditLogAspect.class.getName());

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final String AUDIT_LOG_TASK_QUEUE_URL = "saveAuditLog";

    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.CUSTOMER_LOCATION_SERVICE)
    private CustomerLocationService customerLocationService;

    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.POST_SERVICE)
    private PostService postService;

    private Queue auditLogQueue;

    private AuditLogAspect() {
        auditLogQueue = QueueFactory.getQueue("audit-log-queue");
    }

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
    public void captureSaveCustomerLocationDetailsAction(final ProceedingJoinPoint joinPoint,
                                                         final CustomerLocationDTO customerLocation) {
        LOGGER.info("Inside " + joinPoint.getSignature().getName());

        final Loggable loggable = getLoggableMethodAnnotation(joinPoint);

        if (customerLocation.getId() != null) {
            final CustomerLocationDTO oldCustomerLocation = customerLocationService.getCustomerLocationDetails(
                    customerLocation.getId());

            try {
                final CustomerLocationDTO newCustomerLocation = (CustomerLocationDTO) joinPoint.proceed();
                final AuditLogDTO auditLog = AuditLogUtil.getOldAndNewValue(oldCustomerLocation, newCustomerLocation);
                auditLog.setObjectType(loggable.objectType());

                callAuditLogTaskQueue(auditLog);
            } catch (final Throwable e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    /**
     * Capture save action from the customer location service and record to the audit log.
     *
     * @param joinPoint {@link com.g4s.javelin.service.location.CustomerLocationService#updateCustomerLocationStatus(Long, String)}
     */
    @SuppressWarnings("all")
    @Around("getLoggableMethods() && args(id, status)")
    public void captureUpdateCustomerLocationStatusAction(final ProceedingJoinPoint joinPoint,
                                                          final Long id, final String status) {
        LOGGER.info("Inside " + joinPoint.getSignature().getName());

        final Loggable loggable = getLoggableMethodAnnotation(joinPoint);

        final CustomerLocationDTO oldCustomerLocation = customerLocationService.getCustomerLocationDetails(id);

        try {
            final CustomerLocationDTO newCustomerLocation = (CustomerLocationDTO) joinPoint.proceed();
            final AuditLogDTO auditLog = AuditLogUtil.getOldAndNewValue(oldCustomerLocation, newCustomerLocation);
            auditLog.setObjectType(loggable.objectType());

            callAuditLogTaskQueue(auditLog);
        } catch (final Throwable e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Capture save action from the post service and record to the audit log.
     *
     * @param joinPoint {@link com.g4s.javelin.service.post.impl.PostServiceImpl#savePostDetails(PostDTO)}
     */
    @SuppressWarnings("all")
    @Around("getLoggableMethods() && args(post)")
    public void captureSavePostDetailsAction(final ProceedingJoinPoint joinPoint, final PostDTO post) {
        LOGGER.info("Inside " + joinPoint.getSignature().getName());

        final Loggable loggable = getLoggableMethodAnnotation(joinPoint);

        if (post.getId() != null) {
            final PostDTO oldPost = postService.getPostDetails(post.getId());

            try {
                final PostDTO newPost = (PostDTO) joinPoint.proceed();
                final AuditLogDTO auditLog = AuditLogUtil.getOldAndNewValue(oldPost, newPost);
                auditLog.setObjectType(loggable.objectType());

                callAuditLogTaskQueue(auditLog);
            } catch (final Throwable e) {
                LOGGER.severe(e.getMessage());
            }
        }
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

    /**
     * Call the save audit log endpoint of the audit log queue
     *
     * @param auditLog Audit log details to be saved
     */
    private void callAuditLogTaskQueue(final AuditLogDTO auditLog) {
        try {
            auditLogQueue.add(TaskOptions.Builder.withUrl(AUDIT_LOG_TASK_QUEUE_URL).payload(OBJECT_MAPPER
                    .writeValueAsString(auditLog)));
        } catch (final IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
//CSON: IllegalCatch
