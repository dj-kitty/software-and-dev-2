/*This program has the user input their name and attempts to separate it
Created by David Johnson for Software and Program Development 2 */

import java.util.Scanner;//import scanner
public class Name {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);//activate scanner
        System.out.println("What is your first and last name?");
        String name = input.nextLine();
        String regex = "[,\\.\\s]";//criteria for splitting name
        String[] nameSeparated = name.split(regex);//splitting name and storing it
        System.out.println("Would you like me to call you, Mr./Mrs./Ms. " + nameSeparated[1] + " or can I call you " + nameSeparated[0]);
    }
}
