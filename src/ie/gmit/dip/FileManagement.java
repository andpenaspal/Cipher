package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileManagement {

	//Instance variables
	//IO Object to call the Scanner later
	IO io = new IO();
	String inputFile;
	String outputFile;

	//Constructor
	public FileManagement() {

	}

	//Public Method called from Menu
	//Redirect following the option (encrypt - decrypt from Menu)
	//Pass the custom Square If there's one

	public void fileManager (int option, char[][] customSquare) throws IOException{
		writer(option, customSquare);
	}

	private void writer(int option, char[][] customSquare) throws IOException{

		String key = io.sInputManager(1, 0);

		//Create a BufferedReader to read the File with the Text
		BufferedReader bFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileManager())));
		//Create a FileWriter object to write into the File where you want to save the Encryption
		FileWriter fileWriter = new FileWriter(outputFile());
		//Create a Polybius object with the key as paramenter to Encrypt
		Polybius cipher = new Polybius(key);	
		//String Line to read Line-by-Line
		String line = null;
		//String to save the Encryption
		String text = null;

		//While there's more lines to read
		while((line = bFileReader.readLine()) != null){
			switch(option) {
			case 1:
				//Encrypting if there's no custom square
				if(customSquare == null) {
					text = cipher.encrypt(line.toUpperCase());
					//Encrypting with customSquare	
				}else {
					text = cipher.encrypt(line.toUpperCase(), customSquare);
				}
				break;
			case 2:
				//Encrypting
				if(customSquare == null) {
					text = cipher.decrypt(line.toUpperCase());
				}else {
					text = cipher.decrypt(line.toUpperCase(), customSquare);
				}
				break;
			default:
				break;
			}

			//Write line by line in the File and change line after each one
			fileWriter.write(text + "\n");
		}

		//Closing Readers/Writer/Scanner, there's no more Input

		bFileReader.close();
		fileWriter.flush();
		fileWriter.close();

		//Print the name of the file created saving the cipher text
		System.out.println("\nText sucessfully saved on " + outputFile +
				"\n~ Polybius Cipher has been closed ~");
	}

	private File inputFileManager() throws IOException{
		selection();
		//Create a File object with the name from the Input
		File fInputFile = new File(inputFile);
		//Check if the file exists
		if(fInputFile.exists()) {
			System.out.println("File selected successfully");
		}else {
			System.out.println("There's no file with the specified name. \n~ Polybius Cipher has been closed ~");
			System.exit(0);
		}
		return fInputFile;
	}

	private void selection() throws IOException{
		//Ask for write or select File and Read the Input
		System.out.println("Please, select on option: \n1) Select File from List \n2) Write the name of the File");
		int option = io.iInputManager();
		//Make sure the option is correct
		while(!(option == 1 || option == 2)) {
			//If other selection, Try again
			System.out.println("Selection not found. \nPlease, Try Again:");
			option = io.iInputManager();
		}

		//Switch following the option selected
		//1) Selection. 2) Write

		switch (option) {
		case 1:
			list();
			break;
		case 2:
			write();
			break;
		default:
			break;
		}

	}

	private void list() throws IOException{
		System.out.println("Please, select one of the Files to Import:");
		//Create a File who reads from the default folder of the user and list the files there into an array of files
		File folder = new File(System.getProperty("user.dir"));
		//Create a File Array with the list of files
		File[] listOfFiles = folder.listFiles();

		//Local variables to specify the files listed and the numerical selection
		String defName = ".txt";
		String defName2 = "_customSquare.txt";
		int x = 0;
		int y = 0;
		//String Array with the names of the Files sorted
		String[] names = new String[listOfFiles.length];

		//Loop over the listOfFiles, and if it's a File, assign its name to a variable
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String name = listOfFiles[i].getName();
				//If the name of the File ends with "defName" but not with "defName2", show it in a numeric list and assign the name to a position in a String array
				if((name.regionMatches(name.length() - 4, defName, 0, 4)) && (!name.regionMatches(name.length() - 17, defName2, 0, 17))) {
					//"x + 1" to start selection in "1" and not in "0"
					System.out.println((x + 1) + ") " + listOfFiles[i].getName());
					names[y] = listOfFiles[i].getName();
					//+1 to the position of the String Array and the Numeric list shown
					y += 1;
					x += 1;		  
				} 
				//If not files found
			}else if(i == 0){
				System.out.println("No Files found");
				System.exit(1);
			}
		}
		//Select the numerical option from the List shown over the loop
		//Take the name of the file being the selection the position in the "names" array (x = y earlier)
		//Assign the name selected to the variable of the file to import

		int selection = io.iInputManager();
		//Check if the selection exist
		while(selection > x || selection == 0) {
			System.out.println("Selection not found. Please Try Again:");
			selection = io.iInputManager();
		}


		inputFile = names[selection -1];
		//Show selection made
		System.out.println("You've selected: " + inputFile);
	}

	private void write() throws IOException{
		//Ask for the name of the file and read the Input with the BufferedReader
		System.out.println("Please, Enter the name of the file to Import:");
		//Assign the Input to the variable and add the "txt" termination
		inputFile = io.sInputManager(3, 0);
		inputFile = inputFile + ".txt";
	}

	private File outputFile() throws IOException{
		//Ask for the name of the File to create saving the Encryption when done
		//Assign the input to variable and append ".txt"
		System.out.println("Please, Enter the name for the file where you want to save the encryption:");
		String outputFileForEncryption = io.sInputManager(3, 0);

		io.closeIO();

		outputFileForEncryption = outputFileForEncryption + ".txt";
		outputFile = outputFileForEncryption;

		//Create a File object with the name from the Input to save the Data
		File outputFile = new File (outputFileForEncryption);

		//If the file is created succesfully, ask for the key, read it with the BufferedReader 
		if(outputFile.createNewFile()) {

		}else {
			System.out.println("A file with this name already exist. \n~ Polybius Cipher has been closed ~");
			System.exit(0);
		}

		return outputFile;
	}
}
