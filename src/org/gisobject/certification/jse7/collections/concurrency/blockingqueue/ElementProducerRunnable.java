package org.gisobject.certification.jse7.collections.concurrency.blockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Gregory on 31/03/2015.
 */
public abstract class ElementProducerRunnable<E extends BlockingQueue<Element>> implements Runnable, ElementProducer {
    private final E blockingQueue;

    private int quantity = 1;
    private final List<Element> elements = new ArrayList<>();

    public ElementProducerRunnable(E blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public ElementProducerRunnable(E blockingQueue, int quantity) {
        this.blockingQueue = blockingQueue;
        this.quantity = quantity;
    }



    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            Element e = new Element("element" + (int) (Math.random() * 1000));
            elements.add(e);
        }
        offerElementsTo(blockingQueue, elements);
    }

    public List<Element> getOfferedElements() {
        return elements;
    }
}
