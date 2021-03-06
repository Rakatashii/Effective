package com.revature.effective.cip.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** <pre>
 * Marks an Object Type, Constructor, Field, Local Variable, or Method as NOT Thread-Safe. 
 * 
 * To be used at the highest level possible.
 * 
 * Example:
 * 
 * {@link com.revature.effective.cip.annotation.NotThreadSafe} at Class-Level
 * &#064;NotThreadSafe
 * public class A {
 *     public void methA1() {
 *         // not thread-safe
 *     }
 *     public void methA2() {
 *         // not thread-safe
 *     }
 * }
 * 
 * vs.
 * 
 * public class B {
 *     {@link com.revature.effective.cip.annotation.ThreadSafe} at Method-Level
 *     &#064;ThreadSafe
 *     public void methB1() {
 *         // thread-safe
 *     }
 *     {@link com.revature.effective.cip.annotation.NotThreadSafe} at Method-Level
 *     &#064;NotThreadSafe
 *     public void methB2() {
 *         // not thread-safe
 *     }
 * } 
 * </pre>
 * @author Revature
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD})
public @interface NotThreadSafe {}
