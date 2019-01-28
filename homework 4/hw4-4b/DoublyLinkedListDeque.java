
class DoublyLinkedListDeque<E> implements Deque<E> {
    DoublyLinkedList<E> list = new DoublyLinkedList<>();
    public DoublyLinkedListDeque() {
    };
    public int size() {
      return list.size();
    }
    public boolean isEmpty() {
      return list.isEmpty();
    }
    public E first() {
      return list.first();
    }
    public E last() {
      return list.last();
    }
    public void addFirst(E e) {
       list.addFirst(e);
    }
    public void addLast(E e) {
       list.addLast(e);
    }
    public E removeFirst() {
      return list.removeFirst();
    }
    public E removeLast() {
      return list.removeLast();
    }

}