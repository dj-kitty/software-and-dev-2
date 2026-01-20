/*This program reads a file I created earlier
Created by David Johnson for Software and Program development 2 */

import java.io.*;
public class fileReading {
public static void main(String[] args) throws Exception {//this acknowledges that there are places where exceptions are thrown
    String filePath = "C:\\Users\\david\\OneDrive\\Documents\\tech trep\\2025-2026 classes\\Software dev\\semester 2\\unit 1\\week 2\\David.txt";
//the file path
    BufferedReader readFile = new BufferedReader(new FileReader(filePath));//this throws FileNotFound Exception
    String fileContent;

    while ((fileContent = readFile.readLine()) !=null) {//throws I/O exception
//this tells the program to read each line in order and put it as fileContent
        System.out.println(fileContent);
    }
    readFile.close();
    }
}
