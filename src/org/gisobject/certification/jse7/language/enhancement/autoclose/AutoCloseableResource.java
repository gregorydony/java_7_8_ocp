package org.gisobject.certification.jse7.language.enhancement.autoclose;

/**
 * Created by Gregory on 18/03/2015.
 */
public class AutoCloseableResource implements AutoCloseable {

    public static class InnerResource implements AutoCloseable {
        @Override
        public void close() throws Exception {
            throw new RuntimeException("Internal exception");
        }
    }

    private int id = 0;

    public AutoCloseableResource(int id) {
        this.id = id;
    }
    @Override
    public void close() throws Exception {
        try (InnerResource innerResource = new InnerResource()) {
            throw new RuntimeException("Exception in close " + id);
        }  
    }

    public void doTheJob() throws Exception {
        throw new RuntimeException("Unable to do the job");
    }

}
