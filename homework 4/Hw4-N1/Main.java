class SinglyLinkedList<E> { 
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
    public SinglyLinkedList( ) { } 
    public int size( ) { return size; }
    public boolean isEmpty( ) { return size == 0; }
  public E first( ) {
    if (isEmpty( )) return null;
    return head.getElement( );
  }
   public Node<E> getFirst( ) {
    if (isEmpty( )) return null;
    return head;
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
  public static boolean isPalindrome(Node<Character> e, int size) {
      Stack<Character> st = new LinkedStack<>();
      String original = "";
      while(size>0) {
        st.push(e.getElement());
        String s=String.valueOf(e.getElement()); 
        original += s; 
        e = e.getNext();
        size--;
      };
      int i = 0;
      while(!st.isEmpty()) {
        if(st.pop() != original.charAt(i)) {
          return false;
        }
        i++;
      }
      return true;
      
 }
 
   
}
interface Stack<E> {
  int size( );
  boolean isEmpty( );
  void push(E e);
  E top( );
  E pop( );
}


class LinkedStack<E> implements Stack<E> {
 private SinglyLinkedList<E> list = new SinglyLinkedList<>( );
 public LinkedStack( ) { };
 public int size( ) { return list.size( ); }
 public boolean isEmpty( ) { 
   return list.isEmpty( ); 
 }
 public void push(E element) { 
   list.addFirst(element); 
 }
 public E top( ) { return list.first( ); }
 public E pop( ) { return list.removeFirst( ); }
 
 
}

class Main {
  public static void main(String[] args) {
   
    SinglyLinkedList<Character> S = new SinglyLinkedList<>();
    S.addFirst('a');
    S.addLast('b');
    S.addLast('c');
    S.addLast('b');
    S.addLast('a');
    System.out.println(S.isPalindrome(S.getFirst(), 5));
   
  }
  
}


