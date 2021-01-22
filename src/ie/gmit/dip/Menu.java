package ie.gmit.dip;

import java.io.IOException;

public class Menu {

	//Instance Variables
	//IO Object to call the Scanner later
	IO io = new IO();
	//Variable to redirect following the Menu Option Selected
	private int redirect;
	//Key to Encrypt/Decrypt
	private String key;
	//Text to Encrypt/Decrypt
	private String text;
	//Custom Square (Smart mode)
	private char[][] customSquare;
	//To redirect if there's a Custom Square
	boolean isCustomSquare;

	//Constructor
	public Menu() {

	}

	/*
	 Menu Overview:
	 Main Menu:
	 	1) Encrypt
	 		1)Text -> Key() -> Text() -> (TextManagement)
	 		2)File -> (File Management)
	 		3)Go Back
	 	2) Decrypt
	 		1)Text -> Key() -> Text() -> (TextManagement)
	 		2)File -> (File Management)
	 		3)Go Back
	 	3) Smart Menu
	 		1)Direct Encryption-Decryption -> Key() -> Text() -> (TextManagement)
	 		2)Encryption Showing Works -> Key() -> Text() -> (TextManagement)
	 		3)Decryption Showing works -> Key() -> Text() -> (TextManagement)
	 		4)Direct Encryption-Decryption Showing Works -> Key() -> Text() -> (TextManagement)
	 		5)Square Constructor Menu
	 			1)Write your own Square -> (Square Constructor)
	 			2)Import Square -> (SquareConstructor)
	 			3)Go Back
	 		6)Go Back
	 	4) Quit
	 */

	//Method called from Runner
	public void menu () throws IOException{
		System.out.println("\n################################################");
		System.out.println("Welcome to the Polybius Cipher   By Andres Penas");
		System.out.println("################################################");

		mainMenu();
	}

	//Method called to set the Custom Square from the Square Constructor Mode
	public void menu (char[][] customSquare) throws IOException{
		//Call the method to set the Custom Square and its boolean
		setCustomSquare(customSquare);
	}

	//Main Menu with the options
	private void mainMenu() throws IOException{
		//Try-Catch
		try {
			//Options
			System.out.println("\n#### Main Menu ####");
			System.out.println("\nPlease, select one option (1-4):");
			System.out.println("1) Encrypt \n2) Decrypt \n3) Smart Menu \n4) Quit");

			//Open Scanner, read the next Int and assign the value to the variable

			int mainMenuOption = io.iInputManager();

			//Assign the value to the Instance variable
			//? Instead of creating an Instance variable I could pass it through the method. Which method would be better and why?
			redirect = mainMenuOption;

			//Switch statement with the Input and calling the method for each option
			switch(mainMenuOption) {
			case 1:
				encryptMenu();
				break;

			case 2:
				decryptMenu();
				break;

			case 3:
				smartMenu();
				break;

			case 4:
				System.out.println("\n~ Polybius Cipher has been closed ~");
				//To close the program
				System.exit(0);
				break;
				//If selection isn't one of the options:
			default:
				System.out.println("Option not found. Please, select one of the options avaliable");
				//Try again
				mainMenu();
				break;
			}
			//Catch the error, print it and close	
		}catch(Exception e) {
			System.out.println(e.getMessage() + "\n\nAn error has ocurred \n~ Polybius Cipher has been closed ~");
			//Tried but couldn't find the way to specifically Catch the error if the Input is not an Int
			//Tried with another "catch" block, but got an error cause "Exception" already catches it (?)
			//Tried with a While loop with "in.hasNextInt()" but the Try-Catch catches it automatically
		}
	}

	//Sub-menu for Encryption, called from Main-Menu
	private void encryptMenu() throws IOException{

		System.out.println("\n#### Encryption Menu #### \n\nPlease select your option:");
		System.out.println("1) Text \n2) File \n3) Back to Main Menu");

		//Input assigned to the variable
		int encryptMenuOption = io.iInputManager();

		//Switch with the Input
		switch(encryptMenuOption) {
		//Text Encryption
		case 1:
			//Assign to the variables the return of the methods from IO Class			
			key = io.sInputManager(1, redirect);
			text = io.sInputManager(2, redirect);
			//Close IO
			io.closeIO();

			//New object of the class "TextManagement"
			TextManagement textManagement = new TextManagement();
			//If there's a Custom Square (Boolean Ins Var), call the method with it (char[][]), if it's not, without it
			if(isCustomSquare) {
				textManagement.textManagement(redirect, key, text, customSquare);
			}else {
				textManagement.textManagement(redirect, key, text, null);
			}
			break;
			//File Encryption
		case 2:
			//Same as above but with the FileManagement class to handle Files
			FileManagement fileManagement = new FileManagement();
			if(isCustomSquare) {
				fileManagement.fileManager(redirect, customSquare);
			}else {
				fileManagement.fileManager(redirect, null);
			}
			break;
			//Go back to Main Menu	
		case 3:
			mainMenu();
			break;
			//Option not found, try again	
		default:
			System.out.println("Option not found. Please, select one of the options avaliable");
			encryptMenu();
			break;
		}
	}

	//The same that Encryption but for Decryption
	private void decryptMenu() throws IOException{
		//Menu Options
		System.out.println("\n#### Decryption Menu #### \n\nPlease select your option:");
		System.out.println("1) Text \n2) File \n3) Back to Main Menu");
		//Scanner to read the Input from the System.in
		int decryptMenuOption = io.iInputManager();
		//Switch with the Input
		switch(decryptMenuOption) {
		case 1:
			//Methods to ask for Key and Text with the redirection (Encryption or Decryption)


			key = io.sInputManager(1, redirect);
			text = io.sInputManager(2, redirect);

			io.closeIO();

			//New object "TextManagement" class
			TextManagement textManagement = new TextManagement();
			//Call the method with or without the custom Square
			if(isCustomSquare) {
				textManagement.textManagement(redirect, key, text, customSquare);
			}else {
				textManagement.textManagement(redirect, key, text, null);
			}
			break;
		case 2:
			//Same for files
			FileManagement fileManagement = new FileManagement();
			if(isCustomSquare) {
				fileManagement.fileManager(redirect, customSquare);
			}else {
				fileManagement.fileManager(redirect, null);
			}
			break;
			//Back to Main Menu	
		case 3:
			mainMenu();
			break;
		default:
			System.out.println("\nOption not found. Please, select one of the options avaliable");
			decryptMenu();
			break;
		}

	}

	//Method with the Smart Menu 
	private void smartMenu() throws IOException{

		//Options
		System.out.println("\n#### Smart Menu #### \n\nPlease select your option:");
		System.out.println("1) Direct Encryption-Decryption \n2) Encryption Showing Work "
				+ "\n3) Decryption Showing Work \n4) Direct Encryption-Decryption Showing Work "
				+ "\n5) Smart Square \n6) Back to Main Menu");
		//Variable assigned with the Input from the user
		int smartMenuOption = io.iInputManager();

		//Switch with the Input
		//First 4 options call the same method (different parameter following the selection)
		switch(smartMenuOption) {
		case 1:
		case 2:
		case 3:
		case 4:
			//Call the methods to ask for the KEY and Text
			//Methods have a different option for the Smart Menu (redirect = 3)
			//Text Method with SmartMenuOption is different (Overloaded method)

			key = io.sInputManager(1, redirect);
			text = io.sInputManager(2, redirect, smartMenuOption);

			io.closeIO();

			//New object from the class and call the method there with or without the Custom Square
			SmartManagement smartManagement = new SmartManagement();
			if(isCustomSquare) {
				smartManagement.smartManagement(smartMenuOption, key, text, customSquare);
			}else {
				smartManagement.smartManagement(smartMenuOption, key, text, null);
			}
			break;
		case 5:
			//Menu with the Smart Square Options
			smartSquareMenu();
			break;
		case 6:
			//Go Back
			mainMenu();
			break;
		default:
			//If selection not found, try again
			System.out.println("\nOption not found. Please, select one of the options avaliable");
			smartMenu();
			break;
		}
	}

	//Menu for the SmartSquare
	private void smartSquareMenu() throws IOException{
		//Options
		System.out.println("\n#### Smart Square Menu #### \n\nPlease select your option:");
		System.out.println("1) Write Your Own Square \n2) Create a Default Square File \n3) Import Square \n4) Back to Smart Menu");
		//Scanner to read the Input from user with the option
		int smartSquareMenuOption = io.iInputManager();
		//Create an object of the class to call methods later
		SquareConstructor squareConstructor = new SquareConstructor();
		//Switch with the input
		switch (smartSquareMenuOption) {
		case 1:
			//to write the Square			
			squareConstructor.squareConstructor();
			break;
		case 2:
			//Create a defualt square
			io.closeIO();
			squareConstructor.defaultSquare();
			break;
		case 3:
			//Import square
			squareConstructor.importSquare();
			break;
		case 4:
			//Go back
			smartMenu();
			break;
		default:
			//Selection not found, try again
			System.out.println("\nOption not found. Please, select one of the options avaliable");
			smartSquareMenu();
			break;
		}

	}

	//Method to Set the Custom Square, called from the Method menu overloaded
	private void setCustomSquare(char[][] square) throws IOException{
		//Set the Ins Var with the Custom Square and change the boolean to True (to redirect to the Enc-Dec with the Custom Square in the Menu selections)
		customSquare = square;
		isCustomSquare = true;
		//Go to the Main Menu with the variables assigned (now the Enc-Dec will work with the CustomSquare Options)
		mainMenu();
	}
}
