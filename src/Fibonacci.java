public class Fibonacci {
    public static long fibonacci_recursive(int n){
        if(n <= 2) return 1;
        return fibonacci_recursive(n-1) + fibonacci_recursive(n-2);
    }
}
