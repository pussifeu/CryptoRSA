import java.io.IOException;
import java.math.BigInteger;

/**
 * Combinaison du programme
 * 
 *
 */
public class RSA {

	public RSA(int bitLength) {
		new GenerateKey(bitLength);
	}

	public static void main(String[] args) throws IOException {
		int taille = 0;
		if (args.length > 0) {
			taille =Integer.parseInt(args[0]);
		}
		
		
		new RSA(taille);

		System.out.println("b : " + GenerateKey.getB());
		System.out.println("a : " + GenerateKey.getA());
		System.out.println("q : " + GenerateKey.getQ());
		System.out.println("p : " + GenerateKey.getP());
		System.out.println("n : " + GenerateKey.getN());
		System.out.println("phiDeN : " + GenerateKey.getPhiDeN());
		System.out.println("a*b : " + GenerateKey.getA().multiply(GenerateKey.getB()).mod(GenerateKey.getPhiDeN()));
		
		String message = Utils.lectureFichier("message.txt");
		
		BigInteger[] Y = Chiffrer.chiffrer(message, GenerateKey.getN() ,GenerateKey.getB(),taille);
		String X = DeChiffrer.dechiffrer(Y, GenerateKey.getA(), GenerateKey.getN());
		
		System.out.println(X);
		
	}

	

}
