import java.util.LinkedList;
import java.util.List;

public class Primes {
    public static boolean isPrime(long input){
        if(input <= 1) return false;
        for(long i = 2; i <= Math.sqrt(input); i++){
            if(input%i == 0)
                return false;
        }
        return true;
    }

    public static List<Long> primes_iterative(long input){
        List<Long> primes = new LinkedList<>();
        for(long i =2 ; i <= input; i++){
            if(isPrime(i))
                primes.add(i);
        }
        return primes;
    }

    public static void primes_array(int input){
        boolean[] array = new boolean[input];
        for(int i = 0; i < array.length; i++){
            array[i] = true;
        }
        array[0] = false;
        array[1] = false;
        for(int i = 2; i < input; i++){
            if(!array[i])continue;
            for(int j = i*2; j < array.length; j+=i){
                array[j] = false;
            }
        }
        for(int i = 2; i < array.length; i++){
            if(array[i])
            System.out.println(String.format("%d = %b", i, array[i]));

        }
    }
}
