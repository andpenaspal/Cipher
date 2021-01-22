package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class SquareFileManagement {

	//Instace Variables
	IO io = new IO();

	public SquareFileManagement() {

	}

	//To Import a Custom Square
	public void squareFileManagement() throws IOException{
		importSquare();
	}

	//To Export a Custom Square
	public void squareFileManagement(char[] heading, char[] body) throws IOException{
		exportSquare(heading, body);
	}

	//To Export a Custom Square
	public void squareFileManagement(char[] heading, char[] body, boolean def) throws IOException{
		exportDefaultSquare(heading, body);
	}

	//Method to export a Square
	private void exportSquare(char[] heading, char[] body) throws IOException{
		//Ask for the name to create a file, read the Input and assign it to a variable, adding a suffix to locate it easier in the future
		System.out.println("Please, Enter the name of the file to save the custom Square:");

		String customSquare = io.sInputManager(3, 0);

		customSquare = customSquare + "_customSquare.txt";
		//Create a nuew object File with the input
		File customSquareFile = new File(customSquare);
		//If the file is created
		if(customSquareFile.createNewFile()) {
			//New FileWriter to write into the file object
			FileWriter fileWriter = new FileWriter(customSquareFile);

			//Write the heading of the Square
			//In this way to make it more fancy in the Doc
			//"\n" didn't work (Idk why) so I had to use the "String.format..."
			for(int i = 0; i < heading.length; i++) {
				fileWriter.write(heading[i]);
				if(i == heading.length - 1) {
					fileWriter.write(String.format("%n"));
				}
			}
			//Write the Body of the Square
			for(int i = 0; i < body.length; i++) {
				if(i % 6 == 0)fileWriter.write( String.format("%n"));
				fileWriter.write(body[i]);
			}

			fileWriter.flush();
			fileWriter.close();

			//Successful text
			System.out.println("\nCustom Square sucessfully saved on '" + customSquare + "'");

		}else {
			//If the name where to save the Square already exist and the file can't be created
			System.out.println("A file with this name already exist. \n~ Polybius Cipher has been closed ~");
			System.exit(0);
		}
	}
	//Method to export the Default Square
	private void exportDefaultSquare(char[] heading, char[] body) throws IOException{
		String defaultCustomSquare = "Default_customSquare.txt";
		//Create a nuew object File with the input
		File customSquareFile = new File(defaultCustomSquare);
		//If the file is created
		if(customSquareFile.createNewFile()) {
			//New FileWriter to write into the file object
			FileWriter fileWriter = new FileWriter(customSquareFile);

			//Write the heading of the Square
			//In this way to make it more fancy in the Doc
			//"\n" didn't work (Idk why) so I had to use the "String.format..."
			for(int i = 0; i < heading.length; i++) {
				fileWriter.write(heading[i]);
				if(i == heading.length - 1) {
					fileWriter.write(String.format("%n"));
				}
			}
			//Write the Body of the Square
			for(int i = 0; i < body.length; i++) {
				if(i % 6 == 0)fileWriter.write( String.format("%n"));
				fileWriter.write(body[i]);
			}

			fileWriter.flush();
			fileWriter.close();

			//Successful and close
			System.out.println("\n" + defaultCustomSquare + " successfully saved on '" + System.getProperty("user.dir") + "'" +
					"\n~ Polybius Cipher has been closed ~");
		}else {
			//If the name where to save the Square already exist and the file can't be created
			System.out.println("A file with the name '" + defaultCustomSquare + "' already exist. \n~ Polybius Cipher has been closed ~");
			System.exit(0);
		}
	}

	//Method to import a custom Square
	private void importSquare() throws IOException{

		System.out.println("Please, select one of the Squares to Import:");
		//Create a file object with the data of the Default Folder
		File folder = new File(System.getProperty("user.dir"));
		//Create a File Array with the list of files of the Data of the Default Folder
		File[] listOfFiles = folder.listFiles();
		//Suffix for the Squares
		String defName = "_customSquare.txt";
		//Local variables for the loop
		int x = 0;
		int y = 0;
		//String Array with the names sorted
		String[] names = new String[listOfFiles.length];

		//Loop over the list of files and if it's a file, assign the name to a variable
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String name = listOfFiles[i].getName();
				//If the name has the Suffix desired (is a custom square), list it in a numerical list and assign the name to the Names Array
				if(name.regionMatches(name.length() - 17, defName, 0, 17)) {
					System.out.println((x + 1) + ") " + listOfFiles[i].getName());
					names[y] = listOfFiles[i].getName();
					//Next position in list and in Array
					y +=1;
					x += 1;		  
				} 
			}else if (i == 0){
				//If there's no files
				System.out.println("No Files found");
				System.exit(1);
			}
		}
		//If there's Files in the folder but there's no Squares:
		//It's not fancy (It shows the message of "select Square") but is the only way I could figure out
		if(y == 0) {
			System.out.println("There's no Squares avaliable \nIf you think there's is, please, check the suffix of the File. It must be '_customSquare.txt'");
			System.out.println("\n~ Polybius Cipher has been closed ~");
			System.exit(0);
		}

		//Take Input from user, assign it to a variable

		int option = io.iInputManager();
		//Check if the selection exist
		while(option > x || option == 0) {
			System.out.println("Selection not found. Please Try Again:");
			option = io.iInputManager();
		}

		//The Input is at the same time the position in the Names Array
		//Assign the name to the variable to import a square
		String importSquare = names[option -1];

		//Create a File obect with the selection
		File importSquareFile = new File(importSquare);

		//Check if the file exists
		if(importSquareFile.exists()) {
			System.out.println("The Custom Square '" + importSquare + "' Has Been Selected");
		}else {
			System.out.println("There's no file with the specified name. \n~ Polybius Cipher has been closed ~");
			System.exit(0);
		}

		//Buffered reader to read the file
		BufferedReader bFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(importSquareFile)));
		//Local variable to read by Line & StringBuilder to append them
		String line = null;
		StringBuilder sb = new StringBuilder();
		//Lopp to read Line by Line and append them
		while((line = bFileReader.readLine()) != null){
			sb.append(line.toUpperCase());
		}
		//Variable with all imported data
		String importedCustomSquareAll = sb.toString();

		bFileReader.close();

		//Create an object of the class and call the method with the imported data
		SquareConstructor importedSquare = new SquareConstructor();
		importedSquare.importedSquare(importedCustomSquareAll);

	}

}
