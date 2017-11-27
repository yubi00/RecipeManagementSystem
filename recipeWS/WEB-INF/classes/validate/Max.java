package validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify the maximum value of the annotated field.
 * Created by Sameer Cooshna on 29/08/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Max
{
	// todo: task 1
	public double value();
	public boolean inclusive() default true;
}
