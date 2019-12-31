package com.revature.effective.cip.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** <pre>
 * References the lock that guard's the monitor of a local-object or field.
 * </pre>
 * @author Revature
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD, ElementType.LOCAL_VARIABLE})
public @interface GuardedBy {
	String lock() default "Ambiguous Lock";
}
