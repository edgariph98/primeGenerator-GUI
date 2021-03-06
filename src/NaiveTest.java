import java.math.BigInteger;
import java.math.*;
public class NaiveTest
{
	//final is prime test
	public static boolean isPrime(BigInteger candidate) {
	    if (!candidate.isProbablePrime(100)) //weed out  not likely primes
	        return false;

	    //weed out evens since they are not prime because they are divisible by two but not including 
	    BigInteger two = new BigInteger("2");
	    if (!two.equals(candidate) && BigInteger.ZERO.equals(candidate.mod(two)))
	        return false;

	    //start Checking if any odd numbers are a divisor of candidate
	    // i increasing by 2 and starting at 3
	    // and only going up to the square root of the candidate
	    for (BigInteger i = new BigInteger("3"); i.multiply(i).compareTo(candidate) < 1; i = i.add(two)) { //start from 3, 5, etc. the odd number, and look for a divisor if any
	        if (BigInteger.ZERO.equals(candidate.mod(i))) //check if i is divisor of candidate
	            return false;
	    }
	    return true;
	}
}
