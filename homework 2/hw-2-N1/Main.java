class Main {
public static int indexOfTarget(int[ ] arrayOfNumbers, int target) {
 int low = 0, high = arrayOfNumbers.length - 1;
 while (low <= high) {
    int mid = (low + high) / 2;
    if (target == arrayOfNumbers[mid]) 
      return mid; //return the index of the target
    else if (target < arrayOfNumbers[mid])
      high = mid - 1; //go left
    else
      low = mid + 1; //go right
    }
    return -1; 
 }

 public static void main(String[] args) {
   int[] arr = {1,2,3,4,5,10};
   System.out.println(indexOfTarget(arr,55));
   System.out.println(indexOfTarget(arr,5));
   System.out.println(indexOfTarget(arr,0));
 }
}