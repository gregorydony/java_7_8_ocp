package org.gisobject.certification.jse7.collections.concurrency.blockingqueue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNull;

/**
 * Created by GISObject on 03/04/2015.
 */
public final class ArrayBlockingQueueTest {

    private static class ArrayBlockingElementConsumer extends ElementConsumerRunnable<ArrayBlockingQueue<Element>> {
        private final int timeoutInMillis;
        public ArrayBlockingElementConsumer(ArrayBlockingQueue<Element> blockingQueue, int timeoutInMillis) {
            super(blockingQueue);
            this.timeoutInMillis = timeoutInMillis;
        }
    }

    private static class ArrayBlockingElementProducer extends ElementProducerRunnable<ArrayBlockingQueue<Element>> {
        public ArrayBlockingElementProducer(ArrayBlockingQueue<Element> blockingQueue, int quantity) {
            super(blockingQueue, quantity);
        }
    }

    /**
     *
     * @throws InterruptedException
     */
    @Test
    public void testSuccessfulTake() throws InterruptedException {
        ArrayBlockingQueue<Element> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        ElementConsumerRunnable consumer = new ArrayBlockingElementConsumer(arrayBlockingQueue,100);
        Thread t1 = new Thread(consumer);
        t1.start();
        Thread.sleep(50);
        assertNull(consumer.getLastConsumedElement());
        ElementProducerRunnable producer = new ArrayBlockingElementProducer(arrayBlockingQueue,5);
        Thread t2 = new Thread(producer);
        t2.start();
        Thread.sleep(50);
        assertThat(consumer.getLastConsumedElement(), is(equalTo(producer.getOfferedElements().get(0))));
    }

}
