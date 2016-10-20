import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
/**
 * Classe pour la generation des clé et enregistrement dans un fichier
 * @author antonio
 *
 */
public class GenerateKey {
	private static BigInteger q;
	private static BigInteger p;
	private static BigInteger a;
	private static BigInteger b= new BigInteger("1").add(new BigInteger("2").pow(16));
	private static BigInteger phiDeN;
	private static BigInteger n;
	private Euclide eucl = new Euclide();

	public GenerateKey(int bitLength) {
		p = BigInteger.probablePrime(bitLength / 2, new SecureRandom());
		q = BigInteger.probablePrime(bitLength / 2, new SecureRandom());
		
		//p = GeneratePrimeNumbers.primeNumberGenerate(bitLength);
		//q = GeneratePrimeNumbers.primeNumberGenerate(bitLength);
		
		n = p.multiply(q);
		phiDeN = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1"))); 
		a = eucl.modInverse(b, phiDeN);
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		String fileName = "";//param
		int bitLength = 0;//param
		if(args.length > 0){
			bitLength = Integer.parseInt(args[0]);
			if(args[1] != "")fileName = args[1]+"-"+bitLength;
		}
		new GenerateKey(bitLength);
		
		System.out.println("b : "  +b);
		System.out.println("a : "  +a);
		System.out.println("q : "  +q);
		System.out.println("p : "  +p);
		System.out.println("n : "  +n);
		System.out.println("phiDeN : "  +phiDeN);
		System.out.println("a*b : "+ a.multiply(b).mod(phiDeN));
		
		PrintWriter writer = new PrintWriter(fileName+".priv", "UTF-8");		
		String tnpqab = bitLength + " " + n + " " + p + " " + q + " " + a + " " + b ;		
		writer.println(tnpqab);		
		writer.close();
		
		PrintWriter writerPub = new PrintWriter(fileName+".pub", "UTF-8");		
		String tnb = bitLength + " " + n + " " + b ;		
		writerPub.println(tnb);		
		writerPub.close();
	}

	public static BigInteger getQ() {
		return q;
	}

	public static void setQ(BigInteger q) {
		GenerateKey.q = q;
	}

	public static BigInteger getP() {
		return p;
	}

	public static void setP(BigInteger p) {
		GenerateKey.p = p;
	}

	public static BigInteger getA() {
		return a;
	}

	public static void setA(BigInteger a) {
		GenerateKey.a = a;
	}

	public static BigInteger getB() {
		return b;
	}

	public static void setB(BigInteger b) {
		GenerateKey.b = b;
	}

	public static BigInteger getPhiDeN() {
		return phiDeN;
	}

	public static void setPhiDeN(BigInteger phiDeN) {
		GenerateKey.phiDeN = phiDeN;
	}

	public static BigInteger getN() {
		return n;
	}

	public static void setN(BigInteger n) {
		GenerateKey.n = n;
	}

	public Euclide getEucl() {
		return eucl;
	}

	public void setEucl(Euclide eucl) {
		this.eucl = eucl;
	}
	
}
