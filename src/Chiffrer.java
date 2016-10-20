import java.io.*;
import java.math.BigInteger;

/**
 * Classe pour le chiffrement RSA ECB
 * 
 * @author antonio
 *
 */
public class Chiffrer {

	public Chiffrer() {
	}

	public static void main(String[] args) throws IOException {

		String fileName = "";
		if (args.length > 0) {
			fileName = args[0];
		}
		//Lire le fichier qui stock la clé public
		String filePublic = Utils.lectureFichier(fileName+".pub");
		String[] filePublicSplit = filePublic.split(" ");
				
		//Lire le message  sur l'entrer standard et traitement du message
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String text = null;

		String sChiffrer = "", X = "";
		StringBuffer sBufferChiffrer = new StringBuffer();
		StringBuffer sBufferMessage = new StringBuffer();
		
		while ((text = input.readLine()) != null) {
			sBufferMessage.append(text);
			X = sBufferMessage.toString();
		}
		
		//Chiffrement du message X par la clé
		BigInteger[] Y = chiffrer(X, new BigInteger(filePublicSplit[1]), new BigInteger(filePublicSplit[2]),Integer.parseInt(filePublicSplit[0]));
		
		//Converssion du message chiffrer en buffer String
		for (int i = 0; i < Y.length; i++) {
			sBufferChiffrer.append(Y[i]);
			sBufferChiffrer.append(" ");
			sChiffrer = sBufferChiffrer.toString();
		}
		//Stockage du message chiffrer dans un fichier
		PrintWriter wMessageChiffrer = new PrintWriter("msg-for-"+fileName+".chiffre", "UTF-8");		
		String messageChiffrer = sChiffrer;		
		wMessageChiffrer.println(messageChiffrer);		
		wMessageChiffrer.close();
	}

	/**
	 * découpé en blocs de t bits, puis chiffré en séquence
	 * 
	 * @param message
	 * @param n
	 * @param b
	 * @param t
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static BigInteger[] chiffrer(String message, BigInteger n, BigInteger b, int t) throws UnsupportedEncodingException {
		//Convertion du chaine en bloc de t bit
		byte[] temp = new byte[1];
		byte[] digits = new byte[t];
		digits = message.getBytes("UTF8");
		
		//Decoupage de chaine en bit de caratere de t bits
		BigInteger[] bigdigits = new BigInteger[digits.length];
		for (int i = 0; i < bigdigits.length; i++) {
			temp[0] = digits[i];
			bigdigits[i] = new BigInteger(temp);
		}
		//Chiffrement par clé public b et n
		BigInteger[] chiffrer = new BigInteger[bigdigits.length];
		for (int i = 0; i < bigdigits.length; i++) {
			chiffrer[i] = bigdigits[i].modPow(b, n);
		}
		return (chiffrer);
	}
	
}
