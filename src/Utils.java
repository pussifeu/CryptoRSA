import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utilitaire
 * @author antonio
 *
 */
public class Utils {

	public Utils() {
	}
	/**
	 * Lire un fichier
	 * @param source
	 */
	public static String lectureFichier(String source) {
		String string = "";
		try {
			String ligne;
			BufferedReader fichier = new BufferedReader(new FileReader(source));

			while ((ligne = fichier.readLine()) != null) {
				string = ligne;
			}

			fichier.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}
	
	

	// Condansat du message
	public static String sha1(String message) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(message.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

}
