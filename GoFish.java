/*This program is a game of Go Fish with user input, but all the cards are dealt
Created by David Johnson for Software and Program Development 2*/

import java.util.*;

public class GoFish {
    
@SuppressWarnings({ "unchecked", "null" })
    public static void main(String[] args) {

methods.start();
Scanner input = new Scanner(System.in);
methods.playerNames(input);
    
ArrayList<Integer> deck = new ArrayList<>();
for (int i = 0; i < 4; i++){

    for(int e = 1; e < 14; e++){
        deck.add(e);
    }
}

ArrayList<Integer> cards = (ArrayList<Integer>) deck.clone();
Collections.shuffle(deck);

ArrayList<String> names = methods.playerNames;

ArrayList<Integer> player1Hand = new ArrayList<>();
ArrayList<Integer> player2Hand = new ArrayList<>();
ArrayList<Integer> player3Hand = new ArrayList<>();
ArrayList<Integer> player4Hand = new ArrayList<>();

for(int i = 0; i < 4; i++){

player1Hand.add(deck.get(0));
deck.remove(0);

player2Hand.add(deck.get(0));
deck.remove(0);

player3Hand.add(deck.get(0));
deck.remove(0);

player4Hand.add(deck.get(0));
deck.remove(0);

}

methods.printHands(names.get(0), player1Hand);
methods.printHands(names.get(1), player2Hand);
methods.printHands(names.get(2), player3Hand);
methods.printHands(names.get(3), player4Hand);
System.out.println();

int i;
String currentPlayer = null;

ArrayList<Integer> p0 = new ArrayList<>();
ArrayList<Integer> p1 = new ArrayList<>();
ArrayList<Integer> p2 = new ArrayList<>();
ArrayList<Integer> p3 = new ArrayList<>();

int ps0 = 0;
int ps1 = 0;
int ps2 = 0;
int ps3 = 0;


ArrayList<Integer> currentHand = null;
boolean cardsLeft = true;

while(cardsLeft){
for ( i = 0; i < 4; i++){

methods.findingPairs(player1Hand, ps0);
methods.findingPairs(player2Hand, ps1);
methods.findingPairs(player3Hand, ps2);
methods.findingPairs(player4Hand, ps3);

p0.add(ps0);
p1.add(ps1);
p2.add(ps2);
p3.add(ps3);

if(player1Hand.isEmpty()){
    if(player2Hand.isEmpty()){
        if(player3Hand.isEmpty()){
            if(player4Hand.isEmpty()){
                cardsLeft = false;
                break;
            }
        }
    }
}

ArrayList<ArrayList<Integer>> hands = new ArrayList<>();
hands.add(player1Hand);
hands.add(player2Hand);
hands.add(player3Hand);
hands.add(player4Hand);


currentPlayer = names.get(i);
currentHand = hands.get(i);

if (currentHand.isEmpty()) {
    System.out.println(currentPlayer + " has no cards and skips.");
}

if(!currentHand.isEmpty()){
    methods.printHand(currentPlayer, currentHand);



String person = null;
int num = 0;

ArrayList<Integer> handOfPerson = null;



Boolean isPlayer = null;
Boolean number = false;
try{
do{
System.out.println("What number are you looking for?");
String numb = input.nextLine();

if(numb.matches("[0-9]+")){
    num = Integer.parseInt(numb);
    number = true;
}

else if(handOfPerson.contains(num))
number = true;

else{
    System.out.println("Sorry, please enter a number or make sure that number is in your hand.");
}

}while (!number);

do{
System.out.println("Who do you want to ask?");
person = input.nextLine();

isPlayer = names.contains(person);

if(isPlayer == true){
    isPlayer = true;
}
else if(person == currentPlayer){
    System.out.println("Sorry, please input a different player");
}
else if(isPlayer == false) {
    System.out.println("Please input a current player name."); 
}

}while (!isPlayer);
}
catch(Exception e){
    System.out.println("Sorry, something went wrong: " + e.getMessage());
}

int indexOfName = names.indexOf(person);
ArrayList<Integer> pairs = null;
if(indexOfName == 0){
    handOfPerson = player1Hand;
    pairs = p0;
}
if(indexOfName == 1){
    handOfPerson = player2Hand;
    pairs = p1;
}
if(indexOfName == 2){
    handOfPerson = player3Hand;
    pairs = p2;
}
if(indexOfName == 3){
    handOfPerson = player4Hand;
    pairs = p3;
}


if(handOfPerson.contains(num)){

    for(int ii = 0; ii < 2; ii++){
        cards.remove(Integer.valueOf(num));
        pairs.add(num);


    }
System.out.println(person + " has that card.");
 handOfPerson.remove(Integer.valueOf(num));
currentHand.remove(Integer.valueOf(num));

}

else{
    System.out.println(person + " does not have that card.");
    methods.drawCard(cards, currentHand);
}
}
}


methods.printingPairs(names, p0, p1, p2, p3);

    }
}
}