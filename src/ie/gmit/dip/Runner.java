package ie.gmit.dip;

import java.io.*;

public class Runner {

	public static void main (String[] args) throws IOException{

		Menu menu = new Menu();
		menu.menu();		

	}

}

/* Questions:

  - Difference between:
    char[] new, char[] new2.
    1- new = new2;
    2- for(int i = 0; i < new.length; new++){
       new[i] = new2[i]
       }
  - More info about Setters/Getters. Can't see when or why to use them
  - I may be using too much Void Method and setting the Instance variables instead of Returning values with the methods, more info
  - I/O:
    1- When to close the System.in. Once closed you can't open it again
    2- All the I/O I could put into "IO" class is there, couldn't figure out how to put there the Readers/Writers as I need to call them in the other class
    3- If System.in closed, you can't keep working (for example, going back to the menu after Encryption and Encrypt something else, you can't close the I/O until Exit then)
    4- When closing the Buffered Reader/Writter in "SquareFileManagement" I can still work in "FileManagement", and both are Buffered Readers/Writers. Is it because when closing I'm closing the access (stream) to the files and not the "reader/Writer" Stream itself?
    5- In general, more info. I guess/hope is more related to software dessign, because I was kind of lost with all of it
  - Use of constructors  
  - System.exit(0) or System.exit(1)? Diferences?
 */
