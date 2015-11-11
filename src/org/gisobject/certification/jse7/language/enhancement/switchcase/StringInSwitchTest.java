package org.gisobject.certification.jse7.language.enhancement.switchcase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Gregory on 11/03/2015.
 */
public class StringInSwitchTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testTypeOfDayWithSwitchStatementMonday() {
        assertThat(StringInSwitch.getTypeOfDayWithSwitchStatement("Monday"), is(equalTo("Start of work week")));
    }

    @Test
    public void testTypeOfDayWithSwitchStatementFriday() {
        assertThat(StringInSwitch.getTypeOfDayWithSwitchStatement("Friday"), is(equalTo("End of work week")));
    }

    @Test
    public void testTypeOfDayWithSwitchStatementWednesday() {
        assertThat(StringInSwitch.getTypeOfDayWithSwitchStatement("Friday"), is(equalTo("End of work week")));
    }

    @Test
    public void testTypeOfDayWithSwitchStatementFridayWrongCase() {
        exception.expect(IllegalArgumentException.class);
        StringInSwitch.getTypeOfDayWithSwitchStatement("friday");
    }

    @Test
    public void testTypeOfDayWithSwitchStatementFarfaday() {
        exception.expect(IllegalArgumentException.class);
        StringInSwitch.getTypeOfDayWithSwitchStatement("Farfaday");
    }

    @Test
    public void testTypeOfDayWithSwitchStatementNull() {
        exception.expect(NullPointerException.class);
        StringInSwitch.getTypeOfDayWithSwitchStatement(null);
    }

}
