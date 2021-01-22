package ie.gmit.dip;

import java.io.IOException;
import java.util.Arrays;

public class SquareConstructor {

	//Instance variables
	IO io = new IO();
	SquareFileManagement squareFileManagement = new SquareFileManagement();
	String heading;
	String bodyLines;
	char[] headingChar, bodyToCharAll;
	char[][] customSquare;
	//Constructor
	public SquareConstructor() {

	}
	//Public Method to access the class from other classes to construct a square
	public void squareConstructor() throws IOException{
		constructingSquare();
	}
	//Public method to import a square
	public void importSquare() throws IOException{
		importSquareManager();
	}
	//Public method to work around the imported Square
	public void importedSquare(String squareAll) throws IOException{
		setImportedSquare(squareAll);
	}
	//Public method to create a Default Square Text File
	public void defaultSquare() throws IOException{
		defaultSquareConstructor();
	}

	//Method to Start Constructing the custom square
	private void constructingSquare() throws IOException{
		System.out.println("Welcome to the Square Constructor!");
		System.out.println("\nThe Square is a 7x7 Square, But you only need to specify the Head and the 6x6 internal Matrix");
		//Calling the different methods to ask and work around the data
		enterHeading();
		enterBody();
		bodyToChar();
		integrateHeadingsSquare();
		integrateBodySquare();
		exportSquare();

	}
	//Method for the heading
	private void enterHeading() throws IOException{
		//Ask for the heading
		System.out.println("\nPlease, Enter the Heading:");
		System.out.println("(Quick Instructions: Enter 6 simple alpha-numerical values. You can't have the same value twice)");
		//Read the input and assign it to a variable
		String heading = io.sInputManager(3, 0);
		//Make sure the input are no more neither less than 6 characters
		while(heading.length() < 6 || heading.length() > 6) {
			//If it's, say it and ask again
			System.out.println("The Heading must be 6 different Chars. \nPlease, try again:");
			heading = io.sInputManager(3, 0);
		}
		//Assign the variable to the Ins Val
		this.heading = heading;
		//Call the method to transform the String into a Char[]
		headingToChar();
	}
	//Method to convert the String Heading into a Char[]
	private void headingToChar() throws IOException{
		//Assign the Ins Val to the new variable
		char[] headingChar = heading.toCharArray();
		//Loop recursively over the array to make sure there's no same char twice
		//Loop recursively and not just "x = i + 1" to compare each char[i] with all chars and not just with the next one
		for(int i = 0; i < headingChar.length; i++) {
			for (int x = i + 1; x < headingChar.length; x++) {
				//If there's the same char twice, show which one and close
				if(headingChar[i] == headingChar[x]) {
					System.out.println("There can't be the same Char twice in the Heading.");
					System.out.println("(" + (headingChar[i]) + ")");
					System.out.println("~ Polybius Cipher Has Been Closed ~");
					System.exit(0);
					//Original idea: Show the problem (same char twice), show the char (i) and ask to repeat again
					//If I send it back to the last method I'd have two inputs (this line of work don't stop, just goes back and 
					//does it with two lines of work. If I ask to print the result at the end of the method, they print twice. 
					//The flow continues here after doing it all again. It goes to "enterHeading", after that to "Heading to char" and when it finishes it goes back here and continues.
					//I don't know how to "cancel" this line and go back to "Enter Heading". So i've just close the program, not fancy at all.
				}
			}
		}
		//Assign the Array to the Ins Val
		this.headingChar = headingChar;
		//Show the selection
		System.out.println("\nYour heading is: " + Arrays.toString(headingChar));

	}
	//Method to Enter the body
	private void enterBody() throws IOException{
		//String Builder to append all lines
		StringBuilder body = new StringBuilder();
		//Ask for body and show instruction
		System.out.println("\nPlease, Enter the Body:");
		System.out.println("Quick Instructions: Enter 6 simple alpha-numerical values 6 times. You can't have the same value twice");

		//Loop over the Rows of the Square (same as heading)
		for(int i = 0; i < headingChar.length; i++) {
			//Show Current Line
			System.out.println("Line " + (i + 1) + ":");
			//Assign the Input to the variable
			String bodyLine = io.sInputManager(3, 0);
			//Make sure each Line has no more neither less than 6 chars
			while(bodyLine.length() < 6 || bodyLine.length() > 6) {
				//If it's it, say it and try again
				System.out.println("The Line must be 6 different Chars. \nPlease, try again:");
				System.out.println("Line " + (i + 1) + ":");
				bodyLine = io.sInputManager(3, 0);
			}
			//If the Line is correct, append it with the String Builder
			body.append(bodyLine);

		}
		//Assign the String Builder String to the Ins Var to convert it to Char Array in other method
		this.bodyLines = body.toString();
		//Call the method to convert the String into a Char Array
		bodyToChar();
	}
	//Method to Convert the Body String into a Char Array
	private void bodyToChar() throws IOException{
		//Char[] for all the lines together
		char[] bodyToCharAll = bodyLines.toCharArray();
		//Loop recursively over the array to make sure there's no the same char twice
		for(int i = 0; i < bodyToCharAll.length; i++) {
			for (int x = i + 1; x < bodyToCharAll.length - 1; x++) {
				//If there's the same char twice, say it and kill the program
				if(bodyToCharAll[i] == bodyToCharAll[x]) {
					System.out.println("There can't be the same Char twice in the Body.");
					System.out.println("(" + bodyToCharAll[i] +")");
					System.out.println("~ Polybius Cipher Has Been Closed ~");
					System.exit(0);
				}
			}
		}
		//Assign the local variable to the Ins Var
		this.bodyToCharAll = bodyToCharAll;

	}
	//Method to integrate the Heading into the char[][]
	private void integrateHeadingsSquare() {
		//Create a char[][] with the same rows/cols (length + 1 for the empty char on the top left corner)
		char[][] customSquare = new char[headingChar.length + 1][headingChar.length + 1];
		//Loop over the Heading char[] and the char[][] and integrate the Heading into the first row
		for(int i = 0; i < headingChar.length; i++) {
			for(int row = 0; row < customSquare.length; row++) {
				for(int col = 0; col < customSquare[row].length; col++) {
					//Top left corner empty
					if(row == 0 && col == 0) {
						customSquare[0][0] = '\u0000';
					} 
					//Rest of first row
					if(row == 0 && col > 0) {
						customSquare[row][col] = headingChar[i];
						i++;
					}
				}
			}
		}
		//Loop over the Heading Char[] and the char[][] and integrate the heading into the first column
		for(int i = 0; i < headingChar.length; i++) {
			for(int row = 0; row < customSquare.length; row++) {
				for(int col = 0; col < customSquare[row].length; col++) {
					//Ignore the top left corner
					if(row == 0 && col == 0) continue;
					if(row > 0 && col == 0) {
						customSquare[row][col] = headingChar[i];
						i++;
					}
				}
			}
		}
		//Assign the char[][] to the Ins Var
		this.customSquare = customSquare;
	}
	//Method to integrate the body into the char[][] 
	private void integrateBodySquare() {
		//Loop over the Body and the Char[][](ignoring first Row/Col) and assign the Body to the positions
		for(int i = 0; i < bodyToCharAll.length; i++) {
			for(int row = 1; row < customSquare.length; row++) {
				for(int col = 1; col < customSquare[row].length; col++) {
					customSquare[row][col] = bodyToCharAll[i];
					i++;
				}
			}
		}
		//Print the Custom Square
		System.out.println("\nYour custom Square is: \n");
		//Print the Custom Square row by row showing the Square
		for(int row = 0; row < customSquare.length; row++) {
			System.out.println("       " + Arrays.toString(customSquare[row]));
		}
	}
	//Method to export the Square or go to Main Menu enabling the CustomSquare
	private void exportSquare() throws IOException{
		//Ask, read the input and assign it to a variable
		System.out.println("\nWhat would you like to do next? \n1) Export Square and go to Main Menu enabling "
				+ "your Custom Square \n2) Go to the Main Menu enabling your Custom Square");

		int temp = io.iInputManager();
		//Make sure it's a valid input
		while (temp != 1 && temp != 2) {
			System.out.println("Option not found. \nPlease, Try again:");
			temp = io.iInputManager();
		}
		//Redirect following the Input
		switch (temp) {
		case 1:
			squareFileManagement.squareFileManagement(headingChar, bodyToCharAll);

			//Call methods to integrate the Char[] into the Char[][]
			integrateHeadingsSquare();
			integrateBodySquare();

			System.out.println("\nYour custom Square has been enabled! \n");
			//Go Main Menu with the Custom Square Enabled
			goMain();

			break;
		case 2:
			goMain();
			break;
		default:
			break;
		}
	}
	//Method to redirect to import the square
	private void importSquareManager() throws IOException{
		squareFileManagement.squareFileManagement();
	}
	//Method to set the imported square into the variables
	private void setImportedSquare(String squareAll) throws IOException{
		//Create a char[] and assign the values converting them into char[]
		char[] squareAllChars = squareAll.toCharArray();
		//Make sure it's the correct number of chars
		if(squareAllChars.length > 42 || squareAllChars.length < 42) {
			System.out.println("The lenght of the Square in invalid");
			System.exit(1);
		}

		//Create two new char[] for the Data
		char[] importedHeading = new char[6];
		char[] importedBody = new char[36];
		//Loop over the Imported Data and assign the first 6 chars to the Heading and the rest to the Body
		for(int i = 0; i < squareAllChars.length; i++) {
			if(i < 6) {
				importedHeading[i] = squareAllChars[i];
			}
			if(i >= 6) {
				importedBody[i - 6] = squareAllChars[i];
			}
		}
		//Make sure the Heding of the Square is correct (it's easy to change once is in .txt)
		//Check Heading/Body separately as they share chars
		for(int i = 0; i < importedHeading.length; i++) {
			for(int x = i + 1; x < importedHeading.length - 1; x++) {
				if(importedHeading[i] == importedHeading[x]) {
					System.out.println("There's the same Char (" + importedHeading[i] + ") in the Heading twice." +
							"\nThe Sqaure is not valid.");
					System.exit(1);
				}
			}
		}
		//Make sure the Body of the Square is correct (it's easy to change once is in .txt)
		for(int i = 0; i < importedBody.length; i++) {
			for(int x = i + 1; x < importedBody.length - 1; x++) {
				if(importedBody[i] == importedBody[x]) {
					System.out.println("There's the same Char (" + importedBody[i] + ") in the Body twice." +
							"\nThe Sqaure is not valid.");
					System.exit(1);
				}
			}
		}

		//Assign the local variables to the Ins Val
		headingChar = importedHeading;
		bodyToCharAll = importedBody;
		//Call methods to integrate the Char[] into the Char[][]
		integrateHeadingsSquare();
		integrateBodySquare();

		System.out.println("\nYour custom Square has been enabled! \n");
		//Go Main Menu with the Custom Square Enabled
		goMain();

	}
	//Method to create a Default Square text file
	private void defaultSquareConstructor() throws IOException{
		String defaultHeading = "asdfgh";
		String defaultBody = "abcdefghijklmnopqrstuvwxyz0123456789";
		boolean def = true;

		squareFileManagement.squareFileManagement(defaultHeading.toCharArray(), defaultBody.toCharArray(), def);
	}

	//Method to go to Main Menu with the Custom Square enabled
	private void goMain() throws IOException{
		Menu menu = new Menu();
		menu.menu(customSquare);
	}

}
