package org.gisobject.certification.jse7.language.enhancement.exceptions;

import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Gregory on 20/03/2015.
 */
public final class MultiCatchTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testNullPointerException() {
        String s = MultiCatchUtil.validateDoubleValue(null);
        assertThat(s, is(equalTo("java.lang.NullPointerException")));
    }

    @Test
    public void testNumberFormatException() {
        String s = MultiCatchUtil.validateDoubleValue("1.2r");
        assertThat(s, is(equalTo("java.lang.NumberFormatException")));
    }

    @Test
    public void testNoException() {
        String s = MultiCatchUtil.validateDoubleValue("1.2");
        assertThat(s, is(equalTo("1.2")));
    }

    @Test
    public void testCompileValidMultiCatch() throws ClassNotFoundException {
        MultiCatchUtil.compileMultiCatch("Double.parseDouble(\"1\");","NullPointerException|NumberFormatException e");
    }

    @Test
    public void testCompileInvalidMultiCatch() throws ClassNotFoundException {
        expectedEx.expect(IllegalStateException.class);
        expectedEx.expectMessage(StringContains.containsString("Alternatives in a multi-catch statement cannot be related by subclassing"));
        MultiCatchUtil.compileMultiCatch("Double.parseDouble(\"1\");", "IllegalArgumentException|NumberFormatException e");
    }
}
