class Main {
  public static void pairsOfOpposites(int arr[], int start, int end) {
    if(end <= start) {
      return;
    }
    if(arr[start] + arr[end] == 0) {
      System.out.println(arr[start] + " - " + arr[end]);
      pairsOfOpposites(arr, start+1, end-1);
    } else if(arr[start] + arr[end] > 0) {
      oops(arr, start, end-1);
    } else {
      oops(arr, start+1, end);
    }
  }
   
   public static void main(String[] args) {
   int[] arr = {-3, -2,-1, 1, 2, 3, 6, 9};
    pairsOfOpposites(arr, 0, arr.length -1);
 }
}