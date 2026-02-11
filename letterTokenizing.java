/*This program reads a file and separates it into its different parts
Created by David Johnson for Software and Program Development 2 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class letterTokenizing {

public static void main(String[] args) {
    
    ArrayList<String> fileContents = new ArrayList<>();
    String path = "letter_frequency.csv";      
    try {//puts .csv file into arraylist
        BufferedReader read = new BufferedReader(new FileReader(path));
        String line;
        while ((line = read.readLine()) != null){

        StringTokenizer token = new StringTokenizer(line, ",");//uses tokenizer to split at commas
        while (token.hasMoreTokens()){// if there are more tokens, aka more commas, this runs
            fileContents.add(token.nextToken());
        }
        }
        read.close();

    }
    catch (Exception e) {
        System.out.println("Sorry, something went wrong: " + e.getMessage());
    }

ArrayList<String> usedContents = new ArrayList<>();// this arraylist is so I don't loose the file 
    
while (fileContents.size()>= 3) {//this splits the arralist up so that it is back in the original columns
    for (int i = 0; i <= 2; i++){
    System.out.print(fileContents.get(i));
    usedContents.add(fileContents.get(i));
}    
    System.out.println();
    fileContents.subList(0, 3).clear();
}

fileContents.addAll(usedContents);

ArrayList<Integer> numbersOnly = new ArrayList<>();//puts only integers into new arraylist
ArrayList<Double> dub = new ArrayList<>();
for (String item : fileContents){
    String trim = item.trim();
    if (trim.matches("[0-9]+")) {
    int number = Integer.parseInt(trim);
    numbersOnly.add(number);
    }
else if(trim.matches("[0-9].[0-9]+")){
    double dub1 = Double.parseDouble(trim);
    dub.add(dub1);
}

}

    int intSum = 0; 
    for (int element : numbersOnly) { //adds up all the integers from numbersOnly
        intSum += element; 
    }
int total = intSum;
int size = numbersOnly.size();
int average = total/size; //finding the average

    double dubSum = 0; 
    for (Double element : dub) { //adds up all the doubles from dub
        dubSum += element; 
    }

System.out.println("Total: " + average + " " + dubSum);
}
}