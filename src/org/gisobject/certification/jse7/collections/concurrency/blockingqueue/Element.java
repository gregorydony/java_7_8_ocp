package org.gisobject.certification.jse7.collections.concurrency.blockingqueue;

/**
 * Created by Gregory on 27/03/2015.
 */
public class Element {

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Element(){}

    public Element(String state){
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Element element = (Element) o;

        if (state != null ? !state.equals(element.state) : element.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return state != null ? state.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Element{" +
                "state='" + state + '\'' +
                '}';
    }
}
