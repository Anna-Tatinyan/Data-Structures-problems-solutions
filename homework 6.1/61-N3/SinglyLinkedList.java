
class SinglyLinkedList<E> {

    private static class Node<E> {
      private E element;
      private Node<E> next;
      public Node(E e, Node<E> n) {
        element = e;
        next = n;
      }

      public E getElement() { return element; }

      public Node<E> getNext() { return next; }

      public void setNext(Node<E> n) { next = n; }
    }
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;
    public SinglyLinkedList() { }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public E first() {
      if (isEmpty()) return null;
      return head.getElement();
    }
    public E last() {
      if (isEmpty()) return null;
      return tail.getElement();
    }
    public void addFirst(E e) {
      head = new Node<>(e, head);
      if (size == 0)
        tail = head;
      size++;
    }

    public void addLast(E e) {                 // adds element e to the end of the list
      Node<E> newest = new Node<>(e, null);    // node will eventually be the tail
      if (isEmpty())
        head = newest;                         // special case: previously empty list
      else
        tail.setNext(newest);                  // new node after existing tail
      tail = newest;                           // new node becomes the tail
      size++;
    }

    public E removeFirst() {                   // removes and returns the first element
      if (isEmpty()) return null;              // nothing to remove
      E answer = head.getElement();
      head = head.getNext();                   // will become null if list had only one node
      size--;
      if (size == 0)
        tail = null;                           // special case as list is now empty
      return answer;
    }

    public boolean equals(Object o) {
      if (o == null) return false;
      if (getClass() != o.getClass()) return false;
      SinglyLinkedList other = (SinglyLinkedList) o;   // use nonparameterized type
      if (size != other.size) return false;
      Node walkA = head;                               // traverse the primary list
      Node walkB = other.head;                         // traverse the secondary list
      while (walkA != null) {
        if (!walkA.getElement().equals(walkB.getElement())) return false; //mismatch
        walkA = walkA.getNext();
        walkB = walkB.getNext();
      }
      return true;   // if we reach this, everything matched successfully
    }

}