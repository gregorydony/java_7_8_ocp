package org.gisobject.certification.jse7.collections.concurrency.blockingqueue;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Elyse on 21/11/2015.
 */
public interface ElementProducer<E extends BlockingQueue<Element>> {

    default void offerElementsTo(E blockingQueue, List<Element> elementsToOffer) {
        for (Element e : elementsToOffer) {
            blockingQueue.offer(e);
        }
    }

}
