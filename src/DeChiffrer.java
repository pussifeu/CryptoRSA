import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Dechiffrement
 * @author antonio
 *
 */
public class DeChiffrer {

	public DeChiffrer() {
	}
	public static void main(String[] args) throws IOException {
		String fileName = "";
		if (args.length > 0) {
			fileName = args[0];
		}
		//Lire le fichier qui stock la clé privé
		String filePriv = Utils.lectureFichier(fileName+".priv");
		String[] filePrivSplit = filePriv.split(" ");
		
		//Lire le message chiffrer sur l'entrer standard et traitement du message
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String text = null;
		String sMessage = "";
		StringBuffer sBufferMessage = new StringBuffer();
		
		while ((text = input.readLine()) != null) {
			sBufferMessage.append(text);
			sMessage = sBufferMessage.toString();
		}

		String [] splitMessage = sMessage.split(" ");
		BigInteger[] Y = new BigInteger[splitMessage.length];
		for (int i = 0; i <Y.length; i++) {
			Y[i] = new BigInteger(splitMessage[i]);
			System.out.println(Y[i]);
		}
		
		//Dechiffrement du message
		String X = dechiffrer(Y, new BigInteger(filePrivSplit[4]), new BigInteger(filePrivSplit[1]));
		//Sortie
		System.out.println(X);
		
	}
	public static String dechiffrer(BigInteger[] Y, BigInteger a, BigInteger n) {
		int i;
		String X = "";
		BigInteger[] decrypted = new BigInteger[Y.length];
		// Decriptage
		for (i = 0; i < decrypted.length; i++) {
			decrypted[i] = Y[i].modPow(a, n);
		}
		char[] charArray = new char[decrypted.length];
		// Convert valeur decripter en cractère
		for (i = 0; i < charArray.length; i++) {
			charArray[i] = (char) (decrypted[i].intValue());
		}
		try {
			X = new String(charArray);
		} catch (Exception e) {
			System.out.println(e);
		}
		return (X);
	}

}
