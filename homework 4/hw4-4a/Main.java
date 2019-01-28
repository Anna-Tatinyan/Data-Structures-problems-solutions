 interface Deque<E> {
  int size();
  boolean isEmpty();
  E first();
  E last();
  void addFirst(E e);
  void addLast(E e);
  E removeFirst();
  E removeLast();
}

class ArrayDeque<E> implements Deque<E> {
  public static int CAPACITY = 10;  
  private E[] data;               
  private int f = 0; 
  private int sz = 0;   
   
  private int last = CAPACITY - 1;     
  public ArrayDeque() {this(CAPACITY);}  
  public ArrayDeque(int capacity) {        
    data = (E[]) new Object[capacity];        
    CAPACITY = capacity;  
  }
  public int size() { return sz; }
  public boolean isEmpty() { return (sz == 0); }
  public void addFirst(E e) {
     if (sz == data.length) {
      resize(2);

    }
    data[f] = e;
    f = Math.floorMod(f+1, CAPACITY);
    sz++;
  }
  
  public E removeLast() {
    if (isEmpty()) return null;
    else if(sz == CAPACITY/4) {
      resize(1/2);

    }
    E answer = data[Math.floorMod(last+1, CAPACITY)];
    data[Math.floorMod(last+1, CAPACITY)] = null;                            
    last = Math.floorMod(last+1, CAPACITY);
    sz--;
    return answer;
  }
  public void addLast(E e) {
    if (sz == data.length) {
      resize(2);

    }
    data[last] = e;
    last = Math.floorMod(last-1, CAPACITY);
    sz++;
  }

  public E first() {
    if (isEmpty()) return null;
    return data[Math.floorMod(f-1, CAPACITY)];
  }
  public E last() {
    if (isEmpty()) return null;
    return data[Math.floorMod(last+1, CAPACITY)];
  }
  public void resize(int num) {
    E[] temp = (E[]) new Object[num*size()];     
    for (int k=0; k<=f-1; k++) {
      temp[k] = data[k];
    }
    for (int j=size()-1; j>=last+1; j--) {
      temp[j] = data[j];
    }
    data = temp;                            
  }

  public E removeFirst() {
    if (isEmpty()) return null;
    else if(sz == CAPACITY/4) {
      resize(1/2);

    }
    E answer = data[Math.floorMod(f-1, CAPACITY)];
    data[Math.floorMod(f-1, CAPACITY)] = null;                            
    f = Math.floorMod(f-1, CAPACITY);
    sz--;
    return answer;
  }
  
}

  class Main {
    public static void main(String[] args) {
      ArrayDeque<Integer> example = new ArrayDeque<>();
      for(int i = 0; i< 200; i++) {
         example.addFirst(5);
      example.addLast(6);
      }
     
      System.out.println(example.removeFirst());
    }
  }
  /* as we can see there is no problem in extanding our array, it has no limits => we can make it bigger and bigger. Running time O(1), space usage optimal O(2n) ~ O(n) (maximum twice as many elements) as we make it smaller every time when it is less than CAPACITY/4 */