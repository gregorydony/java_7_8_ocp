package org.gisobject.certification.jse7.collections.concurrency.blockingqueue;

import org.junit.Test;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by Gregory on 27/03/2015.
 */
public class SynchronousQueueTest {

    private static class SynchronousElementConsumer extends ElementConsumer<SynchronousQueue<Element>> {
        private final int timeoutInMillis;
        public SynchronousElementConsumer(SynchronousQueue<Element> blockingQueue, int timeoutInMillis) {
            super(blockingQueue);
            this.timeoutInMillis = timeoutInMillis;
        }
    }

    private static class SynchronousElementProducer extends ElementProducer<SynchronousQueue<Element>> {
        public SynchronousElementProducer(SynchronousQueue<Element> blockingQueue) {
            super(blockingQueue);
        }
    }

    @Test
    public void testSuccessfulPoll() throws InterruptedException {
        SynchronousQueue<Element> synchronousQueue = new SynchronousQueue<Element>();
        ElementConsumer consumer = new SynchronousElementConsumer(synchronousQueue,100);
        Thread t1 = new Thread(consumer);
        t1.start();
        Thread.sleep(50);
        assertNull(consumer.getLastConsumedElement());
        ElementProducer producer = new SynchronousElementProducer(synchronousQueue);
        Thread t2 = new Thread(producer);
        t2.start();
        Thread.sleep(50);
        assertThat(consumer.getLastConsumedElement(), is(equalTo(producer.getOfferedElements().get(0))));
    }

    @Test
    public void testTimeoutPoll() throws InterruptedException {
        SynchronousQueue<Element> synchronousQueue = new SynchronousQueue<Element>();
        ElementConsumer consumer = new SynchronousElementConsumer(synchronousQueue,100);
        Thread t1 = new Thread(consumer);
        t1.start();
        Thread.sleep(200);
        assertNull(consumer.getLastConsumedElement());
        ElementProducer producer = new SynchronousElementProducer(synchronousQueue);
        Thread t2 = new Thread(producer);
        t2.start();
        Thread.sleep(500);
        assertNotNull(producer.getOfferedElements().get(0));
        assertNull(consumer.getLastConsumedElement());
    }

    @Test
    public void testPollBeforeOffer() throws InterruptedException {
        SynchronousQueue<Element> synchronousQueue = new SynchronousQueue<Element>();
        ElementProducer producer = new SynchronousElementProducer(synchronousQueue);
        Thread t2 = new Thread(producer);
        t2.start();
        Thread.sleep(100);
        ElementConsumer consumer = new SynchronousElementConsumer(synchronousQueue,100);
        Thread t1 = new Thread(consumer);
        t1.start();
        Thread.sleep(50);
        assertNull(consumer.getLastConsumedElement());
    }

}
