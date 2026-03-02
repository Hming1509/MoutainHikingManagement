/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainhiking;

import java.util.Scanner;

/**
 *
 * @author asus
 */
public class Student {
    private String studentID;
    private String name;
    private String phoneNum;
    private String email;
    private String mountainCode;
    private int tuitionFee;

    public Student() {
        this.studentID = null;
        this.name = null;
        this.phoneNum = null;
        this.email = null;
        this.mountainCode = null;
        this.tuitionFee = 0;
    }

    public Student(String studentID, String name, String phoneNum, String email, String mountainCode, int tuitionFee) {
        this.studentID = studentID;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.mountainCode = mountainCode;
        this.tuitionFee = tuitionFee;
    }

    public String getStuID() {
        return studentID;
    }

    public void setStuID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public int getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(int tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public boolean inputStudentInformationFromKeyboard() {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        do {
            sc = new Scanner(System.in);
            System.out.print("Enter your Student ID: ");
            this.studentID = sc.nextLine();
            if (!Validation.isValidStudentId(this.studentID)) {
                System.out.println("Invalid Student ID format. Please try again.");
            } else {
                break; // Valid student ID
            }
        } while (!Validation.isValidStudentId(this.studentID));

        do {
            sc = new Scanner(System.in);
            System.out.print("Enter your Name: ");
            this.name = sc.nextLine();
            if (!Validation.isValidName(this.name)) {
                System.out.println("Invalid Name format. Please try again.");
            } else {
                break; // Valid name
            }
        } while (!Validation.isValidName(this.name));

        do {
            sc = new Scanner(System.in);
            System.out.print("Enter your Phone Number: ");
            this.phoneNum = sc.nextLine();
            if (!Validation.isValidPhoneNumber(this.phoneNum)) {
                System.out.println("Invalid Phone Number format. Please try again.");
            } else {
                break; // Valid phone number
            }
        } while (!Validation.isValidPhoneNumber(this.phoneNum));

        do {
            sc = new Scanner(System.in);
            System.out.print("Enter your Email: ");
            this.email = sc.nextLine();
            if (!Validation.isValidEmail(this.email)) {
                System.out.println("Invalid Email format. Please try again.");
            } else {
                break; // Valid email
            }
        } while (!Validation.isValidEmail(this.email));

        do {
            sc = new Scanner(System.in);
            System.out.print("Enter Mountain Peak Code (e.g., 01): ");
            this.mountainCode = sc.nextLine();
            if (!Validation.isValidMountainCode(this.mountainCode)) {
                System.out.println("Invalid Mountain Peak Code. Please try again.");
            } else {
                break;
            }
        } while (!Validation.isValidMountainCode(this.mountainCode));
        Validation.dataChanged = true;
        return check;
    }

    public Student updateNewInformationFromKeyboard() {
        Scanner sc = new Scanner(System.in);
        String newValue;
        do {
            sc = new Scanner(System.in);
            System.out.print("Enter your new Name: ");
            newValue = sc.nextLine();
            if (newValue.isEmpty()) {
                break;
            } 
            if (Validation.isValidName(this.name)){
                this.setName(newValue);
                break;
            } else {
                System.out.println("Invalid Name format. Please try again.");
            }
        } while (!Validation.isValidName(this.name));

        do {
            sc = new Scanner(System.in);
            System.out.print("Enter your new Phone Number: ");
            newValue = sc.nextLine();
            if (newValue.isEmpty()) {
                break;
            } 
            if (Validation.isValidPhoneNumber(this.phoneNum)){
                this.setPhoneNum(newValue);
                this.setTuitionFee(new StudentList().calculateTuitionFee(newValue));
                break;
            } else {
                System.out.println("Invalid Phone Number format. Please try again.");
            }
        } while (!Validation.isValidPhoneNumber(this.phoneNum));

        do {
            sc = new Scanner(System.in);
            System.out.print("Enter your new Email: ");
            newValue = sc.nextLine();
            if (newValue.isEmpty()) {
                break;
            } 
            if (Validation.isValidEmail(this.email)){
                this.setEmail(newValue);
                break;
            } else {
                System.out.println("Invalid Email format. Please try again.");
            }
        } while (!Validation.isValidEmail(this.email));

        do {
            sc = new Scanner(System.in);
            System.out.print("Enter new Mountain Peak Code (e.g., 01): ");
            newValue = sc.nextLine();
            if (newValue.isEmpty()) {
                break;
            } 
            if (Validation.isValidMountainCode(this.mountainCode)){
                this.setMountainCode(newValue);
                break;
            } else {
                System.out.println("Invalid Mountain Peak Code. Please try again.");
            }
        } while (!Validation.isValidMountainCode(this.mountainCode));
        Validation.dataChanged = true;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%-15s | %-25s | %-15s | %-10s | %-10s", studentID, name, phoneNum, "MT" + mountainCode, tuitionFee);
    }
}