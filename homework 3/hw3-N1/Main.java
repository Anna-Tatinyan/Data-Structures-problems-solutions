class Main {
  public static int countRecursion = 0;
  public static int countStrings = 0;
  public static void generatesPossibilities(String[] stringArray, int length, int n) {
    
    countRecursion++;
      if(length == n) {
        countStrings++;
        for(int i = 0; i<length; i++) {
          System.out.print(stringArray[i]);
        }
        System.out.println("");
        return;
      }
      if(stringArray[n-1] == "1") {

        stringArray[n] = "0";
        generatesPossibilities(stringArray, length, n+1);
      }
      else if(stringArray[n-1] == "0") {

        stringArray[n] = "0";
        generatesPossibilities(stringArray, length, n+1);
        stringArray[n] = "1";
        generatesPossibilities(stringArray, length, n+1);
      }
  }
  public static void generateStrings(int n) {

    String[] stringArray = new String[n];
            

    int firstElement = 1;
    stringArray[0] = "0";
    generatesPossibilities(stringArray, n, firstElement); //all posibilities wstarting with 0
    stringArray[0] = "1";
    generatesPossibilities(stringArray, n, firstElement); //all posibilities wstarting with 1
  }
  public static void main(String[] args) {
    generateStrings(5);
    System.out.println("Strings count " + countStrings);
    countRecursion = countRecursion - 2;
    System.out.println("Recursive calls count  " + countRecursion);
    countRecursion = 0;
    countStrings = 0;
    generateStrings(6);
    System.out.println("Strings count " + countStrings);
    countRecursion = countRecursion - 2;
    System.out.println("Recursive calls count  " + countRecursion);
    countRecursion = 0;
    countStrings = 0;
    generateStrings(7);
    System.out.println("Strings count " + countStrings);
    countRecursion = countRecursion - 2;
    System.out.println("Recursive calls count  " + countRecursion);
    //5 - 13
    //6 - 21
    //7 - 34
    //8 - 55
  }
}