/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resourcebookingsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author tim
 */
public class FileHandling1 {
public static String folderDirectory = System.getProperty("user.dir") + "\\bookingList.txt";

    public static void writeFile(ArrayList<booking>bookingList) {
        try {
            FileWriter writeToFile = new FileWriter(folderDirectory, false);
            PrintWriter printToFile = new PrintWriter(writeToFile);
            for (int i = 0; i < bookingList.size(); i++) {
                printToFile.println(bookingList.get(i).toString());
            }
            printToFile.close();
            writeToFile.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static ArrayList<booking>readFile() {
        ArrayList<booking>bookingList=new ArrayList<>();
        String lineFromFile;
        try {
            BufferedReader read = new BufferedReader(new FileReader(folderDirectory));
            while ((lineFromFile = read.readLine()) != null) {
                String[] bookingDetails = lineFromFile.split(", ");
                //Int room, String date, String time, int length, String extras,String refreshements_q, String refreshments, Strign r_time
                    booking myBooking = new booking(Integer.parseInt(bookingDetails[0]),bookingDetails[1],(bookingDetails[2]),Integer.parseInt(bookingDetails[3]),bookingDetails[4],bookingDetails[5],bookingDetails[6],bookingDetails[7],bookingDetails[8]);
                
                bookingList.add(myBooking);
            }
            read.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return bookingList;
    }
}


