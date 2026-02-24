/*This file has a user (or 5 users) input 5 different items, stores it, and then 
prints it out so it is readable.
The five items I will do are age, name, home state, favorite animal, and favorite color
Created by David Johnson for Software and Program Development 2 */

import java.io.*;
import java.util.*;

public class delimiters {

static void separator (String line){//this method is for separating the lines and making sure they are readable

String delimiter = "^";//delimiter
StringTokenizer token = new StringTokenizer(line, delimiter);//separating line with delimiter
ArrayList<String> tokenized = new ArrayList<>();
while (token.hasMoreTokens()) {
    tokenized.add(token.nextToken());//adding separated items to arraylist
}
int age = Integer.parseInt(tokenized.get(0)); //turning string number to int number
tokenized.remove(0);//removing it

String name = (tokenized.get(0)).trim();//getting rid of spaces, tabs, etc from the other objects
String state = (tokenized.get(1)).trim();
String animal = (tokenized.get(2)).trim();
String color = (tokenized.get(3)).trim();

tokenized.clear();//replacing non-trimmed items with trimmed items
tokenized.add(name);
tokenized.add(state);
tokenized.add(animal);
tokenized.add(color);

ArrayList<String> lettersOnly = new ArrayList<>();
String regex = "[^a-zA-Z]+";
for (int i = 0; i < tokenized.size(); i++){//taking out everything except letters from each item
    String item = tokenized.get(i);
    if(!item.matches(regex)){
String old = item;
String[] split = old.split(regex);//splits items at non-letters and removing them
String notOld = String.join("", split);//rejoining letters
lettersOnly.add(notOld);
    }
    else{
        lettersOnly.add(item);
    }

}

String newName = (lettersOnly.get(0));//must pull from this array since other array is not separated
String newState = (lettersOnly.get(1));
String newAnimal = (lettersOnly.get(2));
String newColor = (lettersOnly.get(3));
System.out.println("Hello, " + newName + ". You are " + age + " years old. You live in " + newState + ". Your favorite animal is " + newAnimal + " and color is " + newColor + ".");

}
static void average (ArrayList<Integer> array){//this method is for averaging the ages

int sum = 0;
for (int element: array){
sum += element;//creating sum
    }
int average = sum/array.size();//doing average
System.out.println("The average age between the five users is " + average + ".");
}
public static void main(String[] args) throws FileNotFoundException, IOException {
      
String filePath = "simpleDatabase.txt";//filepath

try{

System.out.println("Checking for file");
File database = new File(filePath);//gets ready to create database

for(int i = 0; i < 3; i++){//this is a cool little thing to make the periods after a certain amount of time
    //has no true purpose in here except being cool
    Thread.sleep(250);
    System.out.print(".");
    Thread.sleep(250);
}System.out.println();

if(database.length() > 0){//check to see if file has contents
database.delete();//easy way to clear the file if it hasa contents
}

if(database.createNewFile()){
System.out.println("File not found. Creating now.");//for if the file doesn't exist
for(int i = 0; i < 3; i++){
    Thread.sleep(250);
    System.out.print(".");
    Thread.sleep(250);
}System.out.println();
}
else{
    System.out.println("File found.");
}
}
catch(Exception e){
System.out.println("Sorry, something went wrong: " + e.getMessage());
}

String delimiter = "^";//delimiter
int numOfTimes = 5;//cause there is only five users
BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
BufferedWriter write = new BufferedWriter(new FileWriter(filePath, true));
ArrayList<Integer> numAge = new ArrayList<>();

try{
for(int i = 0; i < numOfTimes; i++){//makes it so this will loop 5 times

String age;
boolean done = false;

do{
System.out.println("How old are you?");//asks for age
age = input.readLine();
String trimmed = age.trim();//trim age before checking if it is number

if(trimmed.matches("[0-9]+")){
    int ageNum = Integer.parseInt(trimmed);//checks if it is a number
    numAge.add(ageNum);
    done = true;
}
else{
    System.out.println("Sorry, please input a number.");
}

} while(!done); //makes this loop repeat until number is found

System.out.println("What is your name?");//asks for name
String name = input.readLine();

System.out.println("What state do you live in?");//asks for state
String state = input.readLine();

System.out.println("What is your favorite animal?");//asks for fav animal
String animal = input.readLine();

System.out.println("What is your favorite color?");//asks for fav color
String color = input.readLine();

write.write(age.trim() + delimiter + name + delimiter + state + delimiter + animal + delimiter + color);//adds everything to file
write.newLine();//makes new line
}
write.close();

}

catch(Exception e){
System.out.println("Sorry, something went wrong: " + e.getMessage());
}

Scanner fileReader = new Scanner(new FileReader(filePath));
ArrayList<String> fileContents = new ArrayList<>();
try {
while (fileReader.hasNextLine()){
fileContents.add(fileReader.nextLine()); //adds file contents to arraylist
}

} catch (Exception e) {
System.out.println("Sorry, something went wrong: " + e.getMessage());
}
fileReader.close();

for(int i = 0; i < fileContents.size(); i++){
    String line = fileContents.get(i);
    separator(line);// calls separator method for each index in arraylist
}

average(numAge); //calls average separator for ages

}
}