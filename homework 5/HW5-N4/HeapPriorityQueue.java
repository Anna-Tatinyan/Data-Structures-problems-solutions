import java.util.ArrayList;
import java.util.Comparator;

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
  /** primary collection of priority queue entries */
  protected ArrayList<Entry<K,V>> heap = new ArrayList<>();
  public HeapPriorityQueue() { super(); }
  public HeapPriorityQueue(Comparator<K> comp) { super(comp); }
  public HeapPriorityQueue(K[] keys, V[] values) {
    super();
    for (int j=0; j < Math.min(keys.length, values.length); j++)
      heap.add(new PQEntry<>(keys[j], values[j]));
    heapify();
  }

  // protected utilities
  protected int parent(int j) { return (j-1) / 2; }     // truncating division
  protected int left(int j) { return 2*j + 1; }
  protected int right(int j) { return 2*j + 2; }
  protected boolean hasLeft(int j) { return left(j) < heap.size(); }
  protected boolean hasRight(int j) { return right(j) < heap.size(); }

  /** Exchanges the entries at indices i and j of the array list. */
  protected void swap(int i, int j) {
    Entry<K,V> temp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, temp);
  }

  /** Moves the entry at index j higher, if necessary, to restore the heap property. */
  protected void upheap(int j) {
    while (j > 0) {            // continue until reaching root (or break statement)
      int p = parent(j);
      if (compare(heap.get(j), heap.get(p)) >= 0) break; // heap property verified
      swap(j, p);
      j = p;                                // continue from the parent's location
    }
  }
  protected void downheap(int j) {
    while (hasLeft(j)) {               // continue to bottom (or break statement)
      int leftIndex = left(j);
      int smallChildIndex = leftIndex;     // although right may be smaller
      if (hasRight(j)) {
          int rightIndex = right(j);
          if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
            smallChildIndex = rightIndex;  // right child is smaller
      }
      if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
        break;                             // heap property has been restored
      swap(j, smallChildIndex);
      j = smallChildIndex;                 // continue at position of the child
    }
  }

  protected void heapify() {
    int startIndex = parent(size()-1);    // start at PARENT of last entry
    for (int j=startIndex; j >= 0; j--)   // loop until processing the root
      downheap(j);
  }

  public int size() { return heap.size(); }
  public Entry<K,V> min() {
    if (heap.isEmpty()) return null;
    return heap.get(0);
  }

  public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
    checkKey(key);      // auxiliary key-checking method (could throw exception)
    Entry<K,V> newest = new PQEntry<>(key, value);
    heap.add(newest);                      // add to the end of the list
    upheap(heap.size() - 1);               // upheap newly added entry
    return newest;
  }

  public Entry<K,V> removeMin() {
    if (heap.isEmpty()) return null;
    Entry<K,V> answer = heap.get(0);
    swap(0, heap.size() - 1);              // put minimum item at the end
    heap.remove(heap.size() - 1);          // and remove it from the list;
    downheap(0);                           // then fix new root
    return answer;
  }

  /** Used for debugging purposes only */
  private void sanityCheck() {
    for (int j=0; j < heap.size(); j++) {
      int left = left(j);
      int right = right(j);
      if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0)
        System.out.println("Invalid left child relationship");
      if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0)
        System.out.println("Invalid right child relationship");
    }
  }
}