package org.gisobject.certification.jse7.collections.concurrency.blockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Gregory on 31/03/2015.
 */
public abstract class ElementProducer<E extends BlockingQueue<Element>> implements Runnable {
    private final E blockingQueue;

    private int quantity = 1;
    private final List<Element> elements = new ArrayList<Element>();

    public ElementProducer(E blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public ElementProducer(E blockingQueue, int quantity) {
        this.blockingQueue = blockingQueue;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            Element e = new Element("element" + (int) (Math.random() * 1000));
            elements.add(e);
            blockingQueue.offer(e);
        }
    }

    public List<Element> getOfferedElements() {
        return elements;
    }
}
