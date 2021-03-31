/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resourcebookingsystem;

/**
 *
 * @author tim
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Resourcebookingsystem {

    /**
     * @param args the command line arguments
     */
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<booking> bookingList = new ArrayList<>();
//    private static ArrayList<booker> mybookers = new ArrayList<>();
    private static ArrayList<booker> bookerList = new ArrayList<>();
    private static int[] roomList = {1, 2, 3, 4, 5}; //rooms availble
    private static SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    public static Calendar cal = Calendar.getInstance();

    public static void main(String[] args) {

        bookingList = FileHandling1.readFile();
        bookerList = FileHandling2.readFile();

        mainMenu();

    }

    public static void mainMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("Welcome to the resource booking system");
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1 - Create a booking");
            System.out.println("2 - View all bookings");
            System.out.println("3 - Edit a booking");
            System.out.println("4 - Delete a booking");
            System.out.println("5 - Make a new booker");
            System.out.println("6 - View booker bookings");
            System.out.println("7 - View all bookers");
            System.out.println("8 - View all refreshment orders");//for staff to see orders
            System.out.println("0 - Exit");
            int userChoice = input.nextInt();
            switch (userChoice) {
                case 1:
                    addBooking();
                    break;

                case 2:
                    viewBookings();
                    break;

                case 3:
                    editBooking();
                    break;
                case 4:
                    deleteBooking();
                    break;
                case 5:
                    newbooker();
                    break;

                case 6:
                    viewbookerbooking();
                    break;
                case 7:
                    viewBookers();
                    break;

                case 8:
                    viewRefreshments();
                    break;

                case 0:
                    FileHandling1.writeFile(bookingList);
                    FileHandling2.writeFile(bookerList);
                    System.exit(0);

            }

        }
    }

    public static void addBooking() {
        try {

            String refreshments = null;
            String r_time = null;
            int bookerid = getbookerindex();
            String refreshments_q = null;

            //calling methods:
            int room = userRoom();
            String date = userDate();
            String time = userTime();
            int length = userLength();

            SimpleDateFormat df = new SimpleDateFormat("HH:mm");//date format
            Date d = df.parse(time);
            cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.HOUR, length);//adds the length of the meeting to the start time
            String endTime = df.format(cal.getTime()); //creates endTime (= the start time + the length of the meeting)

            Date startdate = format.parse(time);
            Date enddate = format.parse(endTime);
            int difference = (int) (enddate.getTime() - startdate.getTime());

            String extras = userExtras();
            String[] Refreshments = userRefreshments(startdate, enddate).split(",");

            //checking that their details aren't the same as the others
            boolean endloop = true;
            if (!bookingList.isEmpty()) {
                endloop = false;
            }

            int i = 0;
            for (int z = 0; (endloop == false); z++) {
                if (!bookingList.isEmpty()) {
                    Date date3 = format.parse(bookingList.get(i).getTime());

                    if ((time.equals(bookingList.get(i).getTime())) || ((enddate.after(date3)) && ((startdate.before(enddate))))) {//
                        if (room == (bookingList.get(i).getRoom())) {
                            if ((date.equals(bookingList.get(i).getDate()))) {
                                System.out.println("Sorry, this slot has already been booked. Please edit your informtion entered:");
                                System.out.println("Please type in what room you want");
                                System.out.println("The rooms we have are: Room 1 = 2 people, room 2 = 4 people, room 3 = 8 people, room 4 = 15 people, room 5 = 50 people. Room 4 is the only room with disabled access for wheelchairs.");
                                System.out.println("Please time in the number for the room you want (e.g.Room 1 => 1)");
                                room = input.nextInt();
                                System.out.println("Please type in a date in the format: dd/MM/yyyy");
                                input.nextLine();//to prevent skipping lines
                                date = input.nextLine();
                                System.out.println("Please type in a time in the format: xx:xx");
                                time = input.nextLine();
                            } else {
                                endloop = true;
                            }
                        } else {
                            endloop = true;
                        }
                    } else {
                        endloop = true;
                    }

                }
            }

            if (!bookingList.isEmpty()) {
                endloop = false;
            }

            if (bookerid == -1) {
                newbooker();
                bookerid = bookerList.size() - 1;
            }
            booking myBooking = new booking(room, date, time, length, extras, Refreshments[2], Refreshments[0], Refreshments[1], bookerList.get(bookerid).getName());
            bookingList.add(myBooking);

            System.out.println(myBooking.toString());

        } catch (ParseException ex) {
            Logger.getLogger(Resourcebookingsystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int userRoom() { //to get room input
        System.out.println("Please type in what room you want");
        System.out.println("The rooms we have are: Room 1 = 2 people, room 2 = 4 people, room 3 = 8 people, room 4 = 15 people, room 5 = 50 people. Room 4 is the only room with disabled access for wheelchairs.");
        System.out.println("Please time in the number for the room you want (e.g.Room 1 => 1)");
        int room = input.nextInt();

        while ((!(room == roomList[0])) && (!(room == roomList[1])) && (!(room == roomList[2])) && (!(room == roomList[3])) && (!(room == roomList[4]))) {//enters a vaild room
            System.out.println("Sorry but that's not a valid room");
            System.out.println("Please type in what room you want");
            System.out.println("The rooms we have are: Room 1 = 2 people, room 2 = 4 people, room 3 = 8 people, room 4 = 15 people, room 5 = 50 people. Room 4 is the only room with disabled access for wheelchairs.");
            System.out.println("Please time in the number for the room you want (e.g.Room 1 => 1)");
            room = input.nextInt();

        }
        return room;
    }

    public static String userDate() {
        String date = "";
        try {
            System.out.println("Please type in a date in the format: dd/MM/yyyy");
            input.nextLine();//to prevent skipping lines
            date = input.nextLine();
            SimpleDateFormat datef = new SimpleDateFormat("dd/MM/yyyy");//date format to help check the input is in the right format
            Date D = datef.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(D);
            date = datef.format(cal.getTime());

        } catch (ParseException ex) {
            Logger.getLogger(Resourcebookingsystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    public static String userTime() {

        System.out.println("Please type in a time in the format: xx:xx");
        String time = input.nextLine();
        return time;

    }

    public static int userLength() {
        System.out.println("How many hours do you want the room for?");
        int length = input.nextInt();
        return length;
    }

    public static String userExtras() {
        String extras = null;
        System.out.println("Would you like any extras?");
        input.nextLine();//to prevent skipping lines
        String extras_q = input.next();
        if (extras_q.toLowerCase().equals("yes")) {
            System.out.println("What would you like?");
            input.nextLine();//to prevent skipping lines
            extras = input.next();

        }
        return extras;
    }

    public static String userRefreshments(Date startdate, Date enddate) { //passed these 2 dates as parameters
        String refreshments = null;
        String r_time = null;
        String refreshments_q = null;
        Date refreshmentdate = null;
        try {

            System.out.println("Would you like any refreshments?");
            input.nextLine();//to prevent skipping lines
            refreshments_q = input.next();
            if (refreshments_q.toLowerCase().equals("yes")) {

                System.out.println("What would you like?");
                input.nextLine();//to prevent skipping lines
                refreshments = input.next();
                System.out.println("What time would you like them delivered?Please type in a time in the format: xx:xx ");
                r_time = input.next();

                refreshmentdate = format.parse(r_time);

                while ((refreshmentdate.after(enddate)) || ((refreshmentdate.before(startdate)))) { //makes sure booking for refreshments is within time slot
                    System.out.println("Sorry but you can't book refreshments with this booking at the time you requested as it's not within your booking");
                    System.out.println("What time would you like them delivered?Please type in a time in the format: xx.xx (e.g. 12:00 => 12.00)");
                    r_time = input.next();
                }

            }

        } catch (ParseException ex) {
            Logger.getLogger(Resourcebookingsystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return refreshments + "," + r_time + "," + refreshments_q;
    }

    public static void viewBookings() {
        if (bookingList.isEmpty()) {
            System.out.println("Sorry, there are no bookings at the moment");
        } else {
            for (int i = 0; i < bookingList.size(); i++) {
                System.out.println(bookingList.get(i).toString());
            }
        }
    }

    public static int getIndex() {
        //search in the obj in the array
        System.out.println("Please type in a time in the format: xx.xx");
        String time = input.nextLine();

        System.out.println("Please type in the room");
        int room = input.nextInt();

        System.out.println("Please type in a date in the format: dd/MM/yyyy");
        input.nextLine();//to prevent skipping lines
        String date = input.nextLine();

        int index = -1;//to set as the booking they have typed in
        if (!bookingList.isEmpty()) {
            for (int i = 0; i < bookingList.size(); i++) {
                if (time.equals(bookingList.get(i).getTime())) {
                    if (room == (bookingList.get(i).getRoom())) {
                        if (date.equals(bookingList.get(i).getDate())) {
                            return i;

                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void editBooking() {

        int index = getIndex();//choose which booking to edit
        if (index != -1) {
            try {
                 
                
                System.out.println("Are you changing atleast one of these: room, date, time, length ?");
                String editq = input.next();
                if (editq.toLowerCase().equals("yes")) {
                    int newroom = userRoom();

                    bookingList.get(index).setRoom(newroom);

                    String newdate = userDate();

                    bookingList.get(index).setDate(newdate);

                    String newtime = userTime();
                    int newlength = userLength();

                    SimpleDateFormat df = new SimpleDateFormat("HH:mm");//date format
                    Date d = df.parse(newtime);
                    cal = Calendar.getInstance();
                    cal.setTime(d);
                    cal.add(Calendar.HOUR, newlength);//adds the length of the meeting to the start time
                    String newendTime = df.format(cal.getTime()); //creates endTime (= the start time + the length of the meeting)
                    
                    Date startdate = format.parse(newtime);
                    Date enddate = format.parse(newendTime);
                    int difference = (int) (enddate.getTime() - startdate.getTime());
                     



                    boolean endloop = true;

                    if (!bookingList.isEmpty()) {
                        endloop = false;
                    }

                    int i = 0;
                    for (int z = 0; (endloop == false); z++) {
                        if (!bookingList.isEmpty()) {
                            Date bookingListDate = format.parse(bookingList.get(i).getTime());

                            if ((newtime.equals(bookingList.get(i).getTime())) || ((enddate.after(bookingListDate)) && ((startdate.before(enddate))))) {
                                if (newroom == (bookingList.get(i).getRoom())) {
                                    if ((newdate.equals(bookingList.get(i).getDate()))) {
                                        System.out.println("Sorry, this slot has already been booked. Please edit your informtion entered:");
                                        System.out.println("Please type in what room you want");

                                        newroom = input.nextInt();
                                        bookingList.get(index).setRoom(newroom);
                                        System.out.println("Please type in a date in the format: dd/MM/yyyy");
                                        input.nextLine();//to prevent skipping lines
                                        newdate = input.nextLine();
                                        bookingList.get(index).setDate(newdate);
                                        System.out.println("Please type in a time in the format: xx:xx");
                                        newtime = input.nextLine();
                                        bookingList.get(index).setTime(newtime);
                                    } else {
                                        endloop = true;
                                    }
                                } else {
                                    endloop = true;
                                }
                            } else {
                                endloop = true;
                            }

                        }
                    }
                    bookingList.get(index).setTime(newtime);
                    bookingList.get(index).setLength(newlength);
                }
                String newextras = userExtras();

                bookingList.get(index).setExtras(newextras);

                String[] newRefreshments = userRefreshments(startdate, enddate).split(",");


                bookingList.get(index).setRefreshments(newRefreshments[2]);
                bookingList.get(index).setRefreshments(newRefreshments[0]);
                bookingList.get(index).setR_time(newRefreshments[1]);

//                }
                System.out.println("Done, the booking has been changed to " + bookingList.get(index).toString());

            } catch (ParseException ex) {
                Logger.getLogger(Resourcebookingsystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("booking not found");

        }
    }

    public static void deleteBooking() {
        int index = getIndex();
        if (index != -1) {
            bookingList.remove(index);
        } else {
            System.out.println("booking not found");
        }
    }

    public static void newbooker() {
        System.out.println("This is our registration for new people making a booking");
        System.out.println("What's your name?");
        String name = input.next();

        System.out.println("What's your email?");
        String email = input.next();

        booker newbooker = new booker(name, email);
        bookerList.add(newbooker);

    }
    int bookingindex = getIndex();

    public static int getbookerindex() {
        System.out.println("What's their name?");
        String name = input.next();
        int index = -1;
        if (!bookerList.isEmpty()) {
            for (int i = 0; i < bookerList.size(); i++) {
                if (name.equals(bookerList.get(i).getName())) {
                    return i;
                }
            }
        }
        System.out.println("This person isn't currently registered in our system");
        return -1;
    }

    public static void viewbookerbooking() {
        int index = getbookerindex();//sees if booker is in arraylist of booker
        if (index == -1) {
            System.out.println("That's not a valid booker");
        } else {
            for (int i = 0; i < bookingList.size(); i++) {
                if (bookingList.get(i).getBookername().equals(bookerList.get(index).getName())) { //comparing name on the booking with the booker that we're searching for
                    System.out.println(bookerList.get(i).toString());
                }
            }
        }
    }

    public static void viewBookers() {
        if (bookerList.isEmpty()) {
            System.out.println("Sorry, there are no bookers at the moment");
        } else {
            for (int i = 0; i < bookerList.size(); i++) {
                System.out.println(bookerList.get(i).toString());
            }
        }
    }

    public static void viewRefreshments() {
        if (!bookingList.isEmpty()) {
            for (int i = 0; i < bookingList.size(); i++) {
                if (bookingList.get(i).getRefreshments_q().toLowerCase().equals("yes")) {
                    System.out.println("Room:" + bookingList.get(i).getRoom() + " .Date:" + bookingList.get(i).getDate() + " . Time to bring the refreshments:" + bookingList.get(i).getR_time() + " .The refreshments to bring:" + bookingList.get(i).getRefreshments());

                }
            }
        }

    }

}
