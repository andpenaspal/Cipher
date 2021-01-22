package ie.gmit.dip;

public class SmartManagement {

	//Constructor
	public SmartManagement() {

	}

	//Public Method to be called from other classes and redirect to the private methods
	//Parameter: option selected (Enc/Dec-Enc-Dec-DeepEnc/Dec), Key and Text from Input and custom square if exist
	public void smartManagement(int option, String key, String text, char[][] customSquare) {
		mainSmart(option, key, text, customSquare);
	}

	//Method to redirect with the selection made
	private void mainSmart(int option, String key, String text, char[][] customSquare) {
		switch (option) {
		case 1:
			smartEncDec(key, text, customSquare);
			break;
		case 2:
			smartEnc(key, text, customSquare);
			break;
		case 3:
			smartDec(key, text, customSquare);
			break;
		case 4:
			smartDeepEncDec(key, text, customSquare);
			break;

		default:
			break;
		}
	}
	//Method to Directly Encrypt and Decrypt
	private void smartEncDec(String key, String text, char[][] customSquare) {
		//Local variables for the Data in the method
		String cipherText;
		String decryptedText;
		//New object Polybius passing the key	
		Polybius cipher = new Polybius(key);

		//If there's a custom square, pass it to the pertinent overloaded method, if not, too
		//Methods to Encrypt the Input and Decrypt the ciphered text
		if(customSquare == null) {
			cipherText = cipher.encrypt(text);
			decryptedText = cipher.decrypt(cipherText);
		}else {
			cipherText = cipher.encrypt(text, customSquare);
			decryptedText = cipher.decrypt(cipherText, customSquare);
		}
		//Show the outputs
		System.out.println("\nCipher Text: " + cipherText);
		System.out.println("\nDecrypted Text: " + decryptedText);
		//Call method to close
		closing();
	}
	//Same as above but just for Encryption
	private void smartEnc(String key, String text, char[][] customSquare) {

		String cipherText;

		Polybius cipher = new Polybius(key);

		if(customSquare == null) {
			cipherText = cipher.smartEncrypt(text);
		}else {
			cipherText = cipher.smartEncrypt(text, customSquare);
		}

		System.out.println("\nCipher Text: " + cipherText);

		closing();
	}
	//Same as above but just for Decryption
	private void smartDec(String key, String text, char[][] customSquare) {

		String decryptedText;

		Polybius cipher = new Polybius(key);

		if(customSquare == null) {
			decryptedText = cipher.smartDecrypt(text);
		}else {
			decryptedText = cipher.smartDecrypt(text, customSquare);
		}

		System.out.println("\nDecrypted Text: " + decryptedText);

		closing();
	}
	//Same as above but with the overloaded methods to show the works
	private void smartDeepEncDec(String key, String text, char[][] customSquare) {

		String cipherText;
		String decryptedText;

		Polybius cipher = new Polybius(key);

		if(customSquare == null) {
			cipherText = cipher.smartEncrypt(text);
			System.out.println("\nCipher Text: " + cipherText);
			decryptedText = cipher.smartDecrypt(cipherText);
		}else {
			cipherText = cipher.smartEncrypt(text, customSquare);
			System.out.println("\nCipher Text: " + cipherText);
			decryptedText = cipher.smartDecrypt(cipherText, customSquare);
		}

		System.out.println("\nDecrypted Text: " + decryptedText);

		closing();
	}
	//Method to close
	private void closing() {
		System.out.println("\n~ Polybius Cipher has been closed ~");
		System.exit(0);
	}

}
