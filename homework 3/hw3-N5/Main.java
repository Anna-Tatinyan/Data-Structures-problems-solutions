class Main {
  public static int count = 0;
  public static int[] insertionSort(int[] arr) {
    for(int i = 1; i < arr.length; i++) {
      int j = i;
      int current = arr[i];
     
      while(j>0 && arr[j-1] > current) {
        count++;
        arr[j] = arr[j-1];
        j--;
      }
      arr[j] = current;
    }
    return arr;
  }
  public static void main(String[] args) {
    int[] arr = {8, 7, 6, 5, 4, 3, 2, 1, 0};
    int[] newArr = insertionSort(arr);
    for(int i = 0; i< newArr.length; i++) {
    System.out.print(newArr[i]);
    }
    System.out.println("");
    System.out.println("Number of comparisons " + count);
  }
}

//O(n) best case when all is sorted
//O(n^2) worst case
//n! comparisons