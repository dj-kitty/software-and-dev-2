/*This program splits a string of text into individual words using a regex
Created by David Johnson for Software and Program Development 2 */

import java.util.Scanner;
import java.util.regex.*;
public class stringSeparation {
    public static void splitString(String testString){
    String regex = "\\s";
    String [] splitText = testString.split(regex);
    
    int i;
    for(i=0; i<splitText.length; i++){
        System.out.println(splitText[i]);
    }
    System.out.println("Your sentence is " + splitText.length + " words long.");
    }
    public static void main(String[] args) {
    String String1="the quick Brown Fox Jumped over the lazy dog";
    splitString(String1);

    boolean done = false;

    do{
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Please input a sentence to separate.");
            String text = input.nextLine();

            String regex = "\\D+";
            if(Pattern.matches(regex, text)){
                System.out.println("Thank you.");
                done = true;
                input.close();
                splitString(text);
            }
            else{
                System.out.println("Sorry, that is not a sentence. Please try again.");
                done = false;
            }
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }while (!done);


    }
}