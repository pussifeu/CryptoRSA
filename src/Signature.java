import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

/**
 * Signature H(message) Produit un hash ou condonsat du message
 * 
 * Chiffre ce condensat grâce à la fonction de chiffrement en utilisant
 * sa clé privée . Le résultat obtenu est la signature du message : SM = C(Kpr,H(M)) ;
 */
public class Signature {

	public Signature() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

		String fileName = "";
		if (args.length > 0) {
			fileName = args[0];
		}

		// Lire le fichier qui stock la clé privé
		String filePriv = Utils.lectureFichier(fileName + ".priv");
		String[] filePrivSplit = filePriv.split(" ");

		// Lire le message sur l'entrer standard et traitement de la signature
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String text = null;

		String X = "", sMessageSigne="";
		StringBuffer sBufferMessage = new StringBuffer();
		
		while ((text = input.readLine()) != null) {
			sBufferMessage.append(text);
			X = sBufferMessage.toString();
		}
		String messageDigest = Utils.sha1(X);
		StringBuffer sBufferMessageSigne = new StringBuffer();
		BigInteger [] messageSigne = chiffrerHash(messageDigest, new BigInteger(filePrivSplit[1]), new BigInteger(filePrivSplit[4]), Integer.parseInt(filePrivSplit[0]));
		
		//Converssion du message chiffrer en buffer String
		for (int i = 0; i < messageSigne.length; i++) {
			sBufferMessageSigne.append(messageSigne[i]);
			sBufferMessageSigne.append(" ");
			sMessageSigne = sBufferMessageSigne.toString();
		}
		//Stockage du message chiffrer dans un fichier
		PrintWriter wMessageSigne = new PrintWriter("signature-"+fileName+".chiffre", "UTF-8");			
		wMessageSigne.println(sMessageSigne);		
		wMessageSigne.close();
		System.out.println(sMessageSigne);
		
	}

	// Chiffrer le condensat du message
	public static BigInteger[] chiffrerHash(String messageDigest, BigInteger n, BigInteger keyPrivate, int t)
			throws UnsupportedEncodingException {
		return Chiffrer.chiffrer(messageDigest, n, keyPrivate, t);
	}

}
