/*This is a simple input/output file with scanners
Created by David Johnson for Software and Program Development 2 */
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
class Scan{

public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Boolean done;

    do{try{
        System.out.println("Approximately how old are you?");//float scan
        float age = input.nextFloat();
        System.out.println("So I get that you are about " + age + " years old.");
        done = true;
    }catch(Exception e){
System.out.println("Sorry, that is not an acceptable value. Please input a decimal number.");
done = false;
    }} while (!done);//this should make the code repeat until an acceptable variable is inputted

    do{ try{
        input.nextLine();
        System.out.println("What is your favorite animal?");//string scan
        String critter = input.nextLine();
        String regex = "\\d+";
        if(!Pattern.matches(regex, critter)){
        System.out.println("So I get that your favorite animal is a " + critter + ".");
        done = true;
        }
        else{
            done = false;
            throw new InputMismatchException("Sorry, that is not a known animal. Please input an animal name.");  
        }
    }
    catch(Exception e){
        System.out.println("Sorry, that is not a known animal. Please input an animal name.");
        done = false;
    }
        
    } while (!done);//this should make the code repeat until an acceptable variable is inputted

        
    do{ try {//int scan
        System.out.println("How many pets do you have?");
        int petNumber = input.nextInt();
        System.out.println("So you have " + petNumber + " pets.");
        done = true;
    } catch (Exception e) {
        System.out.println("Sorry, that is not a number. Please input a number.");
        done = false;
    }
}while (!done);//this should make the code repeat until an acceptable variable is inputted


} 
}