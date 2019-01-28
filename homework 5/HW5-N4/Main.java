
import java.util.*;
class Main {
  public static void heapSort(ArrayList<Integer> list, Comparator<Integer> myComp) {
    HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue(myComp);
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
    heapSort(list, myComp);
    System.out.println("Sort: Heap Sort");
    for(int i = 0; i<list.size(); i++) {
      System.out.print(list.get(i) + " -> ");
    }
     System.out.println("");

    ArrayList<Integer> testList = new ArrayList<>();
    testList.add(5);
    testList.add(6);
    testList.add(4); 
    testList.add(7); 
    testList.add(3);
    testList.add(8);
    testList.add(2);
    testList.add(9);
    testList.add(1);
    testList.add(10);
    heapSort(testList, myComp);
    for(int i = 0; i<testList.size(); i++) {
      System.out.print(testList.get(i) + " -> ");
    }
    //in both cases it runs in O(nlogn)
    //because insert is logn and n times, the remove same
    /*

  Phase 1:

  ArrayList                  Priority Queue


  56473829110                -
   6473829110                5
    473829110                65
     73829110                654
      3829110                7645
       829110                76453
        29110                867534
         9110                8675342
          110                98763425
           10                987634251
            0                10976842513
            -
  Priority Queue             ArrayList
                
  (10out => 397684251)       10
    987634251               
  (9 out => 18763425)        
     86753421                910
  (8 out => 1675342)         
      7645312                8910
  (7 out => 264531)          
       654231                78910
  (6 out => 15423)
        53421                678910
  (5 out => 1342)
         4312                5678910
  (4 out => 231)
          321                45678910
  (3 out => 12)
          21                 345678910
  (2 out => 1)
          1                  2345678910
  (1 out)               
                             12345678910
*/ 
    
  
  }
}