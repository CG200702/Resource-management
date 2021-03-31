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
public class booking {
    private int room;
    private String date;
    private String time;
    private int length;
    private String extras;
    private String refreshments_q;
    private String refreshments;
    private String r_time;
    private String bookername;

    public booking(int room, String date, String time, int length, String extras, String refreshments_q, String refreshments, String r_time, String bookername) {
        this.room = room;
        this.date = date;
        this.time = time;
        this.length = length;
        this.extras = extras;
        this.refreshments_q = refreshments_q;
        this.refreshments = refreshments;
        this.r_time = r_time;
        this.bookername = bookername;
    }
    public String toString(){
        return room+", "+date+", "+time+", "+length+", "+extras+", "+refreshments_q+", "+refreshments+", "+r_time+", "+bookername;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
   

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getRefreshments_q() {
        return refreshments_q;
    }

    public void setRefreshments_q(String refreshments_q) {
        this.refreshments_q = refreshments_q;
    }
    
    

    public String getRefreshments() {
        return refreshments;
    }

    public void setRefreshments(String refreshments) {
        this.refreshments = refreshments;
    }

    public String getR_time() {
        return r_time;
    }

    public void setR_time(String r_time) {
        this.r_time = r_time;
    }

    public String getBookername() {
        return bookername;
    }

    public void setBookername(String bookername) {
        this.bookername = bookername;
    }

    
}
