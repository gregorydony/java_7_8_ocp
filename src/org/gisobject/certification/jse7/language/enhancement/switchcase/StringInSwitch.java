package org.gisobject.certification.jse7.language.enhancement.switchcase;

/**
 * <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/language/strings-switch.html"/>
 * Created by Gregory on 11/03/2015.
 */
public final class StringInSwitch {

    /**
     * <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/language/strings-switch.html">http://docs.oracle.com/javase/7/docs/technotes/guides/language/strings-switch.html</a>
     * @param dayOfWeekArg
     * @return typeOfDay
     */
    public static String getTypeOfDayWithSwitchStatement(String dayOfWeekArg) {
        String typeOfDay;
        switch (dayOfWeekArg) {
            case "Monday":
                typeOfDay = "Start of work week";
                break;
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
                typeOfDay = "Midweek";
                break;
            case "Friday":
                typeOfDay = "End of work week";
                break;
            case "Saturday":
            case "Sunday":
                typeOfDay = "Weekend";
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + dayOfWeekArg);
        }
        return typeOfDay;
    }
}
