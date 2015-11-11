package org.gisobject.certification.jse7.language.enhancement.autoclose;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Gregory on 17/03/2015.
 */
public final class BufferedReaderUtil {

    public static void forceIOException() throws IOException {
        try (BufferedReader br2 = new BufferedReader(new FileReader("bar.txt"))) {
            if (0==0) throw new IOException("Forced exception");
        }
    }

    public static void forceIOAndSuppressedException() throws IOException {
        try (BufferedReader br2 = new BufferedReader(new FileReader("bar.txt")) {
            @Override
            public void close() throws IOException {
                throw new IOException("Suppressed exception");
            }
        }) {
            if (0==0) throw new IOException("Forced exception");
        }
    }
}
