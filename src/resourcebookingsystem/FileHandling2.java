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
public class FileHandling2 {
   public static String folderDirectory = System.getProperty("user.dir") + "\\bookerList.txt";

    public static void writeFile(ArrayList<booker> bookerList) {
        try {
            FileWriter writeToFile = new FileWriter(folderDirectory, false);
            PrintWriter printToFile = new PrintWriter(writeToFile);
            for (int i = 0; i < bookerList.size(); i++) {
                printToFile.println(bookerList.get(i).toString());
            }
            printToFile.close();
            writeToFile.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static ArrayList<booker> readFile() {
        ArrayList<booker> bookerList = new ArrayList<>();
        String lineFromFile;
        try {
            BufferedReader read = new BufferedReader(new FileReader(folderDirectory));
            while ((lineFromFile = read.readLine()) != null) {
                String[] bookerDetails = lineFromFile.split(", ");

                booker myBookers = new booker(bookerDetails[0], bookerDetails[1]);//create new booker object with name and email

                bookerList.add(myBookers);

            }
            read.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return bookerList;
    }
}

