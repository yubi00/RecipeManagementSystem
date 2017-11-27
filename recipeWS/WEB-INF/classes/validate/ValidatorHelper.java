package validate;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.*;

public class ValidatorHelper
{
	/*
	Generic method to validate all fields of a pojo (first paramter) that
	annotated with the given annotation type (second parameter) using the
	checker (third parameter).

	The checker is a functional interface. It checks a field value against
	an annotation and returns an error message if the field value is invalid.
	*/

	public static <T extends Annotation> List<String>
	validate(Object pojo, Class<T>  annotationType, Checker checker)
	{
		List<String> errorList = new ArrayList<String>();

		try
		{
			Field [] fields = pojo.getClass().getDeclaredFields();

			for(Field field: fields)
			{
				field.setAccessible(true);
				T annotationInstance = field.getAnnotation(annotationType);
				if(annotationInstance != null)
				{
					Object fieldValue = field.get(pojo);
					String fieldName = field.getName();
					String error =
						checker.check(annotationInstance, fieldValue, fieldName);

					if(error != null)
					{
						errorList.add(error);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return errorList;
	}
}