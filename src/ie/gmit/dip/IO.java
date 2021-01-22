package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class IO {

	Scanner in = new Scanner(System.in);
	BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));


	public IO(){
	}

	public String sInputManager(int selection, int redirect) throws IOException{
		switch (selection) {
		case 1:
			return keyInput(redirect);
		case 2:
			return textInput(redirect);
		case 3:
			return plainData();
		default:
			break;
		}

		return null;
	}

	public String sInputManager(int selection, int redirect, int smartMenuOption) throws IOException{
		return textInput(redirect, smartMenuOption);
	}

	public int iInputManager() throws IOException{
		return iInput();
	}

	private int iInput() throws IOException{
		int option = in.nextInt();
		return option;
	}

	public void closeIO() throws IOException{
		in.close();
		bReader.close();
	}

	//Method to Enter the key
	private String keyInput(int redirect) throws IOException{
		System.out.println("Please, Enter the Key:");
		//Call the Buffered Reader (Ins Var) and assign the Line written to the Key variable
		String key = bReader.readLine();
		//Wasn't in my plan, but if It's only 1 char I can Encrypt fine but have an exception Decrypting
		//In Polybius: 548. I honestly don't know why, and why there. Tried to figure it out but couldn't
		while(key.length() < 2) {
			System.out.println("The Key must be more than 1 Char \nPlease, Try again:");
			key = bReader.readLine();
		}
		if(key.length() < 1) {
			while(key.length() < 1) {
				System.out.println("The Key can't be null");
				System.out.println("Please, Enter a Valid Key:");
				key = bReader.readLine();
			}
		}
		//If the Key is asked from the Smart Menu, there's limitations
		if(redirect == 3) {
			//Do not let the key pass if it's bigger than 10 chars, ask again
			while(key.length() > 10) {
				System.out.println("Smart Option doesn't allow a Key bigger than '10 Chars'");
				System.out.println("Please, Enter a Valid Key:");
				key = bReader.readLine();
			}
		}
		return key;
	}

	//Method to ask for the Text (Encryption or Decryption following the selection of the menu (redirect))
	private String textInput(int redirect) throws IOException{
		String text = null;
		if(redirect == 1) System.out.println("Please, Enter the Text to Cipher:");
		if(redirect == 2) System.out.println("Please, Enter the Text to Decrypt:");
		text = bReader.readLine();

		return text;
	}

	//Method Overloaded to ask for Text, if it's with the 3 option in Main Menu (Smart Mode)
	//There's limitations
	private String textInput(int redirect, int smartMenuOption) throws IOException{
		String text = null;
		//If it's option 3 in main menu
		//? Don't needed, but just to be sure
		if(redirect == 3) {
			//Switch following the selection in SmartMenu. 1, 2 and 4 ask for Encryption
			switch (smartMenuOption) {
			case 1:
			case 2:
			case 4:
				//Ask for the text and read the Input from the System with the BufferedReader
				System.out.println("Please, Enter the Text to Encrypt:");
				text = bReader.readLine();
				//Not text bigger than 30 chars allowed
				while(text.length() > 30) {
					System.out.println("Smart Option doesn't allow Text bigger than '30 Chars'");
					System.out.println("Please, Enter a Valid Text:");
					text = bReader.readLine();
				}
				break;
				//3 asks for Decryption	
			case 3:
				//Asks for the Text to Decrypt, read the Input from the user with the BufferedReader
				System.out.println("Please, Enter the Text to Decrypt:");
				text = bReader.readLine();
				//Limitation, no bigger than 60 chars (double than Encryption as the Encryption creates 2 chars for every single char in Encryption)
				while(text.length() > 60) {
					System.out.println("Smart Option doesn't allow Text bigger than '60 Chars'");
					System.out.println("Please, Enter a Valid Decryption Text:");
					text = bReader.readLine();
				}
				break;
			default:
				//There's no default, as there's no Input from User to the Switch option (is the one in SmartMode)
				break;
			}
		}

		return text;
	}

	private String plainData() throws IOException{
		String plainData = bReader.readLine();
		return plainData;
	}

}
