package com.example.myamazon.util;

import javax.validation.ValidationException;

/**
 * Collection of validation utils to be used for checking that arguments of
 * methods comply with specific expectations.
 *
 * <ul>
 * <li><b>throw*</b> methods should be used for internal checks. It means that
 * if an exception is thrown then there is a bug in the code.</li>
 *
 * <li><b>validate*</b> should be used for validation of parameters which come
 * from outside (like user input or external service invocation arguments).</li>
 * </ul>
 */
public class ValidationUtils {

    /**
     * Non instantiable class
     */
    private ValidationUtils() {
    }

    /**
     * Checks that the argument is not null.
     *
     * @param argument
     * @param argumentName
     * @throws IllegalArgumentException
     */
    public static void throwIfNull(Object argument, String argumentName) {
        if (argument == null) {
            throw new ValidationException(argumentName + " is null.");
        }
    }
}
