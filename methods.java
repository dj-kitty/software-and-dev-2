import java.util.*;

public class methods {
        
    static ArrayList<String> playerNames = new ArrayList<>();

public static void playerNames(Scanner a){

int numOfTimes = 4;
String regex = "[^a-zA-Z]+";
for(int i = 0; i < numOfTimes; i++){
    int more = i+1;
    System.out.println("Player " + more + ", what is your name?");
    String name = a.nextLine();
    String trimmedName = name.trim();
    if(!name.matches(regex)){

String[] letters = trimmedName.split(regex);
String newName = String.join("", letters);
playerNames.add(newName);
    }
else{
    playerNames.add(i, trimmedName);

}
}
}

public static void printHands(String a, ArrayList<Integer> b){

    System.out.println();
    System.out.println(a + "'s original hand is: " + b);


}

public static void countingPairs(ArrayList<Integer> a){
    
    int originalPairs = a.get(0);
    a.remove(0);

            Map<Integer, Integer> lookForPairs = new HashMap<>();
        for (Integer number : a) {
            lookForPairs.put(number, lookForPairs.getOrDefault(number, 0) + 1);
        }

        int pairs = 0;
        for (int count : lookForPairs.values()) {
            if (count >= 2) {
                pairs += (count * (count - 1)) / 2;
            }
        }    
int totalPairs = pairs + originalPairs;
        System.out.print(totalPairs);


}

public static void printingPairs(ArrayList<String> a, ArrayList<Integer> b, ArrayList<Integer> c, ArrayList<Integer> d, ArrayList<Integer> e){

System.out.print(a.get(0) + " has ");
countingPairs(b);
System.out.println(" pairs.");

System.out.print(a.get(1) + " has ");
countingPairs(c);
System.out.println(" pairs.");

System.out.print(a.get(2) + " has ");
countingPairs(d);
System.out.println(" pairs.");

System.out.print(a.get(3) + " has ");
countingPairs(e);
System.out.println(" pairs.");

}

public static int findingPairs(ArrayList<Integer> a, int b) {

    Map<Integer, Integer> lookForPairs = new HashMap<>();

    for (Integer number : a) {
        lookForPairs.put(number, lookForPairs.getOrDefault(number, 0) + 1);
    }

    for (Map.Entry<Integer, Integer> entry : lookForPairs.entrySet()) {
        int number = entry.getKey();
        int count = entry.getValue();

        int pairs = count / 2; 
        if (pairs > 0) {
            b += pairs;

            for (int i = 0; i < pairs * 2; i++) {
                a.remove(Integer.valueOf(number));
            }
        }
    }

    return b;
}

public static void start(){

    System.out.print("This game is set up four four players.");
    System.err.print(" If you have more than four players, some of you will have to wait.");
    System.out.println(" If you have less than four players, please get more.");

}

public static void printHand(String a, ArrayList<Integer> b){
    System.out.println(a + ", your hand is " + b);
}
    
public static ArrayList<Integer> drawCard(ArrayList<Integer> a, ArrayList<Integer> b){
    int card = a.get(0);
    b.add(card);
    return b;
}
}