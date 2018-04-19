import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) { //main method loops the user through program prompting them to different options and executing queries accordingly
        try {
            String Continue = "Yes";
            Scanner selectionAnswer = new Scanner(System.in);
            do { //loops until the user selects option 6 (Closing the Program).
                engine test = new engine(); //calling engine method
                test.establishConnection(); //getting connection
                //showing the user the available options he can choose from, while using IF/Else statements
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Welcome to the Student Database!");
                System.out.println("We offer our users a variety of options when navigating through our database:");
                System.out.println("1. To display all of our current students press 1.");
                System.out.println("2. To create a new student within the database press 2.");
                System.out.println("3. To update a new student within the database press 3.");
                System.out.println("4. To delete a student from the database press 4.");
                System.out.println("5. To search for a specific student based on Major, GPA, or Faculty Advisor press 5.");
                System.out.println("6. To exit the Student Database press 6.");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
                Scanner selectionNumber = new Scanner(System.in);
                int x;
                x = selectionNumber.nextInt();
                //user selection between the 6 different options available to him
                if (x == 1) {  //
                    test.getRecords();
                    System.out.println("Would you like to go back to the main options menu? (Yes or No)");
                    Continue = selectionAnswer.nextLine();
                    if (Continue.equals("No")) {
                        System.out.println("Goodbye and thank you for using our database!");
                    }
                    if (!Continue.equals("Yes") && !Continue.equals("No")){
                        System.out.println("You did not enter Yes or No.");
                        System.out.println("Would you like to go back to the main options menu? (Yes or No)");
                        Continue = selectionAnswer.nextLine();
                    }

                }
                else if (x == 2) {
                    test.createStudent();
                    System.out.println("Would you like to go back to the main options menu? (Yes or No)");
                    Continue = selectionAnswer.nextLine();
                    if (Continue.equals("No")) {
                        System.out.println("Goodbye and thank you for using our database!");
                    }
                    if (!Continue.equals("Yes") && !Continue.equals("No")){
                        System.out.println("You did not enter Yes or No.");
                        System.out.println("Would you like to go back to the main options menu? (Yes or No)");
                        Continue = selectionAnswer.nextLine();
                    }

                }
                else if (x == 3) {
                    test.updateRecord();
                    System.out.println("Would you like to go back to the main options menu? (Yes or No)");
                    Continue = selectionAnswer.nextLine();
                    if (Continue.equals("No")) {
                        System.out.println("Goodbye and thank you for using our database!");
                    }
                    if (!Continue.equals("Yes") && !Continue.equals("No")){
                        System.out.println("You did not enter Yes or no.");
                        System.out.println("Would you like to go back to the main options menu? (Yes or No)");
                        Continue = selectionAnswer.nextLine();
                    }

                }
                else if (x == 4) {
                    test.deleteRecord();
                    System.out.println("Would you like to go back to the main options menu? (Yes or No)");
                    Continue = selectionAnswer.nextLine();
                    if (Continue.equals("No")) {
                        System.out.println("Goodbye and thank you for using our database!");
                    }
                    if (!Continue.equals("Yes") && !Continue.equals("No")){
                        System.out.println("You did not enter Yes or No.");
                        System.out.println("Would you like to go back to the main options menu? (Yes or No)");
                        Continue = selectionAnswer.nextLine();
                    }

                }
                else if (x == 5) {
                    test.searchStudent();
                    System.out.println("Would you like to go back to the main options menu? (Yes or No)");
                    Continue = selectionAnswer.nextLine();
                    if (Continue.equals("No")) {
                        System.out.println("Goodbye and thank you for using our database!");
                    }
                    if (!Continue.equals("Yes") && !Continue.equals("No")){
                        System.out.println("You did not enter Yes or No.");
                        System.out.println("Would you like to go back to the main options menu? (Yes or No)");
                        Continue = selectionAnswer.nextLine();
                    }

                }
                else if (x ==6){
                    System.out.println("Goodbye and thank you for using our database!");
                    System.exit(0);
                }

                else{
                    System.out.println("Not a valid number, please choose an option between 1 and 6.");
                    System.out.println("Would you like to go back to the main options menu? (Yes or No)");
                    Continue = selectionAnswer.nextLine();
                    if (Continue.equals("No")) {
                        System.out.println("Goodbye and thank you for using our database!");
                    }


                }

            }while (Continue.equals("Yes"));
        }catch (InputMismatchException m){  //prints if they input a wrong type
            System.out.println(m.getMessage());
        }
    }
}


