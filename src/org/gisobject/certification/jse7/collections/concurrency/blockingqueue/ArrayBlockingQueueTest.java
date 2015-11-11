package org.gisobject.certification.jse7.collections.concurrency.blockingqueue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNull;

/**
 * Created by Gregory on 03/04/2015.
 */
public class ArrayBlockingQueueTest {

    private static class ArrayBlockingElementConsumer extends ElementConsumer<ArrayBlockingQueue<Element>> {
        private final int timeoutInMillis;
        public ArrayBlockingElementConsumer(ArrayBlockingQueue<Element> blockingQueue, int timeoutInMillis) {
            super(blockingQueue);
            this.timeoutInMillis = timeoutInMillis;
        }
    }

    private static class ArrayBlockingElementProducer extends ElementProducer<ArrayBlockingQueue<Element>> {
        public ArrayBlockingElementProducer(ArrayBlockingQueue<Element> blockingQueue, int quantity) {
            super(blockingQueue, quantity);
        }
    }

    @Test
    public void testSuccessfulPoll() throws InterruptedException {
        ArrayBlockingQueue<Element> arrayBlockingQueue = new ArrayBlockingQueue<Element>(5);
        ElementConsumer consumer = new ArrayBlockingElementConsumer(arrayBlockingQueue,100);
        Thread t1 = new Thread(consumer);
        t1.start();
        Thread.sleep(50);
        assertNull(consumer.getLastConsumedElement());
        ElementProducer producer = new ArrayBlockingElementProducer(arrayBlockingQueue,5);
        Thread t2 = new Thread(producer);
        t2.start();
        Thread.sleep(50);
        assertThat(consumer.getLastConsumedElement(), is(equalTo(producer.getOfferedElements().get(0))));
    }

}
