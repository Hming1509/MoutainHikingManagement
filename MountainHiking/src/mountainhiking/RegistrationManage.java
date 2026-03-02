/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainhiking;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author asus
 */
public class RegistrationManage {
    private StudentList studentList;
    private MountainList mountainList;
    Scanner sc = new Scanner(System.in);

    public RegistrationManage(MountainList mountainList) {
        this.studentList = new StudentList();
        this.mountainList = mountainList;
    }

    public StudentList getStudentList() {
        return studentList;
    }

    public boolean newRegistration(Student student) {
        boolean check = true;
        student.inputStudentInformationFromKeyboard();
        if (studentList.checkExistStudentId(student.getStuID())) {
            System.out.println("This Student ID already exists. Please enter a different one.");
            check = false;
        } else {
            student.setTuitionFee(studentList.calculateTuitionFee(student.getPhoneNum()));
            studentList.add(student);
        }
        Validation.dataChanged = true;
        return check;
    }

    public boolean updateRegistrationInformation(Student student) {
        boolean check = true;
        for (Student s : this.studentList) {
            if (s.getStuID().equals(student.getStuID())) {
                if (student.getName() != null) s.setName(student.getName());
                if (student.getPhoneNum() != null) s.setPhoneNum(student.getPhoneNum());
                if (student.getEmail() != null) s.setEmail(student.getEmail());
                if (student.getMountainCode() != null) s.setMountainCode(student.getMountainCode());
                s.setTuitionFee(student.getTuitionFee());
                break;
            }
        }
        Validation.dataChanged = true;
        return check;
    }

    public void displayRegistrationList() {
        if (studentList.isEmpty()) {
            System.out.println("No students have registered yet.");
        } else {
            System.out.print("---------------------------------------------------------------------------------------\n");
            System.out.printf("%-15s | %-25s | %-15s | %-10s | %-10s\n", "Student ID", "Name", "Phone", "Peak Code", "Fee");
            System.out.print("---------------------------------------------------------------------------------------\n");
            for (Student s : studentList) {
                System.out.printf(s.toString() + "\n");
            }
            System.out.print("---------------------------------------------------------------------------------------\n");
        }
    }

    public boolean deleteRegistrationInformation(String studentId) {
        boolean check = false;
        Student studentToDelete = studentList.findStudentById(studentId);
        if (studentToDelete != null) {
            studentList.displayByStudent(studentToDelete);
            studentList.remove(studentToDelete);
            check = true;
        }
        Validation.dataChanged = true;
        return check;
    }
    
    public StudentList searchParticipantsByName(String name) {
        StudentList searchResults = new StudentList();
        for (Student s : this.studentList) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                searchResults.add(s);
            }
        }
        return searchResults;
    }

    public StudentList filterDataByCampus(String campusID) {
        StudentList studentByCampusList = new StudentList();
        String campusCode = campusID.substring(0, 1).toUpperCase();
        for (Student s : this.studentList) {
            if (s.getStuID().toUpperCase().startsWith(campusCode)) {
                studentByCampusList.add(s);
            }
        }
        return studentByCampusList;
    }

    public void statisticOfRegistrationByMountainPeak() {
        int count = 0;
        for (Mountain m : mountainList) {
            int participantCount = 0;
            int totalCost = 0;
            for (Student s : studentList) {
                if (s.getMountainCode().equals(m.getMountainCode())) {
                    participantCount++;
                    totalCost += s.getTuitionFee();
                }
            }
            if (participantCount > 0) {
                mountainList.displayStatisticOfRegistrationByMountainPeak(m.getMountainCode(), participantCount, totalCost);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No registrations found for any mountain peak.");
        }
    }

    public int saveToFile(String path) {
        int check = 0;
        PrintWriter f = null;
        try {
            f = new PrintWriter(path);
            for (Student u : this.studentList) {
                String s = u.getStuID() + "," + u.getName() + "," + u.getPhoneNum() + "," + u.getEmail() + "," + u.getMountainCode() + "," + u.getTuitionFee() + "\n";
                f.print(s);
                f.flush();
            }
        } catch (Exception e) {
            check = -1;
            //
        } finally {
            try {
                if(f!=null) f.close();
            } catch (Exception e) {
                check = -2;
            }
        }
        return check;
    }
}