 interface List<E> {

    int size();
    boolean isEmpty();
    E get(int i) throws IndexOutOfBoundsException;
    E set(int i, E e) throws IndexOutOfBoundsException;
    void add(int i, E e) throws IndexOutOfBoundsException;
    E remove(int i) throws IndexOutOfBoundsException;
  }


 class ArrayList<E> implements List<E> {
  public static int CAPACITY=2;     
  private E[] data;                        
  private int size = 0;                    
  public ArrayList() { this(CAPACITY); }   
  public ArrayList(int capacity) {         
    data = (E[]) new Object[capacity];  
    CAPACITY = capacity;
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
    if (size == data.length)               
      resize(2 * data.length);             
    for (int k=size-1; k >= i; k--)
      data[k+1] = data[k];
    data[i] = e;                           
    size++;
  }
  public E remove(int i) throws IndexOutOfBoundsException {
    System.out.println(1);
    checkIndex(i, size);
    E temp = data[i];
    for (int k=i; k < size-1; k++)         
      data[k] = data[k+1];
    data[size-1] = null;                  
    size--;
    return temp;
  }

  protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
    if (i < 0 || i >= n)
      throw new IndexOutOfBoundsException("Illegal index: " + i);
  }
  protected void resize(int capacity) {
    E[] temp = (E[]) new Object[capacity];     
    for (int k=0; k < size; k++)
      temp[k] = data[k];
    data = temp;                            
  }
}
 interface Stack<E> {
  int size( );
  boolean isEmpty( );
  void push(E e);
  E top( );
  E pop( );
 }
 class ArrayListStack<E> implements Stack<E> {
 private ArrayList<E> list = new ArrayList<>( );
 public int size( ) { return list.size( ); }
 public boolean isEmpty( ) { return list.isEmpty( ); }
 public void push(E element) { 

   list.add(size(), element);
 }
 public E top( ) { return list.get(size()-1); }
 public E pop( ) { 
   return list.remove(size()-1); }
 
}
  
  class Main {
  public static void main(String[] args) {
    ArrayListStack<Integer> list = new ArrayListStack<>();
    int start = 0;
      for(int i = 0; i<100; i++) {
        list.push(start++);
      }
      
        System.out.println(list.pop());
    }
  }

  
  /* I just called it 100 times,adding elements  though the initial size of created arraylist was 2. Why can I do this? Because it has no bounded capacity, arraylists. */