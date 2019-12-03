// This file gives access to the underlying datafile and stores the data in the Workout class.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;

public class FileAccess {
  
	  public static boolean loadPrimes(Primes primes, String filename) {
			File primeFile = new File(Config.DATAPATH + filename);
			Scanner scanner;
			try {
				scanner = new Scanner(primeFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			ArrayList<BigInteger> lst = new ArrayList<BigInteger>();
			while(scanner.hasNext()) {
				String line = scanner.nextLine();
				BigInteger i = new BigInteger(line);
				lst.add(i);
			}
			primes.loadPrimes(lst);
			return true;
			
	  }
	  
	  public static boolean loadCrosses(Primes primes, String filename){
		  
		  File primeFile = new File(Config.DATAPATH + filename);
		  Scanner scanner;
		  try {
			  scanner = new Scanner(primeFile);
		  } catch (FileNotFoundException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
			  return false;
		  }
		  ArrayList<Pair<BigInteger>> lst = new ArrayList<Pair<BigInteger>>();
		  while(scanner.hasNext()) {
			  String[] arguments = scanner.nextLine().split(",");
			  BigInteger first = new BigInteger(arguments[0]);
			  BigInteger Second = new BigInteger(arguments[1]);
			  
			  lst.add(new Pair<BigInteger>(first,Second));
		  }
		  primes.loadHexPrimes(lst);
		  return true;
	  }
	  
	  public static boolean savePrimes(Primes primes, String filename) {
		  
		  PrintWriter  writer;
		  try {
			 writer = new PrintWriter(Config.DATAPATH + filename);
			 
			 
		  }catch(Exception e) {
			  e.printStackTrace();
			  return false;
		  }
		  for(BigInteger prime: primes.iteratePrimes()) {
			  String s = prime.toString();
			  try {
				writer.println(s);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  try {
			  writer.close();
		  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
		  return true;
	  }
	  
	  public static boolean saveCrosses(Primes primes, String filename){
		  PrintWriter writer;
		  try {
			  writer = new PrintWriter(Config.DATAPATH + filename);
			 
		  }catch(Exception e) {
			  e.printStackTrace();
			  return false;
		  }
		  for(Pair<BigInteger> p: primes.iterateCrosses()) {
			  String s = p.left().toString() + "," + p.right().toString();
			  try {
				writer.println(s);
			  }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
		  }
		  try {
			  writer.close();
		  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
		  return true;  
	  }
}