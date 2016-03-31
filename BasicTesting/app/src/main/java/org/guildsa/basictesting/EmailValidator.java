package org.guildsa.basictesting;

import java.util.regex.Pattern;

/**
 * For the sake of discussing Android Testing, let's create a simple utility class that doesn't
 * require an actual Android device or simulator to be tested correctly. A class like this can be
 * tested from a regular "Unit Test".
 */
public class EmailValidator {

    /**
     * A RegEx validation pattern for an Email address.
     */
    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    /**
     * Validates if the given input is a valid email address or not.
     *
     * @param email The email to validate.
     * @return {@code true} if the input is a valid email. {@code false} otherwise.
     */
    public static boolean isValidEmail(CharSequence email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}
