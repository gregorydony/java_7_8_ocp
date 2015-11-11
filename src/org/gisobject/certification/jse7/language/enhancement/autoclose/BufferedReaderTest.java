package org.gisobject.certification.jse7.language.enhancement.autoclose;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Gregory on 14/03/2015.
 */
public final class BufferedReaderTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testAutoCloseBufferedReader() throws IOException {
        expectedEx.expect(IOException.class);
        expectedEx.expectMessage("Forced exception");
        BufferedReaderUtil.forceIOException();
    }

    @Test
    public void testAutoCloseBufferedReaderWithSuppressedException() throws IOException {
        expectedEx.expect(IOException.class);
        expectedEx.expectMessage("Forced exception");
        BufferedReaderUtil.forceIOAndSuppressedException();
    }

    @Test
    public void testAutoCloseBufferedReaderAndGetSuppressedException() throws IOException {
        try {
            BufferedReaderUtil.forceIOAndSuppressedException();
        } catch (IOException ioe) {
            assertThat(1, is(equalTo(ioe.getSuppressed().length)));
            assertThat("Suppressed exception", is(equalTo(ioe.getSuppressed()[0].getMessage())));
            assertThat("Forced exception", is(equalTo(ioe.getMessage())));
        }
    }

    @Test
    public void testBufferedReaderWithoutResources() throws IOException {
        BufferedReader br1 = null;
        try {
            BufferedReader br2 = new BufferedReader(new FileReader("bar.txt"));
            br1 = br2;
            throw new IOException("Forced exception");
        } catch (IOException ioe) {
            //No exception as the br1 is not (auto-)closed
            br1.readLine();
        } finally {
            br1.close();
        }
    }
}
