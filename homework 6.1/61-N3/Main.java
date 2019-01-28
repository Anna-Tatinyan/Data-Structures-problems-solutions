
import java.util.*;
class Main {
    public static void unsortedOccurrences(String text) {
    
    AVLTree<String, Integer> tree = new AVLTree<>();
  
    // for(Position<Entry<Integer,Integer>> el: tree.inorder()) {
    //   System.out.println(el.getElement());
    // }  
    
    String[] arr = text.split(" ", 0);
    for(int i = 0; i< arr.length; i++) {
      if(tree.search(arr[i]) == null) {
        tree.insert(arr[i], 1);
      }
      else {
        tree.insert(arr[i], tree.search(arr[i]).getElement().getValue() + 1);
      }
    }
    System.out.println(tree.size());
    Iterator<Entry<String,Integer>> posIt = tree.iterator();
    for(int i =0; i<tree.size(); i++) {
      System.out.println(posIt.next());
    }

  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Unsorted Map");
    System.out.println("Enter string:  ");//requesting the input

    String text = sc.nextLine();//storing the input in the string 
    //better because it is a binary Search not O(n^2), search is logn
    //that we created above
    unsortedOccurrences(text);
  }
    

      }
