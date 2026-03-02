/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainhiking;

/**
 *
 * @author asus
 */
public class Validation {
    public static final String STUDENT_ID_PATTERN = "^(SE|HE|DE|QE|CE)\\d{6}$";
    public static final String NAME_PATTERN = "^.{2,20}$";
    public static final String PHONE_NUMBER_PATTERN = "^(09|08|03|07|05)\\d{8}$";
    // Viettel: 09[6-8], 086, 03[2-9]
    // VNPT (VinaPhone): 09[14], 088, 08[1-5]
    public static final String DISCOUNT_PHONE_PATTERN = "^((09[6-8]|086|03[2-9])|(09[14]|088|08[1-5]))\\d{7}$";
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static MountainList mountainList = new MountainList();
    public static boolean dataChanged;

    public static void setMountainList(MountainList mList) {
        mountainList = mList;
    }

    public static boolean isValidStudentId(String studentId) {
        return studentId != null && studentId.matches(STUDENT_ID_PATTERN);
    }

    public static boolean isValidName(String name) {
        return name != null && name.matches(NAME_PATTERN);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches(PHONE_NUMBER_PATTERN);
    }
    
    public static boolean isDiscountablePhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches(DISCOUNT_PHONE_PATTERN);
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_PATTERN);
    }

    public static boolean isValidMountainCode(String inputCode) {
        boolean check = false;
        if (inputCode == null || inputCode.trim().isEmpty()) {
            check = false;
        }
        if (mountainList == null || mountainList.isEmpty()) {
            check = false;
        }
        for (Mountain m : mountainList) {
            if (m.getMountainCode().equalsIgnoreCase(inputCode)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public static boolean isDataChanged() {
        return dataChanged;
    }
}