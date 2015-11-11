package org.gisobject.certification.jse7.language.enhancement.literals.underscore;

import org.gisobject.certification.jse7.utils.CompilerHelper;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Gregory on 12/03/2015.
 */
public class UnderscoreInNumericTest {

    @Test
    public void testAllowedUnderscoreInInt() {
        assertEquals(123, 1_2_3);
        assertEquals(0xa1, 0xa_1);
        assertEquals(0b1001, 0b10_____________________________________________________________________________________________________01);
    }

    @Test
    public void testUnderscoreInVariable() {
        int i = 123;
        int _456 = 123;
        assertEquals(i, _456);
    }

    @Test
    public void testNotAllowedUnderscoreInInt() throws ClassNotFoundException {
        try {
            CompilerHelper.compile("int i = _123;");
            fail("Exception expected");
        } catch (IllegalStateException ise) {
            assertThat(ise.getMessage(), containsString("cannot find symbol"));
        }
        try {
            CompilerHelper.compile("int i = 123_;");
            fail("Exception expected");
        } catch (IllegalStateException ise) {
            assertThat(ise.getMessage(), containsString("illegal underscore"));
        }
        try {
            CompilerHelper.compile("int i = 0x_a1;");
            fail("Exception expected");
        } catch (IllegalStateException ise) {
            assertThat(ise.getMessage(), containsString("illegal underscore"));
        }
        try {
            CompilerHelper.compile("int i = 0xa1_;");
            fail("Exception expected");
        } catch (IllegalStateException ise) {
            assertThat(ise.getMessage(), containsString("illegal underscore"));
        }
        try {
            CompilerHelper.compile("int i = 0_xa1;");
            fail("Exception expected");
        } catch (IllegalStateException ise) {
            assertThat(ise.getMessage(), containsString("illegal underscore"));
        }
    }

    @Test
    public void testUnderscoreInLong() {
        assertEquals(123L, 1_2_3L);
        assertEquals(0xa1L, 0xa_1L);
        assertEquals(0b1001L, 0b10____01L);
    }

    @Test
    public void testNotAllowedUnderscoreInLong() throws ClassNotFoundException {
        try {
            CompilerHelper.compile("long l = 1234_L;");
            fail("Exception expected");
        } catch (IllegalStateException ise) {
            assertThat(ise.getMessage(), containsString("illegal underscore"));
        }
        try {
            CompilerHelper.compile("long l = 1234L_;");
            fail("Exception expected");
        } catch (IllegalStateException ise) {
            assertThat(ise.getMessage(), containsString("';' expected"));
        }
    }

    @Test
    public void testUnderscoreInDouble() {
        assertEquals(12.3, 1_2.3, 0);
        assertEquals(12.3456D, 12.3_4_5_____6D, 0);
        assertEquals(12.3d, 12.3__________________________________________________________0d, 0);
    }

    @Test
    public void testNotAllowedUnderscoreInDouble() throws ClassNotFoundException {
        try {
            CompilerHelper.compile("double d = 12_.34d;");
            fail("Exception expected");
        } catch (IllegalStateException ise) {
            assertThat(ise.getMessage(), containsString("illegal underscore"));
        }
        try {
            CompilerHelper.compile("double d = 12._34;");
            fail("Exception expected");
        } catch (IllegalStateException ise) {
            assertThat(ise.getMessage(), containsString("illegal underscore"));
        }
    }

    @Test
    public void testUnderscoreInFloat() {
        assertEquals(1211111111111111111111111111111111222222222222222222222222222222222.3, 1211111111111111111111111111111111____________________________222222222222222222222222222222222.3, 0);
        assertEquals(12.3456F, 12.3_4_5_____6F, 0);
        assertEquals(12.3f, 12.3__________________________________________________________0f, 0);
    }
}
