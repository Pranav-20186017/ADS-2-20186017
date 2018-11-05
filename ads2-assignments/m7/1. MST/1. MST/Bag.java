import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     * first node.
     */
    private Node<Item> first;    // beginning of bag
    /**
     * number of elements.
     */
    private int n;               // number of elements in bag
    /**
     * Class for node.
     *
     * @param      <Item>  The item
     */
    private static class Node<Item> {
        /**
         * item in the bag.
         */
        private Item item;
        /**
         * pointer towards next node.
         */
        private Node<Item> next;
    }
    /**
     * Initializes an empty bag.
     */
    public Bag() {
        first = null;
        n = 0;
    }
    /**
     * Returns true if this bag is empty.
     *
     * @return {@code true} if this bag is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }
    /**
     * Returns the number of items in this bag.
     *
     * @return the number of items in this bag
     */
    public int size() {
        return n;
    }
    /**
     * Adds the item to this bag.
     *
     * @param  item the item to add to this bag
     */
    public void add(final Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
    /**
     * @return an iterator that iterates over.
     * the items in this bag in arbitrary order
     */
    public Iterator<Item> iterator()  {
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
         * @param      first  The first
         */
        ListIterator(Node<Item> firstitem) {
            this.current = firstitem;
        }
        /**
         * Determines if it has next.
         *
         * @return True if has next, False otherwise.
         */
        public boolean hasNext() { 
            return current != null;
        }
        /**
         * removes and element.
         */
        public void remove() { 
            throw new UnsupportedOperationException();
        }
        /**
         * moves the cursor ahead by one.
         *
         * @return     { description_of_the_return_value }
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