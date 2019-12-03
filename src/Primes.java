import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

//import Primes.Pair;

import java.math.BigInteger;

/*
 *  Desc: This class generates primes, twin primes, and hexagon crosses using BigInteger data types.
 */
public class Primes {
	
	// Member variables for containing out lists of integers, twin primes, hexagon crosses, and the pairs of twin primes that make up the hex crosses.
	private ArrayList<BigInteger> primeNums;
	private HashSet<BigInteger> primeSet;
	private ArrayList<Pair<BigInteger>> twinPrimes;
	private ArrayList<Pair<Pair<BigInteger>>> hexNums;
	private ArrayList<Pair<BigInteger>> hexCross;
	Primes(){
		primeNums = new ArrayList<BigInteger>();
		twinPrimes = new ArrayList<Pair<BigInteger>>();
		hexNums = new ArrayList<Pair<Pair<BigInteger>>>();
		primeSet = new HashSet<BigInteger>();
		hexCross = new ArrayList<Pair<BigInteger>>();
	}
	// Add a prime to the prime list if and only iff it is not already in the list. (ignore duplicates)
	public void addPrime(BigInteger x)
	{
		primeNums.add(x);
		primeSet.add(x);
	}
	
	// Empties the list of primes.
	public void clearPrimes()
	{
		primeNums.clear();
		primeSet.clear();
		twinPrimes.clear();
	}
	
	// Empties the list of crosses.
	public void clearCrosses()
	{
		hexNums.clear();
		hexCross.clear();
	}
	
	// Output the prime list. Each prime should be on a separate line and the total number of primes should be on the following line.
	public void printPrimes()
	{
		int count =0;
		for(BigInteger b : primeNums) {
			System.out.println(b);
			count++;
		}
		System.out.println("Total Primes: " + count);
	}
	// Output the twin prime list. Each twin prime should be on a separate line with a comma separating them, and the total number of twin primes should be on the following line.
	public void printTwins()
	{
		int count = 0;
		for(Pair<BigInteger> b:twinPrimes) {
			System.out.println(b);
			count++;
		}
		System.out.println("Total twins: " + count);
	}
		
	// Output the hexagon cross list. Each should be on a separate line listing the two twin primes and the corresponding hexagon cross, with the total number on the following line.
	public void printHexes()
	{
		int count = 0;
		BigInteger one = new BigInteger("1");
		for (Pair<Pair<BigInteger>> pair: hexNums) {
			Pair<BigInteger> first =  pair.left();
			Pair<BigInteger> second = pair.right();
			System.out.println("Prime pairs: " + first + " and " + second + " separated by " +  first.right().subtract(one) + ", " + second.right().subtract(one) );
			count++;
		}
		System.out.println("Total Hexes: " + count);
	}
		
	
	// Generate and store a list of primes.
	public void generatePrimes(int start, int count)
	{
		for(int i = start; i != count; ++i) {
			BigInteger num = new BigInteger(String.valueOf(i));
			if (NaiveTest.isPrime(num)){
				addPrime(num);
			}
		}
	}
	
	// Generate and store a list of twin primes.
	private void generateTwinPrimes()
	{
		twinPrimes.clear();
		for(BigInteger n : primeNums) {
			BigInteger nP2 = n.add(new BigInteger("2"));
			if(primeSet.contains(nP2)){
				Pair<BigInteger> p = new Pair<BigInteger>(n,nP2);
				twinPrimes.add(p);
			}
		}
	}
	// Generate and store the hexagon crosses, along with the two twin primes that generate the hexagon cross.
	public void generateHexPrimes()
	{
		generateTwinPrimes();
		clearCrosses();
		for(Pair<BigInteger> p : twinPrimes) {
			BigInteger one = new BigInteger("1");
			BigInteger hex1 = p.left().add(one);
			BigInteger hex2 = hex1.multiply(new BigInteger("2"));
			BigInteger hexFirst = hex2.subtract(one);
			BigInteger hexSecond = hex2	.add(one);
			if(primeSet.contains(hexFirst) && primeSet.contains(hexSecond)) {
				Pair<BigInteger> second = new Pair<BigInteger>(hexFirst,hexSecond);
				Pair<Pair<BigInteger>> finalPair = new Pair<Pair<BigInteger>>(p,second);
				hexNums.add(finalPair);
				hexCross.add(new Pair<BigInteger>(hex1,hex2));
				
			}
		}
	}
	// Count the number of digits in the last (and thus largest) prime.
	public int sizeofLastPrime()
	{
		if(primeNums.size()!=0) {
			return primeNums.get(primeNums.size()-1).toString().length();
		}
		return 0;
	}
	
	// Count the number of digits in the two entries in the last (and thus largest) hexagon cross
	public Pair<Integer> sizeofLastCross()
	{
		if(hexCross.size()!=0) {
			Integer size1 = new Integer(hexCross.get(hexCross.size()-1).left().toString().length());
			Integer size2 = new Integer(hexCross.get(hexCross.size()-1).right().toString().length());
			return new Pair<Integer>(size1,size2);
		}
		return new Pair<Integer>(new Integer(0),new Integer(0));
	}
	
	// Return the number of primes
	public int primeCount()
	{
		return primeNums.size();
	}
	
	// Return the number of crosses.
	public int crossesCount()
	{
		return hexCross.size();
	}
	
	public void loadPrimes(ArrayList<BigInteger> lst) {
		primeNums.clear();
		primeSet.clear();
		twinPrimes.clear();
		for(BigInteger prime : lst) {
			primeNums.add(prime);
			primeSet.add(prime);
		}
		generateTwinPrimes();
	}
	public void loadHexPrimes(ArrayList<Pair<BigInteger>> lst) {
		hexNums.clear();
		hexCross.clear();
		for(Pair<BigInteger> p: lst) {
			hexCross.add(p);
			Pair<BigInteger> hex1 = new Pair<BigInteger>(p.left().subtract(BigInteger.ONE), p.left().add(BigInteger.ONE));
			Pair<BigInteger> hex2 = new Pair<BigInteger>(p.right().subtract(BigInteger.ONE), p.right().add(BigInteger.ONE));
			hexNums.add(new Pair<Pair<BigInteger>>(hex1,hex2));
		}
	}
	
	
	
	public class IterablePrimes implements Iterable<BigInteger>
	{

		@Override
		public Iterator<BigInteger> iterator() {
			// TODO Auto-generated method stub
			return new myIterator();
		}
		public class myIterator implements Iterator<BigInteger>{
			int index;
			myIterator(){
				index =0;
			}
			@Override
			public boolean hasNext() {
				if(index >= primeNums.size()) {
					return false;
				}
				return true;
			}

			@Override
			public BigInteger next() {
				if(index < primeNums.size()) {
					BigInteger val =  primeNums.get(index);
					index++;
					return val;
				}
				// TODO Auto-generated method stub
				return null;
			}
			
		}
	}
	
	public IterablePrimes iteratePrimes() { return new IterablePrimes();}
	

	public class IterableCrosses implements Iterable<Pair<BigInteger>>
	{

		@Override
		public Iterator<Pair<BigInteger>> iterator() {
			// TODO Auto-generated method stub
			return new myIterator();
		}
		public class myIterator implements Iterator<Pair<BigInteger>>{
			int index;
			myIterator(){
				index =0;
			}
			@Override
			public boolean hasNext() {
				if(index >= hexCross.size()) {
					return false;
				}
				return true;
			}

			@Override
			public Pair<BigInteger> next() {
				if(index < hexCross.size()) {
					Pair<BigInteger> val =  hexCross.get(index);
					index++;
					return val;
				}
				// TODO Auto-generated method stub
				return null;
			}
			
		}
		
	}
	public IterableCrosses iterateCrosses() { return new IterableCrosses();}


}
