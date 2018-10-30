import java.util.Iterator;
/**.
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**.
     * { size of DS }
     */
    private int n;
    /**.
     * { first node }
     */
    private Node first;
    /**.
     * Class for node.
     */
    private class Node {
        /**.
         * { object in a node. }
         */
        private Item item;
        /**.
         * { ptr to next node}
         */
        private Node next;
    }

   /**
     * Create an empty stack.
     */
    public Bag() {
        first = null;
        n = 0;
    }
/**.
 * Determines if empty.
 *
 * @return     True if empty, False otherwise.
 * time complexity is 1
 */
    public boolean isEmpty() {
        return first == null;
    }
/**.
 * returns size of the DS.
 *
 * @return     { description_of_the_return_value }
 * time complexity is 1
 */
    public int size() {
        return n;
    }
/**.
 * adds data to the DS.
 *
 * @param      item  The item
 * time complexity is 1
 */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
/**.
 * iterates through DS.
 *
 * @return     { description_of_the_return_value }
 * time complexity is 1
 */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }
/**.
 * Class for list iterator.
 */
    private class ListIterator implements Iterator<Item> {
        /**.
         * { var_description }
         */
        private Node current = first;
        /**.
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         * time complexity is 1
         */
        public boolean hasNext() {
            return current != null;
        }
        /**.
         * removes data.
         * time complexity is 1
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**.
         * returns next object.
         *
         * @return     { description_of_the_return_value }
         * time complexity is 1
         */
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
