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
public class MountainList extends ArrayList<Mountain>{
        public MountainList() {
        super();
    }

    public boolean loadMountainFromFile(String path) {
        boolean check = true;
        FileReader f = null;
        BufferedReader r = null;
        try {
           File fileCheck = new File(path);
           if( fileCheck.exists() && fileCheck.isFile()){
               f = new FileReader(path);
               r = new BufferedReader(f);
               while (r.ready()){
                   String s = r.readLine();
                   String[] arr = s.split(",");
                   if (arr.length == 4){
                        String mountainCode = arr[0];
                        String mountainName = arr[1];
                        String location = arr[2];
                        String description = arr[3];
                        Mountain m = new Mountain(mountainCode, mountainName, location, description);
                        this.add(m);
                   }
               }
           }
        } catch (Exception e) {
            check = false;
        } finally {
            try {
                if(f!=null) f.close();
                if(r!=null) r.close();
            } catch (Exception e) {
            }
        }
        return check;
    }

    public void displayStatisticOfRegistrationByMountainPeak(String peakCode, int numberOfParticipants, int totalCost) {
        System.out.printf("%-10s | %-25s | %-15s", "MT" + peakCode, numberOfParticipants, totalCost);
        System.out.println();
    }
}