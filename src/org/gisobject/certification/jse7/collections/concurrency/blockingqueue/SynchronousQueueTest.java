package org.gisobject.certification.jse7.collections.concurrency.blockingqueue;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNull;

/**
 * Created by Gregory on 27/03/2015.
 */
public final class SynchronousQueueTest {

    private static class SynchronousElementConsumer extends ElementConsumerRunnable<SynchronousQueue<Element>> {
        private final int timeoutInMillis;

        public SynchronousElementConsumer(SynchronousQueue<Element> blockingQueue) {
            super(blockingQueue);
            this.timeoutInMillis = 0;
        }

        public SynchronousElementConsumer(SynchronousQueue<Element> blockingQueue, int timeoutInMillis) {
            super(blockingQueue);
            this.timeoutInMillis = timeoutInMillis;
        }

        @Override
        public Element consumeElementFrom(BlockingQueue blockingQueue) throws InterruptedException {
            if (0 == timeoutInMillis) {
                return ((SynchronousQueue<Element>) blockingQueue).take();
            } else {
                return ((SynchronousQueue<Element>) blockingQueue).poll(timeoutInMillis, TimeUnit.MILLISECONDS);
            }
        }
    }

    private static class SynchronousElementProducer extends ElementProducerRunnable<SynchronousQueue<Element>> {
        public SynchronousElementProducer(SynchronousQueue<Element> blockingQueue) {
            super(blockingQueue);
        }

        /*
        public void offerElementsTo(SynchronousQueue<Element> blockingQueue, List<Element> elementsToOffer) {
            try {
                for (Element e : elementsToOffer) {
                    blockingQueue.offer(e);
                    blockingQueue.offer(e, 200, TimeUnit.MILLISECONDS);
                }
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            }
        }
        */
    }

    @Test
    public void testSuccessfulTake() throws InterruptedException {
        SynchronousQueue<Element> synchronousQueue = new SynchronousQueue<Element>();
        ElementConsumerRunnable consumer = new SynchronousElementConsumer(synchronousQueue,1000);
        Thread t1 = new Thread(consumer);
        t1.start();
        Thread.sleep(50);
        assertNull(consumer.getLastConsumedElement());
        ElementProducerRunnable producer = new SynchronousElementProducer(synchronousQueue);
        Thread t2 = new Thread(producer);
        t2.start();
        Thread.sleep(50);
        assertThat(consumer.getLastConsumedElement(), is(equalTo(producer.getOfferedElements().get(0))));
    }

    @Test
    public void testTimeoutTake() throws InterruptedException {
        SynchronousQueue<Element> synchronousQueue = new SynchronousQueue<Element>();
        ElementConsumerRunnable consumer = new SynchronousElementConsumer(synchronousQueue,10);
        Thread t1 = new Thread(consumer);
        t1.start();
        Thread.sleep(50);
        assertNull(consumer.getLastConsumedElement());
        ElementProducerRunnable producer = new SynchronousElementProducer(synchronousQueue);
        Thread t2 = new Thread(producer);
        t2.start();
        Thread.sleep(50);
        assertNull(consumer.getLastConsumedElement());
    }

    /**
     * In this test
     * @throws InterruptedException
     */
    @Test
    public void testTakeBeforeOffer() throws InterruptedException {
        SynchronousQueue<Element> synchronousQueue = new SynchronousQueue<Element>();
        ElementProducerRunnable producer = new SynchronousElementProducer(synchronousQueue);
        Thread t2 = new Thread(producer);
        t2.start();
        Thread.sleep(100);
        ElementConsumerRunnable consumer = new SynchronousElementConsumer(synchronousQueue,100);
        Thread t1 = new Thread(consumer);
        t1.start();
        Thread.sleep(50);
        assertNull(consumer.getLastConsumedElement());
    }

}
