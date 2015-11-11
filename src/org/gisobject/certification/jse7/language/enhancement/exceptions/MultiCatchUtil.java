package org.gisobject.certification.jse7.language.enhancement.exceptions;

import org.gisobject.certification.jse7.utils.CompilerHelper;

/**
 * Created by Gregory on 20/03/2015.
 */
public final class MultiCatchUtil {

    public static String validateDoubleValue(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException|NullPointerException e) {
            return e.getClass().getCanonicalName();
        }
        return s;
    }

    public static void compileMultiCatch(String expression, String multiCatch) throws ClassNotFoundException{
        String completeExpression = "public void method(){" +
                "try{"
                +expression+
                "}catch("+multiCatch+"){" +
                "}" +
                "}";
        CompilerHelper.compile(completeExpression);
    }

    private MultiCatchUtil() {
        throw new AssertionError("Non instatiable utility class");
    }
}
