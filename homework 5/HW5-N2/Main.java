import java.util.*;

interface Position<E> {
  E getElement() throws IllegalStateException;
}

interface List<E> extends Iterable<E> {
  int size();
  boolean isEmpty();
  E get(int i) throws IndexOutOfBoundsException;
  E set(int i, E e) throws IndexOutOfBoundsException;
  void add(int i, E e) throws IndexOutOfBoundsException;
  E remove(int i) throws IndexOutOfBoundsException;
  Iterator<E> iterator();
}
class ArrayList<E> implements List<E> {
  public static final int CAPACITY=16;
  private E[] data;
  private int size = 0;
  public ArrayList() { this(CAPACITY); }
  @SuppressWarnings({"unchecked"})
  public ArrayList(int capacity) {         // constructs list with given capacity
    data = (E[]) new Object[capacity];     // safe cast; compiler may give warning
  }
  public int size() { return size; }

  public boolean isEmpty() { return size == 0; }
  public E get(int i) throws IndexOutOfBoundsException {
    checkIndex(i, size);
    return data[i];
  }
  public E set(int i, E e) throws IndexOutOfBoundsException {
    checkIndex(i, size);
    E temp = data[i];
    data[i] = e;
    return temp;
  }
  public void add(int i, E e) throws IndexOutOfBoundsException {
    checkIndex(i, size + 1);
    if (size == data.length)               // not enough capacity
      resize(2 * data.length);             // so int the current capacity
    for (int k=size-1; k >= i; k--)        // start by shifting rightmost
      data[k+1] = data[k];
    data[i] = e;                           // ready to place the new element
    size++;
  }

  public E remove(int i) throws IndexOutOfBoundsException {
    checkIndex(i, size);
    E temp = data[i];
    for (int k=i; k < size-1; k++)         // shift elements to fill hole
      data[k] = data[k+1];
    data[size-1] = null;                   // help garbage collection
    size--;
    return temp;
  }

  protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
    if (i < 0 || i >= n)
      throw new IndexOutOfBoundsException("Illegal index: " + i);
  }
  @SuppressWarnings({"unchecked"})
  protected void resize(int capacity) {
    E[] temp = (E[]) new Object[capacity];     // safe cast; compiler may give warning
    for (int k=0; k < size; k++)
      temp[k] = data[k];
    data = temp;                               // start using the new array
  }
  private class ArrayIterator implements Iterator<E> {
    /** Index of the next element to report. */
    private int j = 0;                   // index of the next element to report
    private boolean removable = false;
    public boolean hasNext() { return j < size; }
    public E next() throws NoSuchElementException {
      if (j == size) throw new NoSuchElementException("No next element");
      removable = true;   // this element can subsequently be removed
      return data[j++];   // post-increment j, so it is ready for future call to next
    }
    public void remove() throws IllegalStateException {
      if (!removable) throw new IllegalStateException("nothing to remove");
      ArrayList.this.remove(j-1);  // that was the last one returned
      j--;                         // next element has shifted one cell to the left
      removable = false;           // do not allow remove again until next is called
    }
  }
  public Iterator<E> iterator() {
    return new ArrayIterator();     // create a new instance of the inner class
  }
  public String toString() {
    StringBuilder sb = new StringBuilder("(");
    for (int j = 0; j < size; j++) {
      if (j > 0) sb.append(", ");
      sb.append(data[j]);
    }
    sb.append(")");
    return sb.toString();
  }
}

interface Tree<E> extends Iterable<E> {

    Position<E> root( );
    Position<E> parent(Position<E> p) throws IllegalArgumentException;
    Iterable<Position<E>> children(Position<E> p)
    throws IllegalArgumentException;
    int numChildren(Position<E> p) throws IllegalArgumentException;
    boolean isInternal(Position<E> p) throws IllegalArgumentException;
    boolean isExternal(Position<E> p) throws IllegalArgumentException;
    boolean isRoot(Position<E> p) throws IllegalArgumentException;
    int size( );
    boolean isEmpty( );
    Iterator<E> iterator( );
    Iterable<Position<E>> positions( );
}


interface Queue<E> {
  int size();
  boolean isEmpty();
  void enqueue(E e);
  E first();
  E dequeue();
}

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

class LinkedQueue<E> implements Queue<E> {

  private SinglyLinkedList<E> list = new SinglyLinkedList<>();
  public LinkedQueue() { }                  // new queue relies on the initially empty list
  public int size() { return list.size(); }
  public boolean isEmpty() { return list.isEmpty(); }
  public void enqueue(E element) { list.addLast(element); }
  public E first() { return list.first(); }
  public E dequeue() { return list.removeFirst(); }
  public String toString() {
    return list.toString();
  }

}

abstract class AbstractTree<E> implements Tree<E> {

  public boolean isInternal(Position<E> p) { return numChildren(p) > 0; }


  public boolean isExternal(Position<E> p) { return numChildren(p) == 0; }

  public boolean isRoot(Position<E> p) { return p == root(); }

  public int numChildren(Position<E> p) {
    int count=0;
    for (Position child : children(p)) count++;
    return count;
  }

  public int size() {
    int count=0;
    for (Position p : positions()) count++;
    return count;
  }

  public boolean isEmpty() { return size() == 0; }

  public int depth(Position<E> p) throws IllegalArgumentException {
    if (isRoot(p))
      return 0;
    else
      return 1 + depth(parent(p));
  }

  public int height(Position<E> p) throws IllegalArgumentException {
    int h = 0;                          // base case if p is external
    for (Position<E> c : children(p))
      h = Math.max(h, 1 + height(c));
    return h;
  }

  private class ElementIterator implements Iterator<E> {
    Iterator<Position<E>> posIterator = positions().iterator();
    public boolean hasNext() { return posIterator.hasNext(); }
    public E next() { return posIterator.next().getElement(); } // return element!
    public void remove() { posIterator.remove(); }
  }

  public Iterator<E> iterator() { return new ElementIterator(); }

  public Iterable<Position<E>> positions() { return postorder(); }

  private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    snapshot.add(snapshot.size(), p);                       // for preorder, we add position p before exploring subtrees
    for (Position<E> c : children(p))
      preorderSubtree(c, snapshot);
  }

  public Iterable<Position<E>> preorder() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty())
      preorderSubtree(root(), snapshot);   // fill the snapshot recursively
    return (Iterable<Position<E>>) snapshot;
  }

    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    for (Position<E> c : children(p)) {
      postorderSubtree(c, snapshot);
    }

    snapshot.add(snapshot.size(), p);
    System.out.printf("%s ", p.getElement());
                         // for postorder, we add position p after exploring subtrees
  }

  public Iterable<Position<E>> postorder() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty())
      postorderSubtree(root(), snapshot);   // fill the snapshot recursively
    return (Iterable<Position<E>>) snapshot;
  }
}

interface BinaryTree<E> extends Tree<E> {

    Position<E> left(Position<E> p) throws IllegalArgumentException;
    Position<E> right(Position<E> p) throws IllegalArgumentException;
    Position<E> sibling(Position<E> p) throws IllegalArgumentException;

}

abstract class AbstractBinaryTree<E> extends AbstractTree<E>
                                             implements BinaryTree<E> {

  public Position<E> sibling(Position<E> p) {
    Position<E> parent = parent(p);
    if (parent == null) return null;                  // p must be the root
    if (p == left(parent))                            // p is a left child
      return right(parent);                           // (right child might be null)
    else                                              // p is a right child
      return left(parent);                            // (left child might be null)
  }

  public int numChildren(Position<E> p) {
    int count=0;
    if (left(p) != null)
      count++;
    if (right(p) != null)
      count++;
    return count;
  }

  public Iterable<Position<E>> children(Position<E> p) {
    List<Position<E>> snapshot = new ArrayList<>(2);    // max capacity of 2
    if (left(p) != null)
      snapshot.add(snapshot.size(), left(p));
    if (right(p) != null)
      snapshot.add(snapshot.size(), right(p));
    return (Iterable<Position<E>>) snapshot;
  }


  private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    if (left(p) != null)
      inorderSubtree(left(p), snapshot);
    snapshot.add(snapshot.size(), p);
    if (right(p) != null)
      inorderSubtree(right(p), snapshot);
  }

  public Iterable<Position<E>> inorder() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty())
      inorderSubtree(root(), snapshot);   // fill the snapshot recursively
    return (Iterable<Position<E>>) snapshot;
  }

  public Iterable<Position<E>> positions() {
    return inorder();
  }
}

class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
      protected static class Node<E> implements Position<E> {
          private E element;
          private Node<E> parent;
          private Node<E> left;
          private Node<E> right;
          public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
              element = e;
              parent = above;
              left = leftChild;
              right = rightChild;
          }
          public E getElement( ) { return element; }
          public Node<E> getParent( ) { return parent; }
          public Node<E> getLeft( ) { return left; }
          public Node<E> getRight( ) { return right; }
          public void setElement(E e) { element = e; }
          public void setParent(Node<E> parentNode) { parent = parentNode; }
          public void setLeft(Node<E> leftChild) { left = leftChild; }
          public void setRight(Node<E> rightChild) { right = rightChild; }
      }
      protected Node<E> createNode(E e, Node<E> parent,
      Node<E> left, Node<E> right) {
          return new Node<E>(e, parent, left, right);
      }
      protected Node<E> root = null;
      private int size = 0;
      public LinkedBinaryTree( ) { }
      protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
          throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p;
        if (node.getParent( ) == node)
          throw new IllegalArgumentException("p is no longer in the tree");
        return node;
      }
      public int size( ) {
        return size;
      }
      public Position<E> root( ) {
        return root;
      }
      public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent( );
      }
      public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft( );
      }
      public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
      }
      public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty( )) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
      }
      public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft( ) != null)
          throw new IllegalArgumentException("p already has a left child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
      }
      public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight( ) != null)
          throw new IllegalArgumentException("p already has a right child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
      }
      public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement( );
        node.setElement(e);
        return temp;
      }
      public int wholeHeight() {
        return height(root);
      }

      
     
      void inorderWithoutRecursion()
    	{
    		if (root == null)
    			return;


    		Stack<Position<E>> s = new Stack<Position<E>>();
    		Position<E> curr = root;
    		while (curr != null || s.size() > 0)
    		{
    			while (curr != null)
    			{
    				s.push(curr);
    				curr = left(curr);
    			}
    			curr = s.pop();

    			System.out.print(curr.getElement() + " ");
    			curr = right(curr);
    		}
    	}

      public static void getDrawableNodes(LinkedBinaryTree<Integer> tree) {
        ArrayList<DrawOnePosition<Integer>> arr = new ArrayList<DrawOnePosition<Integer>>();
        int counter = 0;
        for(Position<Integer> p : tree.inorder()) {
          arr.add(counter, new DrawOnePosition<Integer>(p, counter, tree.depth(p)));
          counter++;
        }
        int[][] trueArray = new int[tree.wholeHeight()+1][arr.size()];
        
        for(int j = 0; j<arr.size();j++) {
          trueArray[arr.get(j).y][arr.get(j).x] = arr.get(j).p.getElement();
        }

        for(int i = 0; i<trueArray.length; i++) {
          for(int k = 0; k< trueArray[i].length; k++) {
            if(trueArray[i][k] == 0) {
              System.out.print(" ");
            }
            else {
              System.out.print(trueArray[i][k]);
            }
          }
          System.out.println("");
         
        }
      }

}

class DrawOnePosition<E> {
  //helper class to organize the data, for exercise 2 
  public Position<E> p;
  public int x, y;

  public DrawOnePosition(Position<E> p, int x, int y) {
    this.p = p;
    this.x = x;
    this.y = y;
  }

}
public class Main {
  public static void main(String[] args) {
    LinkedBinaryTree<Integer> t = new LinkedBinaryTree<>();
    t.addRoot(11);
    //System.out.println(t.root().getElement());
    t.addRight(t.root(), 22);
    t.addLeft(t.root(), 33);
    Position<Integer> root = t.root();
    Position<Integer> right = t.right(root);
    Position<Integer> left = t.left(root);

        // 3
        t.addLeft(left, 44);
        t.addRight(left, 55);
        t.addLeft(t.right(root), 66);
        t.addRight(t.right(root), 77);

        // 4
        t.addLeft(t.left(left), 88);
        t.addRight(t.left(left), 99);
        t.addRight(t.right(left), 10);
        t.addRight(t.right(right), 11);

        // 5
        t.addLeft(t.left(t.left(left)), 12);
        t.addRight(t.left(t.left(left)), 13);
        t.addLeft(t.right(t.left(left)), 14);
        t.addRight(t.right(t.right(left)), 15);
        t.addRight(t.right(t.right(right)), 16);

        // 6
        t.addLeft(t.left(t.left(t.left(t.left((root))))), 17);
        t.addRight(t.left(t.left(t.left(t.left((root))))), 18);
        t.addRight(t.left(t.right(t.left(t.left((root))))), 19);
        t.addLeft(t.right(t.right(t.right(right))), 20);
        t.addRight(t.right(t.right(t.right(right))), 21);
  }
}
