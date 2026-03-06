/*This program is a created LinkedList to store animals
created by David Johnson for Software and Program Development 2*/

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class container{

public static class Animal {
    String name;
    String type;
    String age;

    Animal (String name, String color, String age){//constructor
        this.name = name;
        this.type = color;
        this.age = age;
    }

    void setName(String newName){//getting and setting all the variables
        this.name = newName;
    }
    void setColor(String newColor){
        this.type = newColor;
    }
    void setAge(String newAge) {
        this.age = newAge;
    }

    public String getname(){
        return name;
    }
    public String getcolor(){
        return type;
    }
    public String getage(){
        return age;
    }

    public String toString(){//method for printing out data, since we can't use an already created one
        return "Name: " + name + ", Type: " + type + ", Age: " + age;
    }
}

public static class Node {

Node next;
Node previous;
Animal data;

Node (Animal data, Node next,Node previous){//defines what the node is as a constructor
    this.data=data;
    this.next=next;
    this.previous=previous;
}

public void setNext(Node next){ //this method will change the node after the current one
    this.next=next;
}

public void setPrevious (Node previous){//this method changes the node before the current one
    this.previous=previous;
}

public Node getNext(){//gets the next node
    return next;
}

public Node getPrevious(){//gets the previous node
    return previous;
}  

}

public static class testingLinkedList {

private Node head;
private Node tail;
private Node current;
  
testingLinkedList(){//creates node
    head = null;
    tail = head;
    current = null;
}
          
public void insert(Animal data){//inserts animal object at a certain position
    Node temp = new Node (data, null,null);
    temp.previous = current;
    temp.next = current.next;
    current = temp;
}     

public void add(Animal data){//adds animal object at head(if head is empty) or at the end
    Node current = new Node(data, null, null );
    if (head == null){
        head = current; 
        tail = current;
        return;
    }

    else{
        tail.next = current;
        current.previous = tail;
        tail = current;
    }
}
  
public Animal remove(Node current){ //removes node
    current.next.setPrevious(current.previous);
    current.previous.setNext(current.next);
    return current.data;
}

public boolean hasNext(){//checks to see if there is a node after the current one
    if (current.next == null){
        return false;
    }
    return true;
}

public boolean hasPrevious(){//checks to see if there is a node before the current one
    if (current.previous == null){
        return false;
    }
return true;
}

public Animal getNext(){//gets next node
    if (current.getNext() != null){
        current = current.next;
        return current.data;
    }
return null;
}

public Animal getPrevious(){//gets previous node
    if (current.previous != null){
        current = current.previous;
        return current.data;
    }
return null;
}

public Animal getHead(){//gets the first node
    current = head;
    return current.data;
}

public Animal getTail(){//gets the last node
    current = tail;
    return current.data;
}
 
 }

public static void main(String[] args) {
    
Scanner input = new Scanner(System.in);
Boolean num = true;
int numOfTimes = 0;
testingLinkedList listOfAnimals = new testingLinkedList();
Animal animal;
Boolean moreAnimals = true;


do{
    do{

System.out.println("How many pets do you have?");
String number = input.nextLine();

 if(number.matches("[0-9]+")){

String trimmed = number.trim();
numOfTimes = Integer.parseInt(trimmed);

}

else{
    System.out.println("Sorry, please input a number.");
    num = false;
}

}while (!num);
//above code sets how many times the system will ask for animals

boolean number = true;


number = true;
for(int i = 0; i < numOfTimes; i++){

System.out.println("What is the name of your animal?");//name
String name = input.nextLine();

System.out.println("What type of animal is " + name + "?");//type
String type = input.nextLine();

String age = null;
do{//this following code gets the age of the animal
    //it also makes sure it is a number
    //it also has two other options that it askes and checks for

System.out.println("How old is " + name +"? If you do not know, you may input 'I don't know' or 'idk'");
 age = input.nextLine();

String idk = "I don't know";

Pattern pattern = Pattern.compile("idk", Pattern.CASE_INSENSITIVE);//doesn't matter if 'idk' is upper or lowercase
Matcher matcher = pattern.matcher(age);
Boolean match = matcher.find();
if (match == true){
    age = "unknown";
}

else if(age.matches("[0-9]+")){

String trimmed = age.trim();
int numage = Integer.parseInt(trimmed);
age = Integer.toString(numage);

}

else if(age.matches(idk)){
    age = "unknown";
}

else{
    System.out.println("Sorry, please input a number.");
    number = false;
}

}while (!number);

animal = new Animal(name, type, age);//turns data into animal objects
listOfAnimals.add(animal);//adds animal objects to the created linked list

}

boolean done = false;
do{

System.out.println("Do you have more animals? Please answer yes or no");//sees if user has animals they forgot
String answer = input.nextLine().trim().toLowerCase();//makes sure what ever is inputted is lowercase with no extra space

if(answer.equals("yes")){//if person has more animals, makes the whole code repeat
    System.out.println("Ok.");
    moreAnimals = true;
    done = true;
}

else if(answer.equals("no")){//if person doesn't have more animals, moves on
    System.out.println("Thank you.");
    moreAnimals = false;
    done = true;
}

else{
    System.out.println("Sorry, please input yes or no.");//if yes or no is not inputted
    System.out.println(done);
}

}while (!done);


}while(moreAnimals);//block of code repeats while there are more animals

System.out.println("Here are the animals printed in order: ");

animal = listOfAnimals.getHead();
System.out.println(animal);

while(listOfAnimals.hasNext()){//prints linked list in order

    animal = listOfAnimals.getNext();
    System.out.println(animal.toString()); 
}

System.out.println("\nHere are the animals printed in reverse order: " );

animal = listOfAnimals.getTail();
System.out.println(animal);

while(listOfAnimals.hasPrevious()){//prints linked list in reverse order

    animal = listOfAnimals.getPrevious();
    System.out.println(animal.toString()); 
}

input.close();

}
}   