package org.gisobject.certification.jse7.collections.concurrency.blockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Gregory on 31/03/2015.
 */
public abstract class ElementConsumerRunnable<E extends BlockingQueue<Element>> implements Runnable, ElementConsumer {
    final E blockingQueue;
    final List<Element> consumedElementsCache;

    public ElementConsumerRunnable(E blockingQueue) {
        this.blockingQueue = blockingQueue;
        this.consumedElementsCache = new ArrayList<>();
    }

    @Override
    public Element getLastConsumedElement() {
        if (consumedElementsCache.size() != 0) {
            return consumedElementsCache.get(consumedElementsCache.size() - 1);
        }
        return null;
    }

    @Override
    public void run() {
        try {
            consumedElementsCache.add(consumeElementFrom(blockingQueue));
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }
}
