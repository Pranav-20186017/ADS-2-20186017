import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for a Stack.
 *
 * @param      <Item>  The item
 */
public class Stack<Item> implements Iterable<Item> {
    /**
     * node in a stack.
     */
    private Node<Item> first;     // top of stack
    /**
     * size of the stack.
     */
    private int n;                // size of the stack
    /**
     * Class for linked list.
     * @param      <Item>  The item
     */
    private static class Node<Item> {
        /**
         * item in a linked list.
         */
        private Item item;
        /**
         * pointer to next node.
         */
        private Node<Item> next;
    }
    /**
     * Initializes an empty stack.
     */
    public Stack() {
        first = null;
        n = 0;
    }
    /**
     * Returns true if this stack is empty.
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }
    /**
     * Returns the number of items in this stack.
     *
     * @return the number of items in this stack
     */
    public int size() {
        return n;
    }
    /**
     * Adds the item to this stack.
     *
     * @param  item the item to add
     */
    public void push(final Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        n--;
        return item;                   // return the saved item
    }
    /**
     * Returns (but does not remove) the item most recently.
     * added to this stack
     * @return the item most recently added to this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return first.item;
    }
    /**
     * Returns a string representation of this stack.
     *
     * @return the sequence of items in this stack in
     * LIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }
    /**
     * iterator.
     *
     * @return     { description_of_the_return_value }
     */
    Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }
    /**
     * Class for list iterator.
     *
     * @param      <Item>  The item
     */
    private class ListIterator<Item> implements Iterator<Item> {
        /**
         * current node.
         */
        private Node<Item> current;
        /**
         * Constructs the object.
         *
         * @param      firstitem  The first
         */
        ListIterator(final Node<Item> firstitem) {
            this.current = firstitem;
        }
        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        /**
         * removes an element.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * moves the pointer ahead by one memory spcace.
         *
         * @return     { new item }
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
