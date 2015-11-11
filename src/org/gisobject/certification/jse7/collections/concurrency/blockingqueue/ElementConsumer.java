package org.gisobject.certification.jse7.collections.concurrency.blockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Gregory on 31/03/2015.
 */
public abstract class ElementConsumer<E extends BlockingQueue<Element>> implements Runnable {
    final E blockingQueue;
    final List<Element> consumedElement;
    int expectedNumberOfElements;

    public ElementConsumer(E blockingQueue) {
        this(blockingQueue, 1);
    }

    public ElementConsumer(E blockingQueue, int expectedNumberOfElements) {
        this.blockingQueue = blockingQueue;
        this.expectedNumberOfElements = expectedNumberOfElements;
        this.consumedElement = new ArrayList<Element>(expectedNumberOfElements);
    }

    public Element getLastConsumedElement() throws InterruptedException{
        return consumedElement.get(consumedElement.size()-1);
    }

    @Override
    public void run() {
        try {
            blockingQueue.poll();
            consumedElement.add(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
