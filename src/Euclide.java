import java.math.BigInteger;
/**
 * Classe pour les algorithme d'euclide etendu et la fonction qui fait la modInverse
 */
public class Euclide {

	private static final BigInteger ZERO = BigInteger.ZERO;
	private static final BigInteger ONE = BigInteger.ONE;
	private static final BigInteger ONENEGATIF = new BigInteger("-1");
	public Euclide() {
	}
	/**
	 * ecludeEtendu :Calcul de l'inverse en arithmétique modulaire
	 * @param m
	 * @param n
	 * @return
	 */
	public  BigInteger[] ecludeEtendue(BigInteger m, BigInteger n) {
		BigInteger[] ma = new BigInteger[] { m, ONE, ZERO};
		BigInteger[] na = new BigInteger[] { n, ZERO, ONE };
		BigInteger[] ta; // Temporary variable
		BigInteger q; // Quotient
		BigInteger r; // Rest

		if (m.compareTo(n) == -1) {
			ta = na;
			na = ma;
			ma = ta;
		}

		while (na[0].signum() == 1) {
			q = ma[0].divide(na[0]); // Quotient
			for (int i = 0; i < 3; i++) {
				r = ma[i].subtract(q.multiply(na[i]));
				ma[i] = na[i];
				na[i] = r;
			}

		}
		return ma;
	}
	/**
	 * modInverse 
	 * @param n
	 * @param mod
	 * @return
	 */
	public  BigInteger modInverse(BigInteger n, BigInteger mod) {
		BigInteger[] g = ecludeEtendue(mod, n);

		if (g[0].equals(ONE))
			return reduce(g[2], mod);
		else
			return ONENEGATIF;
	}
	/**
	 * 
	 * @param n
	 * @param mod
	 * @return
	 */
	public  BigInteger reduce(BigInteger n, BigInteger mod) {
		BigInteger m = n.mod(mod);

		if (ZERO.compareTo(m) == 0 || ZERO.compareTo(m) == -1)
			return m;
		else
			return m.multiply(mod);
	}

}
