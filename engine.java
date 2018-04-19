import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

        public class engine {
        public Connection con;

        public void establishConnection()
        {
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentDB?useSSL=false", "angio102", "tribuna"); //establishing connection to database
                System.out.println("Connection Established.");
            } catch (SQLException err) {

                System.out.println("Connection Failed.");

            }
        }


        public void getRecords(){  //method used to retreive all the attributes from each student
            try {
                System.out.println("Retrieving records...");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
                PreparedStatement pst = con.prepareStatement("Select * from Student;"); //selecting all attributes from Student
                ResultSet rs2 = pst.executeQuery();

                while (rs2.next()) {
                    System.out.println("Student ID: " + rs2.getString(1));
                    System.out.println("First name: " + rs2.getString(2));
                    System.out.println("Last name: " + rs2.getString(3));
                    System.out.println("GPA: " + rs2.getDouble(4));
                    System.out.println("Major: " + rs2.getString(5));
                    System.out.println("Faculty Advisor: " + rs2.getString(6));
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }

        }

        public void deleteRecord(){ //method used to delete a student from the database
            Scanner delete = new Scanner(System.in);
            try{
            System.out.println("Enter the ID of the student you would like to remove: "); //id is unique and best way to delete something from a DB
            int n = delete.nextInt();

                PreparedStatement pst = con.prepareStatement("DELETE FROM Student WHERE StudentId = ?");
                pst.setInt(1, n);
                pst.executeUpdate();
                System.out.println("The student has successfully been deleted.");

            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
            catch (InputMismatchException x){
                System.out.println("The student ID you entered cannot be of type string. ");
            }

            }
        public void createStudent(){ //method used to add a new student to the database

            Scanner create = new Scanner(System.in);
            Scanner create2 = new Scanner(System.in);
            try{
                PreparedStatement pst = con.prepareStatement("INSERT INTO Student(FirstName,LastName, GPA, Major, FacultyAdvisor) VALUES(?,?,?,?,?)");
                System.out.println("Enter the first name of the student you would like to create: ");
                String firstName  = create.nextLine();
                System.out.println("Enter the last name of the student you would like to create: ");
                String lastName  = create.nextLine();
                System.out.println("Enter the GPA of the student you would like to create: ");
                float gpa = create.nextFloat();
                System.out.println("Enter the major of the student you would like to create: ");
                String major = create2.nextLine();
                System.out.println("Enter the faculty advisor of the student you would like to create: ");
                String facultyAdvisor = create2.nextLine();
                pst.clearParameters();
                pst.setString(1,firstName);
                pst.setString(2,lastName);
                pst.setFloat(3,gpa);
                pst.setString(4,major);
                pst.setString(5,facultyAdvisor);
                pst.executeUpdate();
                System.out.println("The student has successfully been added to the database.");
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }catch(InputMismatchException m){
                System.out.println(m.getMessage()); //handling an input from the user that is not accepted
            }
        }
        public void updateRecord(){ //method used to update a Major or Advisor for a specific student
            try{
                System.out.println("Would you like to update the student Major or Academic Advisor? (Select 1 for Major, 2 for Advisor)");
                Scanner selection = new Scanner(System.in);
                int choice = selection.nextInt();
                if (choice == 1){ //Updating based on Major
                    PreparedStatement pst = con.prepareStatement("UPDATE Student SET Major = ? WHERE StudentId = ?");
                    Scanner update = new Scanner(System.in);
                    System.out.println("Select the ID of the student for which you would like to update the major: ");
                    int id = update.nextInt();
                    System.out.println("Input the new major for the student: ");
                    Scanner update2 = new Scanner(System.in);
                    String major = update2.nextLine();
                    pst.clearParameters();
                    pst.setInt(2,id);
                    pst.setString(1,major);
                    pst.executeUpdate();
                    System.out.println("Update successful.");
                }
                else if(choice ==2){ //Updating based on Advisor
                    PreparedStatement pst = con.prepareStatement("UPDATE Student SET FacultyAdvisor = ? WHERE StudentId = ?");
                    Scanner update = new Scanner(System.in);
                    System.out.println("Select the ID of the student for which you would like to update the faculty advisor: ");
                    int id = update.nextInt();
                    System.out.println("Input the new faculty advisor for the student: ");
                    Scanner update2 = new Scanner(System.in);
                    String facAdv = update2.nextLine();
                    pst.clearParameters();
                    pst.setInt(2,id);
                    pst.setString(1,facAdv);
                    pst.executeUpdate();
                    System.out.println("Update successful.");

                }
                else{ //if input isnt 1 or 2
                    System.out.println("Wrong input. Please input either 1 or 2. ");
                    updateRecord();
                }

            }
            catch(SQLException e){
               System.out.println(e.getMessage());

            }
            catch(InputMismatchException m){
                System.out.println("Wrong input. Please input either 1 or 2.");
            }
        }
        public void searchStudent(){ //method used to seatch a student within the DB based on either his/her major, advisor or GPA
            try{ System.out.println("Would you like to search the student based on Major, Academic Advisor or GPA? (Select 1 for Major, 2 for Advisor, 3 for GPA).");
                Scanner search = new Scanner(System.in);
                int choice = search.nextInt();
                if(choice == 1){ //user decides to seatch based on major
                    PreparedStatement pst = con.prepareStatement("SELECT * FROM Student WHERE Major = ?");
                    Scanner search2 = new Scanner(System.in);
                    System.out.println("Enter the major you would like to search by: ");
                    String major = search2.nextLine();
                    pst.setString(1,major);
                    ResultSet searchResults = pst.executeQuery();
                    while (searchResults.next()) {
                        System.out.println("Student ID: " + searchResults.getString(1));
                        System.out.println("First name: " + searchResults.getString(2));
                        System.out.println("Last name: " + searchResults.getString(3));
                        System.out.println("GPA: " + searchResults.getFloat(4));
                        System.out.println("Major: " + searchResults.getString(5));
                        System.out.println("Faculty Advisor: " + searchResults.getString(6));
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

                    }

                }
                else if(choice == 2){ //user decides to search based on Advisor
                    PreparedStatement pst = con.prepareStatement("SELECT * FROM Student WHERE FacultyAdvisor = ?");
                    Scanner search2 = new Scanner(System.in);
                    System.out.println("Enter the faculty advisor you would like to search by: ");
                    String Adv = search2.nextLine();
                    pst.setString(1,Adv);
                    ResultSet searchResults = pst.executeQuery();
                    while (searchResults.next()) {
                        System.out.println("Student ID: " + searchResults.getString(1));
                        System.out.println("First name: " + searchResults.getString(2));
                        System.out.println("Last name: " + searchResults.getString(3));
                        System.out.println("GPA: " + searchResults.getFloat(4));
                        System.out.println("Major: " + searchResults.getString(5));
                        System.out.println("Faculty Advisor: " + searchResults.getString(6));
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

                    }

                }
                else if(choice ==3){ //user decides to search based on GPA
                    PreparedStatement pst = con.prepareStatement("SELECT * FROM Student WHERE GPA = ?");
                    Scanner search2 = new Scanner(System.in);
                    System.out.println("Enter the GPA you would like to search by: ");
                    double gpa = search2.nextDouble();
                    pst.setDouble(1,gpa);
                    ResultSet searchResults = pst.executeQuery();
                    while (searchResults.next()) {
                        System.out.println("Student ID: " + searchResults.getString(1));
                        System.out.println("First name: " + searchResults.getString(2));
                        System.out.println("Last name: " + searchResults.getString(3));
                        System.out.println("GPA: " + searchResults.getDouble(4));
                        System.out.println("Major: " + searchResults.getString(5));
                        System.out.println("Faculty Advisor: " + searchResults.getString(6));
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

                    }

                } else {
                    System.out.println("Wrong input. Please input either 1, 2 or 3.");
                }

            }
            catch(SQLException e){
                System.out.println(e.getMessage());

            }
            catch(InputMismatchException issue){
                System.out.println("Not a valid input. You can choose to return to the main menu now.");
            }
        }
    }

