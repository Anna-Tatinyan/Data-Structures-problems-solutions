class Main {
  public static int count = 0;
  public static int[] bubbleSort(int[] arr) {
    boolean isSorted;
    for(int i = 0; i<arr.length; i++) {
      isSorted = true;
      for(int j = arr.length - 1; j>i; j--) { 
        count++;
        if(arr[j-1] > arr[j]) {
          int temp = arr[j];
          arr[j] = arr[j-1];
          arr[j-1] = temp;
          isSorted = false;
        }
      }
      if(isSorted) {
        break;
      }
    }
    return arr;
  }
  public static void main(String[] args) {
     int[] arr = {8, 7, 6, 5, 4, 3, 2, 1};
    int[] newArr = bubbleSort(arr);
    for(int i = 0; i< newArr.length; i++) {
    System.out.print(newArr[i]);
    }
    System.out.println("");
    System.out.println("Number of Comparisons " + count);
    
  }
}

//worst O(n^2)
