package org.gisobject.certification.jse7.language.enhancement.literals.binary;

import org.gisobject.certification.jse7.utils.CompilerHelper;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Gregory on 12/03/2015.
 */
public class BinaryLiteralsTest {

    @Test
    public void testBinaryLiterals(){
        assertEquals(12,0b1100);
        assertEquals(0xb,0B1011);
        assertEquals(0xf,0b1111);
        assertEquals(0B000000001111,0b1111);
        assertEquals((int)Math.pow(2,30),0B01000000000000000000000000000000);
        assertEquals((int)-Math.pow(2,31),0B10000000000000000000000000000000);
        assertEquals((long)-Math.pow(2,31),0B10000000000000000000000000000000);
        assertEquals((long)Math.pow(2,31),0B10000000000000000000000000000000L);
        assertEquals(4,0B100L);
        //Add your own tests
    }

    @Test
    public void testImplicitCast() {
        double d = 4.0;
        assertTrue(d == 0b100);
        float f = 14.0F;
        assertTrue(f == 0B1110);
    }

    @Test
    public void testValidBinaryLongException() throws ClassNotFoundException {
        CompilerHelper.compile("public static long L = 0b100L;\n");
        //no exception
    }

    @Test/*(expected=IllegalStateException.class)*/
    public void testInvalidBinaryDoubleException() throws ClassNotFoundException {
        try {
            CompilerHelper.compile("double d = 0b100d;\n");
        } catch (IllegalStateException ise) {
            assertThat(ise.getMessage(),containsString("';' expected"));
        }
    }

    @Test/*(expected=IllegalStateException.class)*/
    public void testInvalidBinaryFloatException() throws ClassNotFoundException {
        try {
            CompilerHelper.compile("float f = 0b100f;\n");
        } catch (IllegalStateException ise) {
            assertThat(ise.getMessage(),containsString("';' expected"));
        }
    }
}
