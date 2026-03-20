/*This program is a created queue and stack program
created by David Johnson for Software and Program Development 2*/

import java.util.*;

public class stacksAndQueues {

public static class person{//person class

String name;
String homeState;
int age;

person(String name, String homeState, int age){//constructor
    this.name = name;
    this.homeState = homeState;
    this.age = age;
}

void setName(String newName){
    this.name = newName;
}
void setState(String newState){
    this.homeState = newState;
}
void setAge(int newAge){
    this.age = newAge;
}

public String getName(){
    return name;
}
public String getState(){
    return homeState;
}
public int getAge(){
    return age;
}

public String toString(){
return name + " lives in " + homeState + " and is " + age + " years old.";
}

}

public static class node {

node next;
node previous;
person data;

node (person data, node next, node previous){//defines node with constructor
    this.data=data;
    this.next=next;
    this.previous=previous;
}

public void setNext(node next){ 
    this.next=next;
}

public void setPrevious(node previous){
    this.previous=previous;
}

public node getPrevious(){
    return previous;
}  

public node getNext(){
    return next;
}

}

public static class list {

private node head;
private node tail;
  
list(){//creates list
    head = null;
    tail = head;
    node current = null;
}
           
public void Push(person data){//add
    node current = new node(data, null, null );
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
  
public person Pop(){ //remove
 if (tail == null) return null;

    person data = tail.data;
    tail = tail.previous;

    if (tail != null)
        tail.next = null;
    else
        head = null;

    return data;

}

public void enqueue(person data){//add
    node current = new node(data, null, null );
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
  
public person dequeue(){ //remove
 if (head == null) return null;

    person data = head.data;
    head = head.next;

    if (head != null)
        head.previous = null;
    else
        tail = null;

    return data;

}

public person stackPeek(){//see what last item is
    return tail.data;
}

public person queuePeek(){//see what first item is
    return head.data;
}

public boolean isEmpty(){ //checks to see if list has anythink in it
    node current = tail;
    if(current.previous == null){ 
        return false;
    }
    return true;
}

public boolean hasPrevious(){//checks to see if there is previous item
    node current = tail;
    if (current.previous == null){
        return false;
    }
return true;
}

public boolean hasNext(){//checks to see if there is an item after the current one
    node current = head;
    if(current.next == null){
        return false;
    }
    return true;
}

public person getPrevious(){//gets previous node
    node current = tail;
    if (current.previous != null){
        current = current.previous;
        return current.data;
    }
return null;
}

public person getNext(){//gets next node
    node current = head;
    if(current.next != null){
        current = current.next;
        return current.data;
    }
    return null;
}

public person getLast(){//gets last (tail) node
    node current = tail;
    return current.data;
}

public person getFirst(){//gets first (head) node
    node current = head;
    return current.data;
}
 
public int size(){ //this gets how many items are in the list
    int size = 0;
    node temp = head;

    while(temp != null){
    size ++;
    temp = temp.next;
}
    return size;
}

public void printStack(){//prints list last to first
    node current = tail;
    while(current != null){
        System.out.println(current.data.toString());
        current = current.previous;
    }
}

public void printQueue(){//prints list first to last
    node current = head;
    while(current != null){
        System.out.println(current.data.toString());
        current = current.next;
    }
}


}



public static void main(String[] args) throws InterruptedException{
    
Scanner input = new Scanner(System.in);//activate scanner

System.out.println("Please list everyone you know, what state they live in, and how old they are.");

boolean done = false;
list people = new list();
person person;


do{

done = false;
boolean correctInput = true;
String name = null;
String state = null;
int age = 0;

do{//asks for name
    correctInput = true;
    System.out.println("What is the name?");
    name = input.nextLine();

    if(name.matches("[0-9]+")){
        System.out.println("Invalid input. Please, try again.");
        correctInput = false;
    }
}while (!correctInput);//makes sure name is not numbers

do{//asks for home state
    System.out.println("Where does " + name + " live?");
    state = input.nextLine();

    if(state.matches("[0-9]+")){
        System.out.println("Invalid input. Please, try again.");
        correctInput = false;
    }
    else{
        correctInput = true;
    }
}while (!correctInput);//makes sure state is not number

do{//asks for age
    System.out.println("How old is " + name + "?");
    String Age = input.nextLine();

    if(Age.matches("\\D")){
        System.out.println("Invalid input. Please, try again.");
        correctInput = false;
    }
    else{
        correctInput = true;
    }
    age = Integer.parseInt(Age);
}while (!correctInput);//makes sure age is number

person = new person(name, state, age);//creating person
people.enqueue(person);//adds person to list

input.nextLine();

System.out.println("If you are out people, please enter '00'. If there are more people you wish to enter, enter any key to continue.");
String answer = input.nextLine();//asks if there is more people or 00 if none

if(answer.matches("00")){
    System.out.println("Thank you.");
    done = true;//if input is 00, will not ask for more people
}
else{
    continue;
}


}while (!done);//!done happens when answer to above question is 00

System.out.println("Printed as queue:");
people.printQueue();//self-explanatory

System.out.println("\nPrinted as stack.");
people.printStack();//self-explanatory

input.close();

Thread.sleep(2000);//pause

System.out.println("All those people went to Walmart and somehow end up in the same checkout line.");



int numOfTimes = people.size();

for(int i = 0; i < numOfTimes; i ++){//real word queue

Thread.sleep(1000);

person = people.queuePeek();//setting person as first person in list

String name = person.getName();//getting name of person

System.out.println(name + " is up at the register.");

Thread.sleep(1000);

System.out.println(name + " is done and is leaving.");
people.dequeue();//taking person out of list

}




}

}