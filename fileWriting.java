/*This code adds my name, grade, and name of school to a text file
Created by David Johnson for Software and Programn Development 2  */

import java.io.*;//the asterik tells the program to import all classes in java.io
public class fileWriting {
public static void main(String[] args){
    String filePath = "C:\\Users\\david\\OneDrive\\Documents\\tech trep\\2025-2026 classes\\Software dev\\semester 2\\unit 1\\week 2\\David.txt";
    //easy access to file path
    try {
        File David = new File(filePath); //gets ready to create new file
        if(David.createNewFile()) {//attempts to create new file
            System.out.println("File successfully created. You can now add to this file.");
        }//happens if file was made
        else{
            System.out.println("This file already exists");
        }//happens if file already exists
    } catch (Exception e) {
        System.out.println("Sorry, an error occured. This file does not exist and cannot be created.");
        e.printStackTrace();
    }//if, for some reason, the file cannot be created and does not already exist (like destination doesn't exist or error 
    //in path name), throws exception

    try {
        BufferedWriter writeText = new BufferedWriter(new FileWriter(filePath));//this writes into the file
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));//this reads keyboard input
        System.out.println("What is your first and last name?");//all the questions to answer
        String name = input.readLine();
        System.out.println("What grade are you in?");
        String grade = input.readLine();
        System.out.println("What school do you go to?");
        String school = input.readLine();
        writeText.write("My name is " + name + ".");

        writeText.newLine();
        writeText.write("I am in " + grade + ".");
        writeText.newLine();
        writeText.write("I go to school at " + school + ".");
        writeText.close();
    } catch (Exception e) {
        System.out.println("Sorry, something went wrong" + e.getMessage());
    }
   
}
    
}