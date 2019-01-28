import java.util.*;
class Main {
  
  public static void unsortedOccurrences(String text) {
    
    UnsortedTableMap<String,Integer> map = new UnsortedTableMap<>();
    
    String[] arr = text.split(" ", 0);
    for(int i = 0; i< arr.length; i++) {
      if(map.get(arr[i]) == null) {
        map.put(arr[i], 1);
      }
      else {
        map.put(arr[i], map.get(arr[i]) + 1);
      }
    }
    Iterable<Entry<String,Integer>> set = map.entrySet();
    int size = map.size();
    while(!map.isEmpty()) {
      System.out.println(set.iterator().next());
    }

  }
  public static void sortedOccurrences(String text) {
    SortedTableMap<String,Integer> map = new SortedTableMap<>();
    String[] arr = text.split(" ", 0);
    for(int i = 0; i< arr.length; i++) {
      if(map.get(arr[i]) == null) {
        map.put(arr[i], 1);
      }
      else {
        map.put(arr[i], map.get(arr[i]) + 1);
      }
    }
    ArrayList<Entry<String,Integer>> set = map.entrySet();
    for(int i = 0; i<set.size(); i++) {
      System.out.println(set.get(i));
    }
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Unsorted Map");
    System.out.println("Enter string:  ");//requesting the input

    String text = sc.nextLine();//storing the input in the string that we created above
    unsortedOccurrences(text);
    System.out.println("Sorted Map");
    sortedOccurrences(text);
    System.out.println("Unsorted Map");
    unsortedOccurrences(text);
     System.out.println("Sorted Map");
    sortedOccurrences(text);
  }
  //the result is different.
  //in case of unsortedMap we have the order just the same as we gave it to. 
  //in case of orderedMap we have ordered structure based on the Lexicographical order
  //worst 
}