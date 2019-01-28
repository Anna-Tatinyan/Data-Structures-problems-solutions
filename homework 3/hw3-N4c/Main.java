class Main {
  public static char[] bucketSort(char [] arr) {
    int limit = 58, length = arr.length, index = 0;
    int[] bucketArray = new int[limit];
    for(int i = 0; i < length; i++) {
      int charASCII = (int) arr[i];
      bucketArray[charASCII-65]++;
    }
    
    for(int value = 0; value <= limit; value++) {
      if(index == arr.length) {
          break;
      }
      for(int k = 0; k < bucketArray[value]; k++) { 
        //will enter only if it is not 0 so that it will be smaller than the value in bucketArray
        char current = (char) (value+65);
        arr[index] = current;
        index++;
      }
    }
    
    return arr;
  }
  
  public static void main(String[] args) {
    char[] arr = {'s','V','z','a','a'};
    char[] arr2 = bucketSort(arr);
    for(int i = 0; i<arr2.length; i++) {
      System.out.print(arr2[i]);
    }
  }
}