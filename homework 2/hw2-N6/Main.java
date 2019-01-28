//complexity O(n^2)
class LinkedList<E> { 
    private static class Node<E> {
    private E element; 
    private Node<E> next; 
    public Node(E e, Node<E> n) {
      element = e;
      next = n;
    }
    public E getElement( ) { return element; }
    public void setElement(E e) { element = e; }
    public Node<E> getNext( ) { return next; }
    public void setNext(Node<E> n) { next = n; }
    }
    private Node<E> head = null; 
    private Node<E> tail = null; 
    private int size = 0; 
    public LinkedList( ) { } 
    public int size( ) { return size; }
    public boolean isEmpty( ) { return size == 0; }
  public E first( ) {
    if (isEmpty( )) return null;
    return head.getElement( );
  }
  public E last( ) { 
    if (isEmpty( )) return null;
    return tail.getElement( );
  }
  public void addFirst(E e) { 
    head = new Node<>(e, head); 
    if (size == 0)
    tail = head; 
    size++;
  }
  public void addLast(E e) { 
    Node<E> newest = new Node<>(e, null); 
    if (isEmpty( ))
    head = newest;
    else
    tail.setNext(newest);
    tail = newest;
    size++;
  }
  public E removeFirst( ) {
    if (isEmpty( )) return null; 
    E answer = head.getElement( );
    head = head.getNext( ); 
    size--;
    if (size == 0)
    tail = null;
    return answer;
  }
  public void insertionSort() {
        Node<Integer> current = (Node<Integer>)head;
        Node<Integer> tail = null;
        while(current != null && tail != head) {
          while(current.next != tail) {
            if(current.getElement() <= current.next.getElement()) {
              int temp = current.getElement();
              current.setElement(current.next.getElement());
              current.next.setElement(temp);
            }
            current = current.next;
          }
          tail = current;
          current = (Node<Integer>)head;
        }
    }
  public void printList () {
    Node<E> current = head;
    for(int i = 0; i < size-1; i++){
      System.out.print(current.getElement() + " -> ");
      current = current.getNext();
    }
    System.out.print(current.getElement());
  }
}
class Main {
  public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.addFirst(7);
    list.addLast(4);
    list.addLast(5);
    list.addLast(2);
    list.addLast(5);
    list.addLast(6);
    list.insertionSort();
    list.printList();
    System.out.println("");
    LinkedList<Integer> list2 = new LinkedList<Integer>();
    list2.addFirst(7);
    list2.addLast(7);
    list2.addLast(5554);
    list2.addLast(-2);
    list2.addLast(0);
    list2.addLast(-55);
    list2.insertionSort();
    list2.printList();
  }
}