package com.revature.effective.cip.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** <pre>
 * Marks an Object Type, Constructor, Field, Local Variable, or Method as Immutable. 
 * </pre>
 * @author Revature
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
public @interface Immutable {}
