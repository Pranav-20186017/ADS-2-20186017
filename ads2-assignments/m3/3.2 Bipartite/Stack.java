/**
 * stack class.
 *
 * @param      <Item>  The item
 */
public class Stack<Item> {
    /**
     * // size of the stack.
     */
    public int size;
    /**
     * // top of stack.
     */
    public Node first;

    /**
     * Class for node.
     */
    public class Node {
        /**
         * item.
         */
        public Item item;
        /**
         * next node.
         */
        public Node next;
    }

    /**
      * Create an empty stack.
      */
    public Stack() {
        first = null;
        size = 0;
    }



    /**
     * Is the stack empty?
     *
     * @return     True if empty, False otherwise.
     * Time Complexity : O(1)
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Return the number of items in the stack.
     *
     * @return     { size }
     * Time Complexity : O(1)
     */
    public int size() {
        return size;
    }

    /**
     * add an item to stack.
     *
     * @param      item  The item.
     * Time Complexity : O(1)
     */
    public void push(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        size++;
    }
}