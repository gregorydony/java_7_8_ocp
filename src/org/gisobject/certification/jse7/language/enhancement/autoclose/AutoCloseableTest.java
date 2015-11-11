package org.gisobject.certification.jse7.language.enhancement.autoclose;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Gregory on 19/03/2015.
 */
public final class AutoCloseableTest {

    private void tryAutoCloseableResource(int id) throws Exception {
        try (AutoCloseableResource autoCloseableResource = new AutoCloseableResource(id)) {
            autoCloseableResource.doTheJob();
        }
    }

    private void tryAutoCloseableResourceCascade(int id, int innerId) throws Exception {
        try (AutoCloseableResource autoCloseableResource = new AutoCloseableResource(id)) {
            tryAutoCloseableResource(innerId);
        }
    }

    @Test
    public void testDoTheJob() {
        int id = getRandomId();
        try {
            tryAutoCloseableResource(id);
        } catch (Exception e) {
            assertThat(e.getMessage(), is(equalTo("Unable to do the job")));
            assertThat(e.getSuppressed().length, is(equalTo(1)));
            assertThat(e.getSuppressed()[0].getMessage(), is(equalTo("Exception in close " + id)));
            assertThat(e.getSuppressed()[0].getSuppressed().length, is(equalTo(1)));
            assertThat(e.getSuppressed()[0].getSuppressed()[0].getMessage(), is(equalTo("Internal exception")));
        }
    }

    @Test
    public void testDoTheJobCascade() {
        int id = getRandomId();
        int innerId = getRandomId();
        try {
            tryAutoCloseableResourceCascade(id, innerId);
        } catch (Exception e) {
            assertThat(e.getMessage(), is(equalTo("Unable to do the job")));
            assertThat(e.getSuppressed().length, is(equalTo(2)));
            assertThat(e.getSuppressed()[0].getMessage(), is(equalTo("Exception in close " + innerId)));
            assertThat(e.getSuppressed()[1].getMessage(), is(equalTo("Exception in close " + id)));
        }
    }

    private int getRandomId() {
        return (int)(Math.random() * 100000);
    }
}
