package validate;

import java.lang.annotation.*;

@FunctionalInterface
public interface Checker
{
	public String check(Annotation instance, Object fieldValue, String fielName);
}