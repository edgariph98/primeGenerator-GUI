import java.math.BigInteger;

public class Project1 {
	public static void main(String[] args) 
	{
		// Instantiate Primes Class
		Primes p = new Primes();
		MainWindow wind = new MainWindow(Config.APPLICATIONNAME,p);
		
		/*Primes p1 =new Primes();
		p1.generatePrimes(2, 12);
		p1.generateHexPrimes();
		FileAccess.loadPrimes(p1, "newprimes.txt");
		p1.printTwins();
		FileAccess.loadCrosses(p1, "crosses.txt");
		p1.printHexes();
		//FileAccess.savePrimes(p, "newprimes.txt");*/
		//FileAccess.saveCrosses(p, "crosses.txt");
		//MainWindow mw = new MainWindow(Config.APPLICATIONNAME, p);
	}
}
