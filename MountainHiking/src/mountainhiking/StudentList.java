/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainhiking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class StudentList extends ArrayList<Student> {

    public boolean checkExistStudentId(String studentId) {
        boolean check = false;
        for (Student s : this) {
            if (s.getStuID().equals(studentId)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public int calculateTuitionFee(String phoneNum) {
        int result = 6000000;
        if (Validation.isDiscountablePhoneNumber(phoneNum)) {
            result *= 0.65;
        }
        return result;
    }

    public Student findStudentById(String studentId) {
        Student foundStudent = null;
        for (Student s : this) {
            if (s.getStuID().equals(studentId)) {
                foundStudent = s;
                break;
            }
        }
        return foundStudent;
    }

    public boolean loadRegistrationListFromFile(String path) {
        boolean check = true;
        FileReader f = null;
        BufferedReader r = null;
        try {
            File fileCheck = new File(path);
            if (fileCheck.exists() && fileCheck.isFile()) {
                f = new FileReader(path);
                r = new BufferedReader(f);
                while (r.ready()) {
                    String s = r.readLine();
                    String[] arr = s.split(",");
                    if (arr.length == 6) {
                        String studentID = arr[0];
                        String name = arr[1];
                        String phoneNum = arr[2];
                        String email = arr[3];
                        String mountainCode = arr[4];
                        String tuitionFeeStr = arr[5];
                        int tuitionFee = Integer.parseInt(tuitionFeeStr);
                        Student stu = new Student(studentID, name, phoneNum, email, mountainCode, tuitionFee);
                        this.add(stu);
                    }
                }
            }
        } catch (Exception e) {
            check = false;
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
                if (r != null) {
                    r.close();
                }
            } catch (Exception e) {
            }
        }
        return check;
    }

    public void displayByStudent(Student s) {
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("Student ID: %s\n", s.getStuID());
        System.out.printf("Name      : %s\n", s.getName());
        System.out.printf("Phone     : %s\n", s.getPhoneNum());
        System.out.printf("Moutain   : MT%s\n", s.getMountainCode());
        System.out.printf("Fee       : %s\n", s.getTuitionFee());
        System.out.println("-----------------------------------------------------------------");
    }

    public void displayStudents(StudentList specialList) {
        System.out.print("--------------------------------------------------------------------------------\n");
        System.out.printf("%-15s | %-25s | %-15s | %-10s | %-10s\n", "Student ID", "Name", "Phone", "Peek Code", "Fee");
        System.out.print("--------------------------------------------------------------------------------\n");
        for (Student s : specialList) {
            System.out.printf(s.toString() + "\n");
        }
        System.out.print("--------------------------------------------------------------------------------\n");
    }
}