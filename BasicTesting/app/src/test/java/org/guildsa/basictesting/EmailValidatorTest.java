package org.guildsa.basictesting;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the EmailValidator logic.
 *
 * http://googletesting.blogspot.com/2010/12/test-sizes.html
 *
 * Small:  This test doesn't interact with any file system or network.
 * Medium: Accesses file systems on box which is running tests.
 * Large:  Accesses external file systems, networks, etc.
 */
@SmallTest
public class EmailValidatorTest {

    @Test
    public void emailValidator_correctEmailSimple_returnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.com"));
    }

    @Test
    public void emailValidator_correctEmailSubDomain_returnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.co.uk"));
    }

    @Test
    public void emailValidator_invalidEmailNoTopLevelDomain_returnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email"));
    }

    @Test
    public void emailValidator_invalidEmailDoubleDot_returnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email..com"));
    }

    @Test
    public void emailValidator_invalidEmailNoUsername_returnsFalse() {
        assertFalse(EmailValidator.isValidEmail("@email.com"));
    }

    @Test
    public void emailValidator_emptyString_returnsFalse() {
        assertFalse(EmailValidator.isValidEmail(""));
    }

    @Test
    public void emailValidator_nullEmail_returnsFalse() {
        assertFalse(EmailValidator.isValidEmail(null));
    }
}
