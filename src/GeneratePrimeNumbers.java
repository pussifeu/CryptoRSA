import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
/**
 * 
 * Classe pou generer et verifier si un nombre est premier
 */
public class GeneratePrimeNumbers {

	static int k = 40;
	public GeneratePrimeNumbers() {
		// TODO Auto-generated constructor stub
	}
	private static final BigInteger ZERO = BigInteger.ZERO;
	private static final BigInteger ONE = BigInteger.ONE;
	private static final BigInteger TWO = new BigInteger("2");
	private static final BigInteger THREE = new BigInteger("3");

	public static boolean estProbablementPremier(BigInteger n, BigInteger S) {
		// S'assurer que la fonction prenne un param n > 2
		if (n.compareTo(THREE) < 0) { return true; }
		
		int k = 0;
		
		// n-1 ← 2^k * m où m impair
		BigInteger m = n.subtract(ONE);
		while (m.mod(TWO).equals(ZERO)) {
			k++;
			m = m.divide(TWO);
		}
		
		// b ← a^m mod n
		BigInteger b = S.modPow(m, n);
		
		//Si b = 1 mod n alors n est premier et on retourne vrai
		if (b.equals(ONE) || b.equals(n.subtract(ONE))) { return true; }
		
		int r=1 ;
		
		// Boucler jusqu'à ce qu'on obtient b = n-1
		for ( ; r < k; r++) {
			//b ← b2 mod n
			b = b.modPow(TWO, n);
			
			// Si b == 1 alors n est composite
			if (b.equals(ONE)) { return false; }
			
			// Si b = n-1 alors on sort de la boucle
			if (b.equals(n.subtract(ONE))) { break; }
				
		}
		
		// Si r = k, on n'a pas pu trouvé b tel que b = n-1
		if (r == k) { return false; }
			
		
		return true;
	}
	
	public static boolean estPremierRapide( BigInteger n, int k ) {
		// Choix aléatoire de a tel que 1 < a <= n-1
		BigInteger S = uniformRandom(TWO, n.subtract(ONE));
		while ( k > 0 ) {
			 if ( ! estProbablementPremier( n, S ) ) { return false; }
		     k--;
		}
		return true;
	}
	


	private static BigInteger uniformRandom(BigInteger bottom, BigInteger top) {
		Random rnd = new Random();
		BigInteger res;
		do {
			res = new BigInteger(top.bitLength(), rnd);
		} while (res.compareTo(bottom) < 0 || res.compareTo(top) > 0);
		return res;
	}
	
	/**
	 * Generer un nombre premier	
	 * @return
	 */
	public static BigInteger primeNumberGenerate(int nbBits) {
		BigInteger nombreAleatoire = new BigInteger(nbBits, new SecureRandom());
		if (estPremierRapide(nombreAleatoire, k)) 
		{
			if(nombreAleatoire.isProbablePrime(k))
				return nombreAleatoire;	
			else
				return primeNumberGenerate(nbBits);
		}
		else 
			return primeNumberGenerate(nbBits);
	}
}
