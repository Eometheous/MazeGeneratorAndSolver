package main.linkedLists;

public class Node {
    private Node next;
    private int item;

    public Node() {
        item = 0;
        next = null;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }
}
