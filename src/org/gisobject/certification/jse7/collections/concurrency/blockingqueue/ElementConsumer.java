package org.gisobject.certification.jse7.collections.concurrency.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Elyse on 21/11/2015.
 */
public interface ElementConsumer<E extends BlockingQueue<Element>> {

    default Element consumeElementFrom(E  blockingQueue) throws InterruptedException {
        return blockingQueue.take();
    }

    Element getLastConsumedElement();
}
