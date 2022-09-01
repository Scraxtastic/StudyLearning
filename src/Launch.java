import java.util.List;

public class Launch {
    public static void main(String[] args) {
        Launch launcher = new Launch();
        launcher.start();
    }

    public void start(){
//        writeFibonacciRec();
//        writePrimes_Iterative();
//        Primes.primes_array(300000000);
    }

    public void writeFibonacciRec(){
        int max = 50;
        for(int i = 0; i<max; i++) {
            System.out.println(String.format("input: %d, result: %d, perc: %f", i, Fibonacci.fibonacci_recursive(i), (i/(float)max)*100));
        }
    }

    public void writePrimes_Iterative(){
        List<Long> primes = Primes.primes_iterative(2003);
        for(int i = 0; i < primes.size(); i++){
            System.out.println(String.format("%d: %d -\tperc: %f%%", i, primes.get(i), (i/(float)primes.size())*100));
        }
    }
}
