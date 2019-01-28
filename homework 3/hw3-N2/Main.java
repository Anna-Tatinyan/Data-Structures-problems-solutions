class Main {
  public static boolean isPalindromeNonRec(char[] c) {
    int n = c.length - 1;
    boolean isPalindrome = true;
    for(int i = 0; i<=n; i++) {
      if(c[i] != c[n - i]){
        isPalindrome = false;
      }
    }
    return isPalindrome;
  };
  public static int i = 0;
  public static boolean isPalindromeRec(char[] c) {
    int n = c.length;
    boolean isPalindrome = true;
    if(i == n/2) {
      return isPalindrome;
    }
    if(c[i] != c[n - 1 - i]) {
      isPalindrome = false;
      return isPalindrome;
    }
    i++;
    return isPalindromeRec(c);
  }
  public static void main(String[] args) {
    char[] c = {'a','b', 'a', 'd'};
    System.out.println(isPalindromeRec(c));
  }
}