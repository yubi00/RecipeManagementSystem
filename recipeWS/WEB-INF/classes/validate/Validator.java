package validate;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.*;

public class Validator
{
	public static void validate(Object pojo) throws ValidationException
	{
		List<String> errorList = new ArrayList<String>();

		errorList.addAll(validateMin(pojo));
		errorList.addAll(validateRequired(pojo));
		errorList.addAll(validateMax(pojo));
		errorList.addAll(validateMinLength(pojo));
		errorList.addAll(validateMaxLength(pojo));

		// Throw exception if there are errors
		if (errorList.size() > 0)
		{
			// Convert errorList into a string
			String errors = "";
			for(String message: errorList)
			{
				errors = errors + message + "\n";
			}

			throw new ValidationException(
				"Number of errors: " + errorList.size() + "\n"
				+ errors);
		}
	}

	/*
	Min
	To validate all the fields of a  pojo that are annotated
	with the Min annotation.
	*/
	public static List<String> validateMin(Object pojo)
	{
		List<String> errors = ValidatorHelper.validate(
			pojo,
			Min.class,
			(a, fv, fn)-> checkMin(a, fv, fn));

		return errors;
	}

	public static String checkMin(Annotation annotation, Object fieldValue,
	String fieldName)
	{
		String error = null;
		Min min = (Min) annotation;
		Double value = new Double(fieldValue.toString());

		boolean valid = min.inclusive()? value >= min.value(): value > min.value();
		if(!valid)
		{
			error = value + " for " + fieldName + " is too low";
		}

		return error;
	}

	/*
	Required
	To validate all the fields of a  pojo that are annotated
	with the Required annotation.
	*/
	public static List<String> validateRequired(Object pojo)
	{
		List<String> errors = ValidatorHelper.validate(
			pojo,
			Required.class,
			(a, fv, fn)-> checkRequired(a, fv, fn));

		return errors;
	}

	public static String checkRequired(Annotation annotation, Object fieldValue,
	String fieldName)
	{
		String error = null;

		boolean valid = fieldValue != null;
		if(!valid)
		{
			error = fieldName + " must not be null";
		}

		return error;
	}

	/*
	Max
	To validate all the fields of a  pojo that are annotated
	with the Max annotation.
	*/
	public static List<String> validateMax(Object pojo)
	{
		List<String> errors = ValidatorHelper.validate(
			pojo,
			Max.class,
			(a, fv, fn)-> checkMax(a, fv, fn));

		return errors;
	}

	public static String checkMax(Annotation annotation, Object fieldValue,
	String fieldName)
	{
		String error = null;
		Max max= (Max) annotation;
		Double value = new Double(fieldValue.toString());

		boolean valid = max.inclusive()? value <= max.value(): value < max.value();
		if(!valid)
		{
			error = value + " for " + fieldName + " is too high";
		}

		return error;
	}

	/*
	MinLength
	To validate all the fields of a  pojo that are annotated
	with the MinLength annotation.
	*/
	public static List<String> validateMinLength(Object pojo)
	{
		List<String> errors = ValidatorHelper.validate(
			pojo,
			MinLength.class,
			(a, fv, fn)-> checkMinLength(a, fv, fn));

		return errors;
	}

	public static String checkMinLength(Annotation annotation, Object fieldValue,
	String fieldName)
	{
		String error = null;
		MinLength minLength = (MinLength) annotation;
		String value = fieldValue != null ? fieldValue.toString(): null;

		boolean valid = value != null && value.length() >= minLength.value();

		if(!valid)
		{
			error = value + " for " + fieldName + " is too short";
		}

		return error;
	}

	/*
	MaxLength
	To validate all the fields of a  pojo that are annotated
	with the MaxLength annotation.
	*/
	public static List<String> validateMaxLength(Object pojo)
	{
		List<String> errors = ValidatorHelper.validate(
			pojo,
			MaxLength.class,
			(a, fv, fn)-> checkMaxLength(a, fv, fn));

		return errors;
	}

	public static String checkMaxLength(Annotation annotation, Object fieldValue,
	String fieldName)
	{
		String error = null;
		MaxLength maxLength = (MaxLength) annotation;
		String value = fieldValue != null ? fieldValue.toString(): null;

		boolean valid = value != null && value.length() <= maxLength.value();

		if(!valid)
		{
			error = value + " for " + fieldName + " is too long";
		}

		return error;
	}

}