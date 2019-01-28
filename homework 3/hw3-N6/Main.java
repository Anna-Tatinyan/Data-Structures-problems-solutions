class Main {
  public static int count;
  public static int[] selectionSort(int[] arr) {
    int min, temp;
      for(int i = 0; i<arr.length - 1; i++) {
        min = i;
        for(int j = i+1; j<arr.length; j++) {
           count++;
          if(arr[j] < arr[min]) {
           
            min = j;
            temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
          }
        }
      }
      return arr;
  }
  public static void main(String[] args) {
    int[] arr = {8, 7, 6, 5, 4, 3, 2, 1};
    int[] newArr = selectionSort(arr);
    for(int i = 0; i< newArr.length; i++) {
    System.out.print(newArr[i]);
    }
    System.out.println("");
    System.out.println("Number of comparisons " + count);
  }
}

//1) n*(n-1) comparisons 
//2) n^2
//3) best case all sorted 
/// worst case non sorted - in decreaasing order