class Main {
  public static int findIndex(int last, int [] arr) {
    for(int i = 0; i<arr.length; i++) {
    
    if(arr[i] > 0 && arr[i-1] <= 0) {
      return i;
    }
    
    }
    return last;
  }
  public static void binarySearchIterative(int[] arrayOfNumbers) {
    int n = arrayOfNumbers.length;
    int[] numbersWithOpp = new int[n];
    int mid = findIndex(arrayOfNumbers[n-1],arrayOfNumbers);
    int low = 0, high = n - 1;
    int j = 0;
    while (low <= mid) {
      
      if (arrayOfNumbers[low] + arrayOfNumbers[high] == 0) {
        numbersWithOpp[j] = arrayOfNumbers[high];
        j++;
        high--;
        if(Math.abs(arrayOfNumbers[low]) != arrayOfNumbers[high-1]) {
          low++;
        }
      }

      else {
        System.out.println(low + " " + high);
        low++; //go right
        if(Math.abs(arrayOfNumbers[low]) < arrayOfNumbers[high]) {
          high--;
        }
      }
    }
      for(int i =0; i<numbersWithOpp.length; i++) {
        if(numbersWithOpp[i] != 0) {
        System.out.print(numbersWithOpp[i]);
        System.out.print(" - ");
        System.out.println(numbersWithOpp[i]);
        }
      }
  }

  public static void main(String[] args) {
    int[] arr = {-6,-3,-1,1,3,6};
    binarySearchIterative(arr);
    
  }
}