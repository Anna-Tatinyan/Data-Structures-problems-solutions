import java.util.*;

class Main {
   public static void selectionSort(ArrayList<Integer> list, Comparator<Integer> myComp) {
    UnsortedPriorityQueue<Integer, Integer> pq = new UnsortedPriorityQueue(myComp);
    int size = list.size();
    for(int i = 0; i<size; i++) {
      pq.insert(list.get(0), null);
      list.remove(0);
    }
    for(int j = 0; j<size; j++) {
       list.add(pq.min().getKey());

       pq.removeMin();
    }
   
  }
   public static void insertionSort(ArrayList<Integer> list, Comparator<Integer> myComp) {
    SortedPriorityQueue<Integer, Integer> pq = new SortedPriorityQueue(myComp);
    int size = list.size();
    for(int i = 0; i<size; i++) {
      pq.insert(list.get(0), null);
      list.remove(0);
    }
    for(int j = 0; j<size; j++) {
       list.add(pq.min().getKey());

       pq.removeMin();
    }
   
  }
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<>();
    DefaultComparator<Integer> myComp = new DefaultComparator<Integer>();
    list.add(5);
    list.add(2);
    list.add(8);
    list.add(8);
    list.add(88);
    selectionSort(list, myComp);
    System.out.println("Sort: Selection with Priority Queue");
    for(int i = 0; i<list.size(); i++) {
      System.out.print(list.get(i) + " -> ");
    }
     System.out.println("");
    list.add(1);
    list.add(-2);
    list.add(-8);
    list.add(18);
    list.add(88);
    insertionSort(list, myComp);
    System.out.println("Sort: Insertion with Priority Queue");
    for(int i = 0; i<list.size(); i++) {
      System.out.print(list.get(i) + " -> ");
    }
    System.out.println("");
  }

}

//inseertion worst O(n^2) in decreasing order 
//because there is no work to do hence 
//insertion best O(n) in ascending order

//selection worst and best the same because it will look for the minimum O(n^2)