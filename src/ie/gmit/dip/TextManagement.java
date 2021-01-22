package ie.gmit.dip;

public class TextManagement {

	//Constructor
	public TextManagement() {

	}

	//Public Method (called from Menu)
	//Redirect folowing option (Encrypt-Decrypt from Menu)
	//Option, key and Text as aparamenters
	//Pass the Custom Square if there's one
	public void textManagement(int option, String key, String text, char[][] customSquare){
		switch (option) {
		case 1:
			textEncryption(key, text, customSquare);
			break;
		case 2:
			textDecryption(key, text, customSquare);
		default:
			break;
		}
	}

	//Method to Encrypt Text
	private void textEncryption(String key, String text, char[][] customSquare) {

		//Create an object Polybius with the Key as parameter
		Polybius cipher = new Polybius(key);
		//String to assign the Ciphered Text
		String cipherText;

		//Call one or other Method (overloaded) if there's Custom Square or not
		//Text as parameter (from Menu)
		if(customSquare == null) {
			cipherText = cipher.encrypt(text);
		}else {
			cipherText = cipher.encrypt(text, customSquare);
		}

		//Show the Ciphered Text
		System.out.println("\nCipher Text: " + cipherText);

		//Method to close the program
		closing();
	}

	//Same for decyption but calling the Decryption method in Polybius
	private void textDecryption(String key, String text, char[][] customSquare) {

		Polybius cipher = new Polybius(key);
		String decryptedText;

		if(customSquare == null) {
			decryptedText = cipher.decrypt(text);
		}else {
			decryptedText = cipher.decrypt(text, customSquare);
		}

		System.out.println("\nDecrypted Text: " + decryptedText);

		closing();
	}

	//Method to close the program after the work
	private void closing() {
		System.out.println("\n~ Polybius Cipher has been closed ~");
		System.exit(0);
	}

}
