import java.util.Iterator;
import java.util.NoSuchElementException;
interface Position<E> {
  E getElement() throws IllegalStateException;
}
class LinkedPositionalList<E> {
  private static class Node<E> implements Position<E>{
    private E element;               
    private Node<E> prev;            
    private Node<E> next;              
    public Node(E e, Node<E> p, Node<E> n) {
      element = e;
      prev = p;
      next = n;
    }  
    public E getElement() throws IllegalStateException {
      if (next == null)                         
        throw new IllegalStateException("Position no longer valid");
      return element;
    }
    public void setElement(E e) {
      element = e;
    }
    public Node<E> getPrev() {
      return prev;
    }
    public Node<E> getNext() {
      return next;
    }
    public void setPrev(Node<E> p) {
      prev = p;
    }
    public void setNext(Node<E> n) {
      next = n;
    }
    
  } 
  private Node<E> header;
  private Node<E> trailer;                      
  private int size = 0;
  public LinkedPositionalList() {
    header = new Node<>(null, null, null);      
    trailer = new Node<>(null, header, null);   
    header.setNext(trailer);                    
  }
  private Node<E> validate(Position<E> p) throws IllegalArgumentException {
    if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
    Node<E> node = (Node<E>) p;     
    if (node.getNext() == null)     
      throw new IllegalArgumentException("p is no longer in the list");
    return node;
  }  
  private Position<E> position(Node<E> node) {
    if (node == header || node == trailer)
      return null;   
    return node;
  }
  public int size() { return size; }
  public boolean isEmpty() { return size == 0; }
  public Position<E> first() {
    return position(header.getNext());
  }
  public Position<E> last() {
    return position(trailer.getPrev());
  }
  public Position<E> before(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return position(node.getPrev());
  }
  public Position<E> after(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return position(node.getNext());
  }
  private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
    Node<E> newest = new Node<>(e, pred, succ);  
    pred.setNext(newest);
    succ.setPrev(newest);
    size++;
    return newest;
  }
  public Position<E> addFirst(E e) {
    return addBetween(e, header, header.getNext());       
  }
  public Position<E> addLast(E e) {
    return addBetween(e, trailer.getPrev(), trailer);     
  }
  public Position<E> addBefore(Position<E> p, E e)
                                throws IllegalArgumentException {
    Node<E> node = validate(p);
    return addBetween(e, node.getPrev(), node);
  }
  public Position<E> addAfter(Position<E> p, E e)
                                throws IllegalArgumentException {
    Node<E> node = validate(p);
    return addBetween(e, node, node.getNext());
  }
  public E set(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> node = validate(p);
    E answer = node.getElement();
    node.setElement(e);
    return answer;
  }
  public E remove(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    Node<E> predecessor = node.getPrev();
    Node<E> successor = node.getNext();
    predecessor.setNext(successor);
    successor.setPrev(predecessor);
    size--;
    E answer = node.getElement();
    node.setElement(null);           
    node.setNext(null);              
    node.setPrev(null);
    return answer;
  }
  private class PositionIterator implements Iterator<Position<E>> {
    private Position<E> cursor = first();   
    private Position<E> recent = null;       
    public boolean hasNext() { return (cursor != null);  }
    public Position<E> next() throws NoSuchElementException {
      if (cursor == null) throw new NoSuchElementException("nothing left");
      recent = cursor;           
      cursor = after(cursor);
      return recent;
    }
    public void remove() throws IllegalStateException {
      if (recent == null) throw new IllegalStateException("nothing to remove");
      LinkedPositionalList.this.remove(recent);         
      recent = null;               
    }
  } 
  private class PositionIterable implements Iterable<Position<E>> {
    public Iterator<Position<E>> iterator() { return new PositionIterator(); }
  } 
  public Iterable<Position<E>> positions() {
    return new PositionIterable();       
  }
  private class ElementIterator implements Iterator<E> {
    Iterator<Position<E>> posIterator = new PositionIterator();
    public boolean hasNext() { return posIterator.hasNext(); }
    public E next() { return posIterator.next().getElement(); } 
    public void remove() { posIterator.remove(); }
  }
  public Iterator<E> iterator() { return new ElementIterator(); }
}
public class Main{
  public static void main(String[] args){
     LinkedPositionalList<Integer> list = new LinkedPositionalList<>();
    list.addFirst(88);  //method addFirst => calls addBetween to add an existing node/header in between 
    list.addLast(888); // addLast => add element between the trailer and trailer.previous, returns the position 
    list.addLast(8888); 
    Position<Integer> ReturnedPosition = list.addLast(9); //totally same but we keep the position in the variable
    list.set(ReturnedPosition, 23); //change the value of our Returned Position
    list.remove(ReturnedPosition); //don't like it anymore now give the position to be removed
    Iterator<Integer> IteratorList = list.iterator(); //returns new element iterator
    while (IteratorList.hasNext()){ //cursor points to smth not null
      System.out.print(IteratorList.next() + " ");
    } //print all, at the end recent becomes the last element as every time recent goes next and cursor goes one more ahead 
    IteratorList.remove(); // removes the last element (recent)
    System.out.println();
    Iterable<Position<Integer>> iterableList = list.positions(); // same goal other implementation using iterable interface
    for(Position<Integer> element: iterableList){ 
      System.out.print(element.getElement() + " ");
    }
  }
}

//LinkedPositionalList: space usage => O(n)
//Every function has running time of O(1) constant 