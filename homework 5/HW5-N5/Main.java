class Main {

  public static void inPlaceHeapSort(int[] arr) {
    for (int i = arr.length / 2 - 1; i >= 0; i--) {
      heapify(arr, i, arr.length);

    }
    int index = arr.length-1;
    while(index>0) {
      int current = arr[0];
      arr[0] = arr[index];
      for (int i = arr.length / 2 - 1; i >= 0; i--) {
        heapify(arr, i, index);
      }
      arr[index] = current;
      index--;
    }

  }
  public static void heapify(int[] arr, int i, int n) {

    int left = 2*i + 1;
    int right = 2*i + 2;

    int largest = i;
    if(left<n && arr[left] > arr[largest]) {
      largest = left;
    }
    if(right<n && arr[right] > arr[largest]) {
      largest = right;
    }
    if(largest != i && largest<n) {
      int temp = arr[largest];
      arr[largest] = arr[i];
      arr[i] = temp;
    }
  }
  public static void main(String[] args) {
    int[] arr = {4,2,7,8,1,0};
    inPlaceHeapSort(arr);
    for(int i = 0; i<arr.length; i++) {
      System.out.print(arr[i]);
    }
    int[] test = {5, 6, 4, 7, 3, 8, 2, 9, 1, 10};
    inPlaceHeapSort(test);
    System.out.println("");
    for(int i = 0; i<test.length; i++) {
      System.out.print(test[i]);
    }
  }
}

//O(nlogn) cause it is a binary tree (insert is O(logn) * n, swap O(nlogn))
//same for the worst case 


//O(n) space we don't use any extra space 


    //in both cases it runs in O(nlogn)
    /*

  Phase 1:

  Array Before               Array after we                              First Time we                                 heapify


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
  Array            
                
  (10out => 397684251)       
    98763425110             
  (9 out => 18763425)        
     86753421910
  (8 out => 1675342)         
      76453128910
  (7 out => 264531)          
       65423178910
  (6 out => 15423)
        53421678910
  (5 out => 1342)
         43125678910
  (4 out => 231)
          32145678910
  (3 out => 12)
          21345678910
  (2 out => 1)
          12345678910
  (1 out)               
          12345678910
*/ 