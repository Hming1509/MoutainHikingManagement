/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mountainhiking;

import java.util.Scanner;

/**
 *
 * @author asus
 */
public class SystemUsing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        MountainList mountainData = new MountainList();
        StudentList studentList = new StudentList();
        // Load mountain data from file
        if (mountainData.loadMountainFromFile("MountainList.csv")) {
            System.out.println("Mountain data loaded successfully, " + mountainData.size() + " records found.");
        } else {
            System.out.println("Failed to load mountain data.");
        }
        Validation.setMountainList(mountainData);
        RegistrationManage registrationManage = new RegistrationManage(mountainData);
        if (studentList.loadRegistrationListFromFile("RegistrationList.dat")) {
            System.out.println("Registration data loaded successfully, " + studentList.size() + " records found.");
            registrationManage.getStudentList().addAll(studentList);
        } else {
            System.out.println("No previous registration data found.");
        }
        int choice;
        do {
            System.out.println("\n=======================================================");
            System.out.println("=       MOUNTAIN HIKING CHALLENGE REGISTRATION      =");
            System.out.println("=======================================================");
            System.out.println("| 1. New Registration                                 |");
            System.out.println("| 2. Update Registration Information                  |");
            System.out.println("| 3. Display Registered List                          |");
            System.out.println("| 4. Delete Registration Information                  |");
            System.out.println("| 5. Search Participants by Name                      |");
            System.out.println("| 6. Filter Data by Campus                            |");
            System.out.println("| 7. Statistics of Registration Numbers by Location   |");
            System.out.println("| 8. Save Data to File                                |");
            System.out.println("| 9. Exit the Program                                 |");
            System.out.println("=======================================================");
            System.out.print("Please choose an option (1-9): ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
            }
            switch (choice) {
                case 1:
                    Student student = new Student();
                    if (registrationManage.newRegistration(student)) {
                        System.out.println("Registration successful!");
                        registrationManage.saveToFile("Registrations.dat");
                    } else {
                        System.out.println("Registration failed.");
                    }
                    break;
                case 2:
                    System.out.print("Enter your Student ID: ");
                    String updateStudentId = sc.nextLine();
                    Student studentToUpdate = registrationManage.getStudentList().findStudentById(updateStudentId);
                    if (studentList.findStudentById(updateStudentId) == null) {
                        System.out.println("This student has not registered yet.");
                    } else {
                        studentToUpdate.updateNewInformationFromKeyboard();
                        registrationManage.updateRegistrationInformation(studentToUpdate);
                        System.out.println("Update successful!");
                    }
                    break;
                case 3:
                    registrationManage.displayRegistrationList();
                    break;
                case 4:
                    if (registrationManage.getStudentList().isEmpty()) {
                        System.out.println("No students have registered yet.");
                        break;
                    } else {
                        System.out.print("Enter your Student ID to delete: ");
                        String deleteStudentId = sc.nextLine();
                        Student studentToDelete = registrationManage.getStudentList().findStudentById(deleteStudentId);
                        if (studentToDelete != null) {
                            studentList.displayByStudent(studentToDelete);
                            System.out.print("Are you sure you want to delete this registration? (Y/N): ");
                            String confirm = sc.nextLine();
                            if (confirm.equalsIgnoreCase("Y")) {
                                if (registrationManage.deleteRegistrationInformation(deleteStudentId)) {
                                    System.out.println("The registration has been successfully deleted.");
                                } 
                            } else {
                                System.out.println("The registration has not been deleted.");
                            }
                        } else {
                            System.out.println("This student has not registered yet.");
                        }
                    }
                    break;
                case 5:
                    System.out.print("Enter the name to search: ");
                    String searchName = sc.nextLine();
                    StudentList foundStudents = registrationManage.searchParticipantsByName(searchName);
                    if (foundStudents != null) {
                        foundStudents.displayStudents(foundStudents);
                    } else {
                        System.out.println("No one matches the search criteria!");
                    }
                    break;
                case 6:
                    System.out.print("Enter Campus ID to filter (e.g., SE, HE, DE, QE, CE): ");
                    String campusID = sc.nextLine();
                    StudentList filteredList = registrationManage.filterDataByCampus(campusID);
                    if (filteredList != null) {
                        filteredList.displayStudents(filteredList);
                    } else {
                        System.out.println("No one matches the filter criteria!");
                    }
                    break;
                case 7:
                    System.out.print("-----------------------------------------------------------------------\n");
                    System.out.printf("%-10s | %-25s | %-15s\n", "Peak Code", "Number of Participants", "Total Cost");
                    System.out.print("-----------------------------------------------------------------------\n");
                    registrationManage.statisticOfRegistrationByMountainPeak();
                    System.out.print("-----------------------------------------------------------------------\n");
                    break;
                case 8:
                    int check = registrationManage.saveToFile("RegistrationList.dat");
                    if (check == 0) {
                        System.out.println("Registration data has been successfully saved to `registrations.dat`.");
                    } else if (check == -1) {
                        System.out.println("Error writing to file.");
                    } else if (check == -2) {
                        System.out.println("Error closing file.");
                    }
                    break;
                case 9:
                    if (Validation.isDataChanged()) {
                        System.out.print("Do you want to save the changes before exiting? (Y/N): ");
                        String saveChoice = sc.nextLine();
                        if (saveChoice.equalsIgnoreCase("Y")) {
                            int saveCheck = registrationManage.saveToFile("RegistrationList.txt");
                            if (saveCheck == 0) {
                                System.out.println("Registration data has been successfully saved to `registrations.dat`.");
                            } else if (saveCheck == -1) {
                                System.out.println("Error writing to file.");
                            } else if (saveCheck == -2) {
                                System.out.println("Error closing file.");
                            }
                        }
                    }
                    System.out.println("Exiting the program. Goodbye!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("This function is not available.");
                    break;
            }
        } while (choice != 9);
    }
}