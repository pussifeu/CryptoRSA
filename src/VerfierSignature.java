import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
/**
 * classe qui charge la verification de la signature
 *
 *
 */
//DSm=D(Kpb,SM)=D(Kpb,C(Kpr,H(M)))=H(M)
//Car SM = C(Kpr,H(M)) 
public class VerfierSignature {

	
	public VerfierSignature() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

		// Lire le message sur l'entrer standard et traitement de la signature
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String text = null;
		
		String fileName = "", fileNameSignature = "";
		if (args.length > 0) {
			fileName = args[0];
			fileNameSignature = args[1];
		}

		// Lire le fichier qui stock la clé privé
		String filePub = Utils.lectureFichier(fileName + ".pub");
		String[] filePubSplit = filePub.split(" ");
		
		String fileSignature = Utils.lectureFichier(fileNameSignature + ".chiffre");
		String[] fileSignatureSplit = fileSignature.split(" ");

		String X = "";
		StringBuffer sBufferMessage = new StringBuffer();

		while ((text = input.readLine()) != null) {
			sBufferMessage.append(text);
			X = sBufferMessage.toString();
		}
		String messageDigest = Utils.sha1(X);
		
		
		BigInteger[] Y = new BigInteger[fileSignatureSplit.length];
		for (int i = 0; i < Y.length; i++) {
			Y[i] = new BigInteger(fileSignatureSplit[i]);
		}
		
		String dechiffreSignature = dechiffreSignature(Y, new BigInteger(filePubSplit[2]),new BigInteger(filePubSplit[1]));
		
		if(verifSignature(messageDigest,dechiffreSignature)){
			System.out.println("Signature valide");
			System.out.println(messageDigest);
			System.out.println("-----------------------------");
			System.out.println(dechiffreSignature);
		}
				
		else{
			System.out.println("Signature non valide");
			System.out.println(messageDigest);
			System.out.println("-----------------------------");
			System.out.println(dechiffreSignature);
		}
	}
	
	// Chiffrer le condensat du message
	public static String dechiffreSignature(BigInteger[] Y, BigInteger pubKey, BigInteger n)
			throws UnsupportedEncodingException {
		return DeChiffrer.dechiffrer(Y, pubKey, n);
	}
	/**
	 * Verification du signature
	 */
	public static Boolean verifSignature(String messageDigest, String signatureDechiffrer){
		
		return messageDigest.equals(signatureDechiffrer);
	}
}
