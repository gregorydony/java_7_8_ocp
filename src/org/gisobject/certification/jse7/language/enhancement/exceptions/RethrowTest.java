package org.gisobject.certification.jse7.language.enhancement.exceptions;

/**
 * Created by Gregory on 24/03/2015.
 */
public class RethrowTest {

    private static class FirstException extends Exception {}
    private static class SecondException extends Exception {}

    public void rethrowException(String exceptionName) throws Exception {
        try {
            if (exceptionName.equals("First")) {
                throw new FirstException();
            } else {
                //Double d = Double.parseDouble(exceptionName);
                throw new SecondException();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void rethrowException2(String exceptionName) throws Exception {
        try {
            if (exceptionName.equals("First")) {
                throw new FirstException();
            } else {
                //Double d = Double.parseDouble(exceptionName);
                throw new SecondException();
            }
        } catch (Exception e) {
            FirstException ex = (FirstException)e;
            throw ex;
        }
    }
}
