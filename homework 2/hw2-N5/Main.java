class Main {
  public static void findMaximum(int[][] arr) {
    int[] maxArr = new int[arr.length];
    for(int i = 0; i<arr.length; i++) {
      int max = arr[0][0];
      for(int j = 0; j<arr[i].length; j++) {
        
        if(max < arr[i][j]) {
          max = arr[i][j];
        }
      }
      maxArr[i] = max;
    }
    for(int i = 0; i<arr.length; i++) {
      System.out.println("Maximum of the " + (i+1) + " array is " + maxArr[i]);
    }
  }
  public static void main(String[] args) {
    int[][] multiArr = new int[][]{
      { 4, 8, 7, 0},
      { 8, 44, 5, 0, 0 },
    };
    findMaximum(multiArr);
    int[][] multiArr1 = new int[][]{
      { 0, 0, 0, 0},
      { 8, 8, 8, 8, 8 },
    };
    findMaximum(multiArr1);
    int[][] multiArr2 = new int[][]{
      { 0, 11111, 0, 0},
      { 8, 8, 8, 88888, 8 },
    };
    findMaximum(multiArr2);
  };
}