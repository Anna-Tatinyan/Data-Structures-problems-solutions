
class ArrayQueue<E> implements Queue<E> {
  public static int CAPACITY = 1000;  
  private E[] data;               
  private int f = 0;         
  private int sz = 0;   
  public ArrayQueue() {this(CAPACITY);}  
  public ArrayQueue(int capacity) {        
    data = (E[]) new Object[capacity]; 
    CAPACITY = capacity;   
  }
  public int size() { return sz; }
  public boolean isEmpty() { return (sz == 0); }

  public void enqueue(E e) {
    if (sz == data.length) {
      resize();

    }
    data[sz] = e;
    sz++;
  }

  public E first() {
    if (isEmpty()) return null;
    return data[f];
  }
  public void resize() {
    E[] temp = (E[]) new Object[2*size()];     
    for (int k=0; k < sz; k++)
      temp[k] = data[k];
    data = temp;                            
  }

  public E dequeue() {
    if (isEmpty()) return null;
    E answer = data[f];
    data[f] = null;                            
    f = (f + 1);
    sz--;
    return answer;
  }
  
}



  interface Queue<E> {
    int size( );
    boolean isEmpty( );
    void enqueue(E e);
    E first( );
    E dequeue( );
  }

  
  
  class Main {
    public static void main(String[] args) {
      ArrayQueue<Integer> example = new ArrayQueue<>(5);
      int start = 0;
      for(int i = 0; i<100; i++) {
        example.enqueue(start++);
        example.enqueue(start++);
      }
      
        System.out.println(example.dequeue());
    }
  }
  /* I just called it 100 times,adding elements from the beginning and from the end,  though the initial size was 5. Why can I do this? Because it has no bounded capacity, just like with arraylists. */