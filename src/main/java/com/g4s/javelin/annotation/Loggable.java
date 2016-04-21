package com.g4s.javelin.annotation;

import com.g4s.javelin.enums.ObjectTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jordan Duabe
 * @since 04/20/2016
 * <p/>
 * Custom annotation for methods that need to be captured for audit logs.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Loggable {
    ObjectTypeEnum objectType();
}
