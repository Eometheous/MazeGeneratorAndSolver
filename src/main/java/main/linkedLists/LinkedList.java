package main.linkedLists;

/**
 * A class implementing a set of items as a singly linked list
 */
public class LinkedList {
    private Node head;
    private int numberOfItems;

    public LinkedList() {
        head = null;
        numberOfItems = 0;
    }

    public int numberOfItems() {
        return numberOfItems;
    }

    /**
     * Adds an item to the set
     * @param item the item being added
     * @return true if the item was added, false if it wasn't
     */
    public boolean add(int item) {
        Node newNode = new Node();
        if (!inList(item)) {
            newNode.setItem(item);
            newNode.setNext(head);
            head = newNode;
            numberOfItems++;
            return true;
        }
        return false;
    }

    /**
     * Searches for a node containing the item in the set
     * @param item the item being searched
     * @return the node containing the item
     */
    public Node search(int item) {
        Node searchNode = head;
        while (searchNode != null) {
            if (searchNode.getItem() == item) return searchNode;
            searchNode = searchNode.getNext();
        }
        return null;
    }

    /**
     * Checks if an item is in the set
     * @param item the item being looked for in the set
     * @return true if the item is in the set, false if it isn't
     */
    public boolean inList(int item) {
        Node searchNode = head;
        while (searchNode != null) {
            if (searchNode.getItem() == item) return true;
            searchNode = searchNode.getNext();
        }
        return false;
    }

    /**
     * Finds the previous node of the item being searched
     * This is used for deleting a node somewhere in the middle or at the end of the set
     * @param item the item being searched
     * @return the node containing the item
     */
    public Node findPreviousNode(int item) {
        Node previousNode = head;
        while (previousNode.getNext() != null) {
            if (previousNode.getNext().getItem() == item) return previousNode;
            previousNode = previousNode.getNext();
        }
        return null;
    }

    /**
     * Removes an item from the set
     * @param item the item being removed
     * @return true if the item was removed, false if the item wasn't found
     */
    public boolean remove(int item) {
        if (!inList(item)) return false;

        if (head.getItem() == item) {
            head = head.getNext();
        }
        else {
            Node previousNode = findPreviousNode(item);
            previousNode.setNext(previousNode.getNext().getNext());
        }
        numberOfItems--;
        return true;
    }

    public Node getHead() {return head;}

    /**
     * Overrides toString from Object
     * @return a string listing all items in the set
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        Node node = head;
        while (node != null) {
            string.append(node.getItem()).append(" ");
            node = node.getNext();
        }
        return string.toString();
    }
}
