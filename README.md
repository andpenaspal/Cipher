# POLYBIUS SQUARE CYPHER
##### Author: Andres Penas Palmeiro
##### Final Project for the Module "Object Oriented Programming" of the H.Dip in Computer Science in Software Development - GMIT
##### Version: 1.0 (Aug 2019)

## Summary
This application encrypts text and decrypts ciphered text using an ADFGVX cipher, a fractionating transposition cipher which combines a modified Polybius square with a single columnar transposition.

## Requirements
JAVA JRE 8 (or superior)

## Usage
Browse the Menu to find the different options: Encrypt, Decrypt or the Smart Mode Options (Special Features, see below).

To Encryp there's need of a Key and Text (or File). Different Keys will output different ciphered texts for the same Text/File. The ciphered text will be shown when the Key and the Text are given. The ciphered text doesn't have any trailing or leading spaces; if copy-paste used, please mind this. For Files, the user must give a name for the output File (which will be created), where the ciphered text will be saved once the encryption is done.

To Decrypt there's need of a Key and Text (or File). To Decrypt a ciphered Text/File, the Key for Decryption must match the Key given to Encrypt the original version of the ciphered Text/File. Different keys will output different messages with the same ciphered text. The decrypted text will be shown when the Key and the ciphered Text are given. For Files, the user must give a name for the output File (which will be created), where the decrypted text will be saved once 
the decryption is done.

## Special Features
All features are accessible through the different Menus
### Files 
The application allows the user to encrypt and decrypt files (.txt). The files can be added writing their names or selecting from a list of Text Files from the application main folder
### Smart Mode
The Smart Mode allows the user to go further in the understanding of the mechanisms of the cypher:
##### Direct Encryption-Decryption:
Allows the user to have an automatic view of the output of both operations with the same text
##### Encryption Showing Work:
Allows the user to see the different steps done by the Cypher, showing:
* The Square used (if customized, see bellow)
    * The Input Data: Key and Plain Text
    * Matrix: Matrix created with the Key on top and the output of the text passed through the Square
    * Encryption Key: Alphabetically ordered Key
    * Sorted Matrix: Matrix sorted by the Encryption key
    * Cipher Text: Encrypted Text
* Decryption Showing Work:
    Same as "Encryption Showing Work" but with the Decryption process
* Direct Encryption-Decryption Showing Work:
    Same as "Direct Encryption-Decryption" but with the "Showing Work" feature in both operations
### Smart Square
Part of the "Smart Mode". The Smart Square Mode allows the user to change the Square of the Cypher:
##### Write Your Own Square: 
Allows the user to Input his/her own square to change the Default Square of the Cypher. The Custom Square created can be exported to a Text file in order to import it in the future
##### Create a Default Square: 
Creates automatically a basic Custom Square Text File. The Text File can be open in a text processor and the Custom Square modified easily in the Text File to import it later. It's meant to give an easier and more friendly way to create a customized Square, though the user must be careful with the format of the Square. If the user changes the name of the Text File, the new name needs to respect the suffix given (see below)
##### Import Square: 
Allows the user to import a Customized Square to use it in the Cypher. The name of the Custom Square must have the following suffix: "_customSquare"

## Limitations
Smart Mode doesn't allow Keys longer than 10 chars and Text longer than 30 chars (60 for Decryption). File Encryption or Decryption is not available in this mode

## About the Cypher
The Encryption work as follows:
1. Each character on the plain text is located inside of the Polybius Square, and the far left and top characters of the Square of this position are selected and saved.
    * Example (with the Default Square):
        * Plain Text "OBJECT" will generate:
         [FG, VX, XA, DF, GV, XG]
        * Being "F" the far left character and "G" the top character of the position generated when the character "O" has been located inside of the Square
2. Once all characters are located and the correspondent far left and top characters saved, a matrix is created with as many columns as Key's characters and as many rows as necessary to fill the matrix with the characters saved from the previous step.
    * The Plain Text "OBJECT" and Key "JAVA" will generate the following matrix:
        [J, A, V, A]
        [F, G, V, X]
        [X, A, D, F]
        [G, V, X, G]
3. The Key will be alphabetically ordered and the columns of the matrix will be sorted by the ordered Key
    * Sorted matrix:
        [A, A, J, V]
        [G, X, F, V]
        [A, F, X, D]
        [V, G, G, X]
4. The ciphered text will be generated by reading off each column (ignoring the key)
    * Ciphered text: "GAVXFGFXGVDX"

## Contact Information
G00376379@gmit.ie