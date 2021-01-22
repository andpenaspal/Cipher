package ie.gmit.dip;

import java.util.Arrays;

public class Polybius {
	//Instance variables:
	//Encryption:
	//Number of rows in the Matrix 
	private int nrows;
	//Key
	private char[] key;
	//Matrix:
	private char[][] matrix;
	//Encryption Key (Key alphabetically ordered)
	private char[] encryptionKey;
	//Decryption:
	//Decription Key (Key alphabetically ordered)
	private char[] decryptionKey;
	//Number of rows in the Decryption Matrix
	private int decryptionNRows;
	//Decryption Matrix:
	private char[][] decryptionMatrix;

	//Polybius Square:
	private char[][] square = {
			{'\u0000', 'A', 'D', 'F', 'G', 'V', 'X'}, 
			{'A', 'P', 'H', '0', 'Q', 'G', '6'},
			{'D', '4', 'M', 'E', 'A', '1', 'Y'},
			{'F', 'L', '2', 'N', 'O', 'F', 'D'},
			{'G', 'X', 'K', 'R', '3', 'C', 'V'},
			{'V', 'S', '5', 'Z', 'W', '7', 'B'},
			{'X', 'J', '9', 'U', 'T', 'I', '8'}
	};

	//Constructor
	public Polybius(String key) {
		setKey(key);
	}

	//Key Method
	//Delete leading and trailing whitespaces from the Key, convert to upper case and into a char[]
	private void setKey(String key) {
		this.key = key.trim().toUpperCase().toCharArray();
	}
	//If there's a custom square, this method change the Default Square for the custom
	//Only called when there's a custom Square
	private void setCustomSquare(char[][] customSquare) {
		square = customSquare;
	}

	//Methods to Encrypt
	//Call the different methods to work around the text to Encrypt it
	public String encrypt(String plainText) {

		setNrows(plainText);
		setMatrix(plainText);
		setEncryptionKey();
		getColumnarTransposition();
		return readByCol().toString();

	}
	//Encryption method showing works for the "Smart Mode"
	public String smartEncrypt(String plainText) {
		System.out.println("\n### Encryption ###");
		System.out.println("\nPolybius Square:");
		for(int row = 0; row < square.length; row++) {
			System.out.println("                  " + Arrays.toString(square[row]));
		}
		System.out.println("\nKey: " + Arrays.toString(key));
		System.out.println("\nPlain Text: " + plainText);
		setNrows(plainText);
		setMatrix(plainText);
		System.out.println("\nMatrix:");
		for(int row = 0; row < matrix.length; row++) {
			System.out.println("       " + Arrays.toString(matrix[row]));
		}
		setEncryptionKey();
		System.out.println("\nEncryption Key:  " + Arrays.toString(encryptionKey));
		getColumnarTransposition();
		System.out.println("\nSorted Matrix:");
		for(int row = 0; row < matrix.length; row++) {
			System.out.println("       " + Arrays.toString(matrix[row]));
		}
		readByCol();

		return readByCol().toString();
	}

	//Method overloaded with the "Custom Square" parameter
	//Called to Encrypt when there's a Custom Square
	public String encrypt(String plainText, char[][] customSquare) {

		setCustomSquare(customSquare);
		setNrows(plainText);
		setMatrix(plainText);
		setEncryptionKey();
		getColumnarTransposition();
		return readByCol().toString();

	}

	//Method overloaded with the "Custom Square" parameter
	//Called to Encrypt Showing works (Smart Mode) when there's a Custom Square
	public String smartEncrypt(String plainText, char[][] customSquare) {

		setCustomSquare(customSquare);
		System.out.println("\n### Encryption ###");
		System.out.println("\nPolybius Square:");
		for(int row = 0; row < square.length; row++) {
			System.out.println("                  " + Arrays.toString(square[row]));
		}
		System.out.println("\nKey: " + Arrays.toString(key));
		System.out.println("\nPlain Text: " + plainText);
		setNrows(plainText);
		setMatrix(plainText);
		System.out.println("\nMatrix:");
		for(int row = 0; row < matrix.length; row++) {
			System.out.println("       " + Arrays.toString(matrix[row]));
		}
		setEncryptionKey();
		System.out.println("\nEncryption Key:  " + Arrays.toString(encryptionKey));
		getColumnarTransposition();
		System.out.println("\nSorted Matrix:");
		for(int row = 0; row < matrix.length; row++) {
			System.out.println("       " + Arrays.toString(matrix[row]));
		}
		readByCol();

		return readByCol().toString();
	}

	//Encryption: Locate the chars of the Plain Text in the Polybius Square 
	//Get the far left char and the top char
	//Add them into a Matrix, being the first row the Key
	//Transpose the columns of the Matrix by alphabetical order
	//Read the Matrix by columns: Ciphered Text

	//Method 1: Locate the Char of the PlainText in the Polybius Square
	//Get the side Chars of the Square for the encryption

	//1.1: Get the far left side letter on the row
	//Loop over the Square to find matches with the Plain Text
	private char getRow(char plain){
		for (int row = 1 ; row < square.length; row++){ //Looping from the second row
			for (int col = 1; col < square[row].length; col++){ //And looping from the second col
				if (square[row][col] == plain){ //If finds a char from plain
					return square[row][0];	//Return the far left side letter on the row where find                 				 	                   
				}

			}
		}    
		return plain; //If not find, return the char of Plain
	}

	//1.2: Get the top letter on the col
	//Loop over the Square to find matches with the Plain Text
	private char getCol(char plain){
		for (int row = 1 ; row < square.length; row++){ //Looping from the second row
			for (int col = 1; col < square[row].length; col++){ //And looping from the second col
				if (square[row][col] == plain){ //If finds a char from plain
					return square[0][col];	//Return the top letter on the column where find                 				 	                   
				}

			}
		}    
		return plain; //If not find, return the char of Plain
	}

	//Method 2: Set the number of Rows needed for the Matrix
	private void setNrows(String plainText) {
		if ((plainText.length() * 2) % key.length > 0) { //If there's remainder in the division between the length of the Plain Text and the key (N* of cols)
			nrows = 2 + ((plainText.length() * 2) / key.length); //Add two rows (key+remainder chars) to the rows needed (2 chars for each plain text char, 0-Row and 0-Col in the Square)
		}else { //If there's no remainder
			nrows = 1 +((plainText.length() * 2) / key.length); //Add 1 row (key) to the rows needed (2 chars for each plain text char, 0-Row and 0-Col in the Square)
		}		
	}

	//Method 3: Create a matrix
	//First Row: Key
	//Rest of Rows: sides of the Polybius Square (Row, Col)
	private void setMatrix(String plainText){
		char[][] matrix = new char[nrows][key.length]; //Create a Matrix (Rows: Nrows (Method 2), Cols: length of the Key)

		//Create a StringBuilder to create a String with the 0-Row and 0-Col of the Square (Method 1)
		StringBuilder sides = new StringBuilder();
		for (int i = 0; i < plainText.length(); i++) {
			sides.append(getRow(plainText.toUpperCase().charAt(i)));
			sides.append(getCol(plainText.toUpperCase().charAt(i)));
		}

		//Loop over the Matrix and assign: the row1 to the key, the rest of rows to the String with the 0-Row 0-Col
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				if (row < 1) {
					for (int i = 0; i < key.length; i++) {
						matrix[row][col] = key[i];
						col++;
						if (row > 1 || col == key.length) {
							break;
						}
					}
				}else if (row > 0){
					for (int i = 0; i < sides.length(); i++) {
						matrix[row][col] = sides.charAt(i);
						col++;
						if (col == key.length) { //Change of row when it's full
							row++;
							col = 0;
						}
					}
					break;
				}
			}	
		}

		this.matrix = matrix;	//Assign to the Instance Variable the Local Matrix
	}

	//Method 4: Set Encryption Key
	//Sort the key alphabetically
	private void setEncryptionKey() {

		char[] encryptionKey = new char[key.length];

		for(int i = 0; i < key.length; i++) {
			encryptionKey[i] = key[i];
		}

		Arrays.sort(encryptionKey);

		this.encryptionKey = encryptionKey;
	}

	//Method 5: Columnar transposition
	//Transpositions of Cols by alphabetical order
	private char[][] getColumnarTransposition(){

		char[][] matrix = new char [nrows][key.length];
		char[][] temp = new char[nrows][key.length]; //New Matrix Temp to swap cols

		//Copy the Key into the new 2D Array
		for(int row = 0; row < matrix.length; row++) {
			for(int col = 0; col < matrix[row].length; col++) {
				matrix[row][col] = this.matrix[row][col];
			}
		}

		//Do not Swap: doesn't respect the order with random chars or same chars duplicated in the Key
		//Copy the values into a new char[][] -> It's done by first-founf-first-move order, same for Decryption

		//Loop over the Matrix and the EncryptionKey and compare the value of the first Row
		//Compare values of the first Row (Key and EncryptionKey) to move the value in the same col but in different Row
		//i: Key, compared to the matrix[0][col] (first row of the Matrix)
		//When i finds the same value in the MatrixKey, takes the value in whatever row is looking [row] and in this [col] and moves it to the [i] col (ordered)
		for(int i = 0; i < encryptionKey.length; i++) {
			for (int row = 1; row < matrix.length; row++) {
				for (int col = 0; col < matrix[row].length; col++) {
					if (encryptionKey[i] == matrix[0][col]) { //If found, copy the value in that col 

						//If the value is already moved (empty), ignore it and continue (not overwrite);
						if(matrix[row][col] == '\0') continue;
						//If the position in the Temp[][] is filled, ignore it and continue (not overwrite, for same chars duplicated)
						if(temp[row][i] != '\0') continue;

						//Copy the value in the same row but in the col specified by the ordered key [i]
						temp[row][i] = matrix[row][col];	
						//Once moved, empty the place in the Matrix to not overwrite
						matrix[row][col] = '\0';				
					}
				}
			}
		}
		//Once finished, copy the Encryption Key to the first Row of the new char[][] (for showing works)
		for(int col = 0; col < key.length; col++) {
			for(int i = 0; i < encryptionKey.length; i++) {
				temp[0][col] = encryptionKey[i];
				col++;
			}
		}

		//Assign the temp[][] to the Instance variable		
		this.matrix = temp;

		return this.matrix;
	}

	//Method 6: Read by column
	//Read the Reorderer Matrix by columns: Ciphered Text
	private StringBuilder readByCol() {
		//Create a StringBuilder to create a String
		StringBuilder byCol = new StringBuilder();

		//Loop over the Reorderer Matrix (by col, not by row, and not the first Row, as it's the key) and append the chars to the String
		for (int col = 0; col < key.length; col++) {
			for (int row = 1; row < matrix.length; row++) {
				//If the position in the Array is empty, write "~" symbol. To avoid missing leading or trailing whitespaces in direct decryption (when the user writes it)
				//The "~" will be ignored in the decryption but used to fill the matrix
				if(matrix[row][col] == '\u0000') {
					byCol.append('~');
				}else {
					byCol.append(matrix[row][col]);
				}
			}
		}
		return byCol; //Ciphered Text
	}


	//Methods to Decrypt
	//Call the different methods to work around the Ciphered text to Decrypt it
	public String decrypt(String cipherText2) {

		setDecryptionKey();
		setDecryptionNRows(cipherText2);
		getDecryptionMatrix(cipherText2);
		getDecryptionColumnarTransposition();

		return getDecryptedText().toString();
	}

	//Call the different methods to Decrypt showing works (Smart Mode)
	public String smartDecrypt(String cipherText) {
		System.out.println("\n### Decryption ###");
		System.out.println("\nPolybius Square:");
		for(int row = 0; row < square.length; row++) {
			System.out.println("                  " + Arrays.toString(square[row]));
		}
		System.out.println("\nKey: " + Arrays.toString(key));
		setDecryptionKey();
		System.out.println("\nDecryption Key: " + Arrays.toString(decryptionKey));
		System.out.println("\nCiphered Text: " + cipherText);
		setDecryptionNRows(cipherText);
		getDecryptionMatrix(cipherText);
		System.out.println("\nDecryption Matrix: ");
		for(int row = 0; row < decryptionMatrix.length; row++) {
			System.out.println("       " + Arrays.toString(decryptionMatrix[row]));
		}
		getDecryptionColumnarTransposition();
		System.out.println("\nSorted Decryption Matrix: ");
		for(int row = 0; row < decryptionMatrix.length; row++) {
			System.out.println("       " + Arrays.toString(decryptionMatrix[row]));
		}
		getDecryptedText();


		return getDecryptedText().toString();
	}

	//Overloaded Method called to Decrypt when there's a custom Square
	public String decrypt(String cipherText2, char[][] customSquare) {

		setCustomSquare(customSquare);
		setDecryptionKey();
		setDecryptionNRows(cipherText2);
		getDecryptionMatrix(cipherText2);
		getDecryptionColumnarTransposition();

		return getDecryptedText().toString();
	}

	//Overloaded Method called to Decrypt showing works when there's a custom Square
	public String smartDecrypt(String cipherText, char[][] customSquare) {
		setCustomSquare(customSquare);
		System.out.println("\n### Decryption ###");
		System.out.println("\nPolybius Square:");
		for(int row = 0; row < square.length; row++) {
			System.out.println("                  " + Arrays.toString(square[row]));
		}
		System.out.println("\nKey: " + Arrays.toString(key));
		setDecryptionKey();
		System.out.println("\nDecryption Key: " + Arrays.toString(decryptionKey));
		System.out.println("\nCiphered Text: " + cipherText);
		setDecryptionNRows(cipherText);
		getDecryptionMatrix(cipherText);
		System.out.println("\nDecryption Matrix: ");
		for(int row = 0; row < decryptionMatrix.length; row++) {
			System.out.println("       " + Arrays.toString(decryptionMatrix[row]));
		}
		getDecryptionColumnarTransposition();
		System.out.println("\nSorted Decryption Matrix: ");
		for(int row = 0; row < decryptionMatrix.length; row++) {
			System.out.println("       " + Arrays.toString(decryptionMatrix[row]));
		}
		getDecryptedText();

		return getDecryptedText().toString();
	}

	//Decryption: Create a Matrix with the Ciphered Text (first Row - Key, assign values by Cols, as was readed to Cipher)
	//Reorder the Matrix Cols by the Key (original order): Columnar transposition
	//Read the chars in pairs to match the 0-Row 0-Col of the Square to get the Char inside of the Square: Decrypted text

	//Method 1: Alphabetical order of the key (as it was in the last Matrix in Encryption)
	private void setDecryptionKey() {

		char[] decryptionKey = new char[key.length]; //New char array with Key length for the alphabetically ordered key

		for (int i = 0; i < key.length; i++) { //Loop over the Array and assign the Key to it
			decryptionKey[i] = key[i];
		}
		Arrays.sort(decryptionKey); //Sort the Array alphabetically

		this.decryptionKey = decryptionKey; //Assign the alphabetically ordered Key to the Instance Variable

	}

	//Method 2: Set the number of Rows needed for the Matrix
	//Divide the ciphered text by the length of the key, if there's remainder, add one extra row (always one extra for the key on top)
	private void setDecryptionNRows(String cipherText) {
		if((cipherText.length() % key.length) == 0) {
			decryptionNRows = 1 + (cipherText.length() / key.length);
		}else if((cipherText.length() % key.length) > 0) {
			decryptionNRows = 2 + (cipherText.length() / key.length);
		}
	}

	//Method 3: fill the Matrix with the Decryption Key in the first row and the Ciphered text in the rest
	private char[][] getDecryptionMatrix(String cipherText){
		char[][] decryptionMatrix = new char[decryptionNRows][key.length]; //Create the Matrix to Decrypt with the rows needed (Method 2) and the cols (lenght of the key)

		//Fill the matrix. Can't explain why, would like to know why. It was the only way I've found to make it work
		//If not done, there's an "Out of array index exception" when trying to fill the Matrix with the Encrypted Text
		for(int row = 0; row < decryptionMatrix.length; row++) {
			for(int col = 0; col < decryptionMatrix[row].length; col++) {
				decryptionMatrix[row][col] = '~';
			}
		}

		//Loop over the Decryption key and the Matrix and add the Decryption Key to the first row
		for (int i = 0; i < key.length; i++) {
			for (int col = 0; col < key.length; col++) {
				for (int row = 0; row < 1; row++) {
					decryptionMatrix[row][col] = decryptionKey[i];
					i++;
				}
			}
		}

		//If the Encrypted text wouldn't fill all the matrix, fill the rest with '~' symbol
		//Same, I don't know why, but it was the only way I've found to make it work
		//If not done, there's an "Out of array index exception" when trying to fill the Matrix with the Encrypted Text
		StringBuilder prueba = new StringBuilder();
		if((cipherText.length() % key.length) != 0 ) {

			for(int i = 0; i < cipherText.length(); i++) {
				prueba.append(cipherText.toUpperCase().charAt(i));
			}
			while (prueba.length() % key.length != 0) {
				prueba.append("~");
			}

			//Loop over the Ciphered Text and the rest of the Matrix and fill it by cols)	
			for (int i = 0; i < prueba.length(); i++) {
				for(int col = 0; col < key.length; col++) {
					for(int row = 1; row < decryptionMatrix.length; row++) {
						decryptionMatrix[row][col] = prueba.charAt(i);
						i++;
					}
				}	
			}
		}else {
			//If the encrypted text fills the Matrix, then
			//Loop over the Ciphered Text and the rest of the Matrix and fill it by cols)	
			for (int i = 0; i < cipherText.length(); i++) {
				for(int col = 0; col < key.length; col++) {
					for(int row = 1; row < decryptionMatrix.length; row++) {
						decryptionMatrix[row][col] = cipherText.toUpperCase().charAt(i);
						i++;
					}
				}	
			}
		}

		this.decryptionMatrix = decryptionMatrix; //Assign the local matrix to the Instance variable
		return decryptionMatrix;
	}

	//Method 4: Reorder the cols of the Matrix by the Key
	private char[][] getDecryptionColumnarTransposition(){

		//Create two Matrix to Reorder the Matrix
		char[][] decryptionMatrix = new char[decryptionNRows][key.length];
		char[][] temp = new char[decryptionNRows][key.length];


		decryptionMatrix = this.decryptionMatrix; //Assign the Instance variable to the Local Matrix

		//Loop over the Key and over the Matrix and reorder the cols of the Matrix by the Key
		for (int i = 0; i < key.length; i++) {
			for (int row = 1; row < decryptionMatrix.length; row++) {
				for (int col = 0; col < decryptionMatrix[row].length; col++) {

					//Not swap (it doesn't respect the order of identical values-chars): "move" values to a new Matrix (Temp) in the position required
					//Compare the Key to the Decryption Key (first row Matrix) and assign the values of the DecryptionMatrix to the Temp Matrix in the Key position
					//If the position in the Decryption Matrix is empty (already ordered), ignore it
					//If the position in the Temp Matrix is already filled, ignore it continue looking (same values-chars)
					//Empty the Decryption Matrix once moved
					if (key[i] == decryptionMatrix[0][col]) {

						if(decryptionMatrix[row][col] == '\0') continue;
						if(temp[row][i] != '\0') continue;

						temp[row][i] = decryptionMatrix[row][col];
						decryptionMatrix[row][col] = '\0';

					}
				}
			}
		}

		//Key into the first row (for showing works)
		for(int row = 0; row < 1; row++) {
			for(int col = 0; col < temp[row].length; col++) {
				for(int i = 0; i < key.length; i++) {
					temp[0][col] = key[i];
					col++;
				}
			}
		}

		decryptionMatrix = temp; //Assign the ordered values to the Matrix

		this.decryptionMatrix = decryptionMatrix; //Assign the ordered Matrix to the Instance variable

		return this.decryptionMatrix;
	}

	//Method 5: Compare Values on matrix to the Square and get the Decrypted char
	private StringBuilder getDecryptedText() {

		//Create a StringBuilder to create a String with the Decrypted Text
		StringBuilder decryptedText = new StringBuilder();

		//Loop over the Matrix (ignoring the first row - key)
		for(int rowD = 1; rowD < decryptionMatrix.length; rowD++) {
			//Loop adding 2 position to the ColD position as it's read by pairs
			for(int colD = 0; colD < decryptionMatrix[rowD].length; colD+=2) {

				//Change the initial position of the column in the Decryption Matrix if required (see *)
				if(key.length % 2 != 0 && rowD % 2 == 0 && colD == 0) colD++;

				//Respect whitespaces and add them to the Decrypted Text String
				if(decryptionMatrix[rowD][colD] == ' ') {
					decryptedText.append(decryptionMatrix[rowD][colD]);
				}

				//Loop over the Square
				squareLoop: for(int rowS = 0; rowS < square.length; rowS++) {

					int kD;
					char first;
					char second;

					//If the Key length is an odd number, the first of the pair is the last value of the column and the second of the pair is the first value of the next row
					//If the Key length is an odd number, the position "ColD" could be the last position. Adjust minding that

					//If the colD value is not the last (key length even number or key length odd number but not in the last pair)
					if(colD < decryptionMatrix[rowD].length-1) {
						//First of the pair is the value of the column
						first = decryptionMatrix[rowD][colD];
						kD = colD + 1;
						//Second of the pair is the next column value
						second = decryptionMatrix[rowD][kD];

						//If the pair of values matches the far left char and the top char, get the conjuction of them
						if(first == square[rowS][0]) {
							for(int colS = 0; colS < square[rowS].length; colS++) {
								if(second == square[0][colS]) {
									decryptedText.append(square[rowS][colS]);						
									break squareLoop;
								}
							}
							//To write Chars not included in the Square	
						}else if((rowS == square.length - 1) && (square[rowS][0] != first)) {
							if(first == '~') continue;
							if(first == ' ') continue;
							decryptedText.append(first);
							break;
						}

						//If the colD position is the last value of the row
					}else {
						//First of the pair is this position
						first = decryptionMatrix[rowD][colD];

						if(rowD < decryptionMatrix.length-1) {
							//Second of the pair is the first Col next Row in the Matrix
							//(*)The first of the pair in the next Row should be ColD+1, moving all one position for this row
							second = decryptionMatrix[rowD+1][0];

							//If the pair of values matches the far left char and the top char, get the conjuction of them
							if(first == square[rowS][0]) {
								for(int colS = 0; colS < square[rowS].length; colS++) {
									if(second == square[0][colS]) {
										decryptedText.append(square[rowS][colS]);
										break squareLoop;
									}
								}
								//To qrite Chars not included in the Square	
							}else if((rowS == square.length - 1) && (square[rowS][0] != first)) {
								if(first == '~') continue;
								if(first == ' ') continue;
								decryptedText.append(first);
								break;
							}
						}
					}
				}
			}
		}

		return decryptedText; //Decrypted Text
	}

}
